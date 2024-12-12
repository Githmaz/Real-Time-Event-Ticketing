package edu.iit.TicketingSimulation.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLoggerUtil {

    private static String logFilePath;

    /**
     * Initializes the logger with a new file for each simulation.
     * The file name includes a timestamp to ensure uniqueness.
     */
    public static void initializeNewLogFile() {
        // Generate a unique file name using the current timestamp
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        logFilePath = "src/main/resources/simulation_log_" + timestamp + ".txt";
    }

    /**
     * Logs a message to the current log file.
     * @param message The message to log.
     */
    public static synchronized void logToFile(String message) {
        if (logFilePath == null) {
            throw new IllegalStateException("Log file is not initialized. Call initializeNewLogFile() first.");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            // Get the current date and time
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            // Write the log entry with timestamp
            writer.write(timestamp + " - " + message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Failed to write log to file: " + e.getMessage());
        }
    }
}
