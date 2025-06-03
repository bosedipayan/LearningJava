package MultiThreaded;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {

    public Consumer<Socket> onAccept = (clientSocket) -> {
        // Default implementation does nothing
        try {
            PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
            toClient.println("Hello from the server!");
            toClient.close();
            clientSocket.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    };
    public static void main(String[] args) {
        int port = 8081; // Default port
        Server server = new Server();
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000); // Set timeout for accepting connections
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket acceptedSocket = serverSocket.accept();

                Thread thread = new Thread(() -> server.onAccept.accept(acceptedSocket));
                thread.start();
            }
        }
        catch (Exception e) {
            System.out.println("Error starting server: " + e.getMessage());
            e.printStackTrace();
            return;
        }
    }
}
