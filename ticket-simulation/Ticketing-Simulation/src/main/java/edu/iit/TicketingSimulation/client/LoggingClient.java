package edu.iit.TicketingSimulation.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * LoggingClient connects to the LoggingServer and displays logs in a separate CLI window.
 */
public class LoggingClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 9090);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to Logging Server. Displaying real-time logs:\n");
            String logMessage;
            while ((logMessage = in.readLine()) != null) {
                System.out.println(logMessage);
            }
        } catch (Exception e) {
            System.err.println("Failed to connect to Logging Server: " + e.getMessage());
        }
    }
}
