import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static final int SERVER_PORT = 8186;
    private static DataInputStream in;
    private static DataOutputStream out;
    private static Socket clientSocket;
    private static String text;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)){

                while (true) {

                    System.out.println("Ожидание подключения...");
                    clientSocket = serverSocket.accept();
                    System.out.println("Подключение установлено!");

                    Thread t = new Thread(()->{
                        Scanner scanner = new Scanner(System.in);
                        while (true) {
                            text = scanner.nextLine();
                            System.out.println( "Сервер: "+ text);
                            try {
                                out.writeUTF("Сервер: "+ text);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    t.setDaemon(true);
                    t.start();

                    in = new DataInputStream (clientSocket.getInputStream());
                    out = new DataOutputStream(clientSocket.getOutputStream());
                    try {
                        while (true) {
                            String message = in.readUTF();
                            if (message.equals("/stop")){
                                System.out.println("Сервер остановлен");
                                System.exit(0);
                            }
                        System.out.println("Клиент: " + message) ;
                        out.writeUTF(message.toUpperCase());
                        }
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            }
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

