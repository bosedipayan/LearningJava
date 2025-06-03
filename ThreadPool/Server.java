package ThreadPool;

public class Server {
    public static void main(String[] args) {
        int port = 8081; // Default port
        java.util.concurrent.ExecutorService threadPool = java.util.concurrent.Executors.newFixedThreadPool(10); // Create a thread pool with 10 threads

        try (java.net.ServerSocket serverSocket = new java.net.ServerSocket(port)) {
            serverSocket.setSoTimeout(10000); // Set timeout for accepting connections
            System.out.println("Server is listening on port " + port);

            while (true) {
                java.net.Socket acceptedSocket = serverSocket.accept();
                threadPool.execute(() -> handleClient(acceptedSocket));
            }
        } catch (Exception e) {
            System.out.println("Error starting server: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (threadPool != null && !threadPool.isShutdown()) {
                threadPool.shutdown();
            }
        }
    }

    private static void handleClient(java.net.Socket clientSocket) {
        try {
            java.io.PrintWriter toClient = new java.io.PrintWriter(clientSocket.getOutputStream(), true);
            toClient.println("Hello from the server!");
            toClient.close();
            clientSocket.close();
        } catch (Exception e) {
            System.out.println("Error handling client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
