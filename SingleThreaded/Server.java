package SingleThreaded;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() {
        int port = 8080;
        try (ServerSocket socket = new ServerSocket(port)) {
            socket.setSoTimeout(10000);

            System.out.println("Server is listening on port " + port);
            Socket clientSocket = socket.accept();
            System.out.println("Client connected: " + clientSocket.getRemoteSocketAddress());
            PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader fromClient = new BufferedReader(new java.io.InputStreamReader(clientSocket.getInputStream()));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Server server  = new Server();
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}