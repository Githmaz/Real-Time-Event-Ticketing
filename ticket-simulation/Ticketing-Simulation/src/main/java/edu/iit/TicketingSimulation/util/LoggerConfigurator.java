package edu.iit.TicketingSimulation.util;

import java.util.logging.*;

public class LoggerConfigurator {

    // ANSI color codes for console output
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";

    // Custom log formatter with color coding
    private static class ColoredLogFormatter extends Formatter {
        @Override
        public String format(LogRecord record) {
            String color;
            switch (record.getLevel().getName()) {
                case "SEVERE":
                    color = RED;
                    break;
                case "WARNING":
                    color = YELLOW;
                    break;
                case "INFO":
                    color = GREEN;
                    break;
                default:
                    color = RESET;
            }
            return String.format("%s%s: %s%s%n", color, record.getLevel(), record.getMessage(), RESET);
        }
    }

    // Method to configure a specific logger with the custom formatter
    public static void configureLogger(Logger logger, Level level) {
        // Remove default handlers to avoid duplicate outputs
        logger.setUseParentHandlers(false);

        // Create and configure a new console handler with the custom formatter
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new ColoredLogFormatter());
        handler.setLevel(level);

        logger.addHandler(handler); // Add the handler to the logger
        logger.setLevel(level); // Set the logging level
    }

    // Method to configure the global logger (root logger) with the custom formatter
    public static void configureGlobalLogger() {
        LogManager logManager = LogManager.getLogManager();
        logManager.reset(); // Reset all existing log configurations

        Logger rootLogger = Logger.getLogger(""); // Root logger
        rootLogger.setLevel(Level.ALL);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new ColoredLogFormatter());
        rootLogger.addHandler(consoleHandler); // Add the handler to the root logger
    }
}
