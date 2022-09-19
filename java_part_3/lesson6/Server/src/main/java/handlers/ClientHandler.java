package Server.handlers;

import Server.MyServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler {
    private static final String AUTH_CMD_PREFIX = "/auth"; // + login + password
    private static final String AUTHOK_CMD_PREFIX = "/authok"; // + username
    private static final String AUTHERR_CMD_PREFIX = "/autherr"; // + error message
    private static final String CLIENT_MSG_CMD_PREFIX = "/cMsg"; // + msg
    private static final String SERVER_MSG_CMD_PREFIX = "/sMsg"; // + msg
    private static final String PRIVATE_MSG_CMD_PREFIX = "/pm"; // + username + msg
    private static final String STOP_SERVER_CMD_PREFIX = "/stop";
    private static final String END_CLIENT_CMD_PREFIX = "/end";
    private static final String SERVER_MSG_CLIENTS_ONLINE = "/online";
    private static final String SERVER_MSG_REGISTRATION_USER = "/reg";
    private static final String SERVER_MSG_RENAME_USER = "/rename";
    private MyServer myServer;
    private Socket clientSocket;

    private DataInputStream in;
    private DataOutputStream out;
    private String username;
    private static final Logger LOGGER = LogManager.getLogger(ClientHandler.class);
    public ClientHandler(MyServer myServer, Socket socket) {

        this.myServer = myServer;
        clientSocket = socket;
    }

    public void handle() throws IOException {
        in = new DataInputStream(clientSocket.getInputStream());
        out = new DataOutputStream(clientSocket.getOutputStream());

        new Thread(() -> {

            try {
                authentication();
                readMessage();
            } catch (IOException e) {
                try {
                    myServer.broadcastServerMessage(this, "Пользователь " + username + " отключился от чата");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                LOGGER.info("Пользователь " + username + " отключился от чата");
                myServer.unSubscribe(this);
                try {
                    myServer.broadcastOnlineClients();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                e.printStackTrace();
            }
        }).start();
    }
    private void registration(String message){
            String[] partMessage = message.split("\\s+",4);
            String loginReg = partMessage[1];
            String passwordReg = partMessage[2];
            String usernameReg = partMessage[3];
            try {
                myServer.addUserDB(loginReg, passwordReg, usernameReg);
            } catch (SQLException e) {
                e.printStackTrace();
                LOGGER.error("Ошибка добавления пользователя");
        }
    }
    private void authentication() throws IOException {
        while (true){
           String message = in.readUTF();
           if (message.startsWith(SERVER_MSG_REGISTRATION_USER)) {
               registration(message);
           }
           else {
               if (message.startsWith(AUTH_CMD_PREFIX)) {
                   boolean isSuccessAuth = processAuthentication(message);
                   if (isSuccessAuth) {
                       break;
                   } else {
                       out.writeUTF(AUTHERR_CMD_PREFIX + " Неверная команда аутентификации");
                       LOGGER.error("Неверная команда аутентификации");
                   }

               }
           }
        }
    }

    private boolean processAuthentication(String message) throws IOException {
        String[] parts = message.split("\\s+");
        if (parts.length != 3) {
            out.writeUTF(AUTHERR_CMD_PREFIX + " Неверная команда аутентификации");
            LOGGER.error("Неверная команда аутентификации");
            return false;
        }

        String login = parts[1];
        String password = parts[2];


        //AuthenticationService auth = myServer.getAuthenticationService();

        try {

            username = myServer.AuthenticationService(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //username = auth.getUsernameByLoginAndPassword(login, password);

        if (username != null) {
            if (myServer.isUsernameBusy(username)) {
                out.writeUTF(AUTHERR_CMD_PREFIX + " Логин уже используется");
                return false;
            }

            out.writeUTF(AUTHOK_CMD_PREFIX + " " + username);
            myServer.subscribe(this);
            LOGGER.info("Пользователь " + username + " подключился к чату");
            myServer.broadcastServerMessage(this, "Пользователь " + username + " подключился к чату");
            myServer.broadcastOnlineClients();

            return true;
        } else {
        try {
            out.writeUTF(AUTHERR_CMD_PREFIX + " Неверная комбинация логина и пароля");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
        }

    }

    private void readMessage() throws IOException {
        while (true) {
            String message = in.readUTF();
            LOGGER.info("message | " + username + ": " + message);
            String typeMessage = message.split("\\s+")[0];

            if (!typeMessage.startsWith("/")){
                LOGGER.warn("Неверный запрос");

            }

            switch (typeMessage) {
                case STOP_SERVER_CMD_PREFIX -> myServer.stop();
                case END_CLIENT_CMD_PREFIX -> closeConnection();
                case CLIENT_MSG_CMD_PREFIX -> {
                    String[] messageParts = message.split("\\S+",2);
                    myServer.broadcastMessage(this, messageParts[1]);

                }
                case PRIVATE_MSG_CMD_PREFIX -> {
                    String[] partMessage = message.split("\\s+",3);
                    String recipient = partMessage[1];
                    String privateMessage = partMessage[2];
                    myServer.privateBroadcastMessage(this, recipient, privateMessage);
                }
                case SERVER_MSG_RENAME_USER -> {
                    String[] partMessage = message.split("\\s+",2);
                    String newUserName = partMessage[1];
                    try {
                        myServer.updateNameUser(this, newUserName);
                        myServer.broadcastOnlineClients();

                    } catch (SQLException e) {
                        e.printStackTrace();
                        LOGGER.warn("Ошибка смены логина");
                        //out.writeUTF(SERVER_MSG_RENAME_USER + " Ошибка смены логина");
                    }
                }
                default -> LOGGER.warn("Неверная команда");
            }

        }
    }

    private void closeConnection() throws IOException {
        clientSocket.close();
        myServer.unSubscribe(this);
        LOGGER.info(username + " отключился");
    }

    public void sendServerMessage(String message) throws IOException {
        out.writeUTF(String.format("%s %s", SERVER_MSG_CMD_PREFIX, message));
    }
    public void sendServerOnlineClients(String message) throws IOException {
        out.writeUTF(String.format("%s %s", SERVER_MSG_CLIENTS_ONLINE, message));
        System.out.println(message);
    }
    public void sendMessage(String sender, String message) throws IOException {
        out.writeUTF(String.format("%s %s %s", CLIENT_MSG_CMD_PREFIX, sender, message));
    }
    public void sendMessage(String sender, String message,boolean isPrivate) throws IOException {
        out.writeUTF(String.format("%s %s %s", isPrivate ?
                        PRIVATE_MSG_CMD_PREFIX
                        : CLIENT_MSG_CMD_PREFIX,
                    sender, message));
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}

