package SingleThreaded;

import java.net.UnknownHostException;

public class Client {
    public void run() throws UnknownHostException{
        int port = 8080;
        String host = "localhost";
        try (java.net.Socket socket = new java.net.Socket(host, port)) {
            System.out.println("Connected to server at " + host + ":" + port);
            java.io.PrintWriter toServer = new java.io.PrintWriter(socket.getOutputStream(), true);
            java.io.BufferedReader fromServer = new java.io.BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));

            // Example of sending a message to the server
            toServer.println("Hello, Server!");
            String response = fromServer.readLine();
            System.out.println("Response from server: " + response);

            toServer.close();
            fromServer.close();
            socket.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Client cl = new Client();
        try {
            cl.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
