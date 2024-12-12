package edu.iit.TicketingSimulation.util;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * LoggingServer starts a server that broadcasts log messages to all connected clients.
 */
public class LoggingServer {

    private static final int PORT = 9090;
    private static final CopyOnWriteArrayList<PrintWriter> clients = new CopyOnWriteArrayList<>();

    public static void start() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Logging Server started on port " + PORT);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    clients.add(out);
                }
            } catch (Exception e) {
                System.err.println("Logging Server failed: " + e.getMessage());
            }
        }).start();
    }

    public static void broadcastLog(String message) {
        for (PrintWriter client : clients) {
            client.println(message);
        }
    }
}
