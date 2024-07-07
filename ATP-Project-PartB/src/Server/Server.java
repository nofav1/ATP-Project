package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server{
    private int port;
    //private int listeningIntervalMS;
    private IServerStrategy strategy;
    private boolean stop;
    private Thread serverThread;
    private ExecutorService threadPool; // Thread pool
    private ServerSocket serverSocket;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        //this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        // initialize a new fixed thread pool with 2 threads:
        int threadPoolSize = Configurations.getInstance().getThreadPoolSize();
        this.threadPool = Executors.newFixedThreadPool(threadPoolSize);
    }

    public void start(){
        serverThread = new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                //System.out.println("Starting server at port = " + port);

                while (!stop) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        //System.out.println("Client accepted: " + clientSocket.toString());

                        // Insert the new task into the thread pool:
                        threadPool.submit(() -> handleClient(clientSocket));

                    } catch (SocketTimeoutException e) {
                        System.out.println("Socket timeout");
                    } catch (SocketException e) {
                        if (stop) {
                            System.out.println("Server socket closed, stopping server.");
                        } else {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        if (!stop) {
                            e.printStackTrace();
                        }
                    }
                }
                serverSocket.close();
            } catch (IOException e) {
                if (!stop) {
                    e.printStackTrace();
                }
            }
        });
        serverThread.start();
    }

    private void handleClient(Socket clientSocket) {
        try {
            strategy.serverstrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            //System.out.println("Done handling client: " + clientSocket.toString());
            clientSocket.close();
        } catch (IOException e){
            //System.out.println("Error handling client: " + e.getMessage());
        }
    }

    public void stop(){
        stop = true;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close(); // Close the server socket to exit the blocking accept call
            }
            if (serverThread != null) {
                serverThread.join(); // Wait for the server thread to finish
            }
            if (threadPool != null) {
                threadPool.shutdown(); // Shutdown the thread pool
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    threadPool.shutdownNow(); // Force shutdown if not completed within timeout
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt(); // Restore the interrupt status
        }
    }
}
