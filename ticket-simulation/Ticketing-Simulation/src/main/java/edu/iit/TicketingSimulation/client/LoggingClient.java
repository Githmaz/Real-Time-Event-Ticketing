package edu.iit.TicketingSimulation.client;

import edu.iit.TicketingSimulation.applicationCLI.DatabaseConfigurator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * LoggingClient connects to the LoggingServer and displays logs in a separate CLI window.
 */
public class LoggingClient {
    private static final Logger logger = Logger.getLogger(LoggingClient.class.getName());
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 9090);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            logger.info("Connected to Logging Server. Displaying real-time logs:\n");
            String logMessage;
            while ((logMessage = in.readLine()) != null) {
                System.out.println(logMessage);
            }
        } catch (Exception e) {
            logger.warning("Failed to connect to Logging Server: " + e.getMessage());
        }
    }
}
