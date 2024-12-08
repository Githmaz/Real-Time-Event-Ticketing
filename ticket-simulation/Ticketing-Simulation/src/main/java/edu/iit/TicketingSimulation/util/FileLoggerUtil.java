package edu.iit.TicketingSimulation.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLoggerUtil {

    private static final String LOG_FILE_PATH = "src/main/resources/simulation_logs.txt";

    public static synchronized void logToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            // Get the current date and time
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String logEntry = timestamp + " - " + message;
            // Print the log entry to the console
            System.out.println(logEntry);
            // Write the log entry with timestamp
            writer.write(timestamp + " - " + message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Failed to write log to file: " + e.getMessage());
        }
    }
}
