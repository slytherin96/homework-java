package Server;

import Server.handlers.ClientHandler;
import Server.services.AuthenticationService;
import Server.services.impl.SimpleAuthenticationServiceImpl;
import services.authentication.AuthenticationDB;
import services.authentication.RegistrationDB;
import services.authentication.RenameDB;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MyServer {

    private final ServerSocket serverSocket;
    private final AuthenticationService authenticationService;
    private final ArrayList<ClientHandler> clients;
    private AuthenticationDB authenticationDB;

    private ArrayList<String> clientsNameOnline;


    public MyServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        authenticationService = new SimpleAuthenticationServiceImpl();
        clients = new ArrayList<>();
        clientsNameOnline = new ArrayList<>();

    }

    public void start() {
        System.out.println("CЕРВЕР ЗАПУЩЕН!");
        System.out.println("---------------");
        while (true) {
            try {
                waitAndProcessNewClientConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*public ArrayList<String> getClientsNameOnline() {
        for (ClientHandler client : clients) {
            clientsNameOnline.add(client.getUsername());
        }
        return clientsNameOnline;
    }*/


    private void waitAndProcessNewClientConnection() throws IOException {
        System.out.println("Ожидание клиента...");
        Socket socket = serverSocket.accept();
        System.out.println("Клиент подключился!");

        processClientConnection(socket);
    }
   /* private ConnectDB getConnectDB(){
        ConnectDB connect = null;

        try {
            connect = new ConnectDB();
            connect.connection();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect;

    }*/

    private void processClientConnection(Socket socket) throws IOException {
        ClientHandler handler = new ClientHandler(this, socket);
        handler.handle();
    }

    public boolean isUsernameBusy(String username) {
        for (ClientHandler client : clients) {
            if (client.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void subscribe(ClientHandler handler) {
        clients.add(handler);
        clientsNameOnline.add(handler.getUsername());
    }

    public synchronized void unSubscribe(ClientHandler handler) {
        clients.remove(handler);
        clientsNameOnline.remove(handler.getUsername());
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public void stop() {
        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println("ЗАВЕРШЕНИЕ РАБОТЫ");
        System.exit(0);
    }

    public synchronized void broadcastMessage(ClientHandler sender, String message) throws IOException {
        for (ClientHandler client : clients) {
            /*if (client == sender) {
                continue;
            }*/
            client.sendMessage(sender.getUsername(), message);
        }
    }

    public synchronized void addUserDB (String login, String password, String username) throws SQLException {
        RegistrationDB registrationDB = new RegistrationDB();
        registrationDB.addUser(login, password, username);
    }

    public synchronized String AuthenticationService (String login, String password) throws SQLException {
        authenticationDB = new AuthenticationDB();
        return authenticationDB.getUsernameByLoginAndPassword(login, password);
    }
    public synchronized void privateBroadcastMessage(ClientHandler sender, String recipient, String message) throws IOException {
        for (ClientHandler client : clients) {
            if (client == sender) {
                continue;
            }
            if (client.getUsername().equals(recipient)) {
                client.sendMessage(sender.getUsername(), message, true);
            }
        }
        System.out.println(clientsNameOnline.toString());
    }

    public synchronized void updateNameUser(ClientHandler sender, String newUserName) throws IOException, SQLException {
        RenameDB renameDB = new RenameDB();
        renameDB.renameUser(sender.getUsername(), newUserName);
        clientsNameOnline.remove(sender.getUsername());
        sender.setUsername(newUserName);
        clientsNameOnline.add(sender.getUsername());
    }

    public synchronized void broadcastServerMessage(ClientHandler sender, String message) throws IOException {
        for (ClientHandler client : clients) {
            if (client == sender) {
                continue;
            }
            client.sendServerMessage(message);
        }
    }

    public synchronized void broadcastOnlineClients() throws IOException {
        for (ClientHandler client : clients) {
            client.sendServerOnlineClients(String.join(";",clientsNameOnline));
        }
    }
}
