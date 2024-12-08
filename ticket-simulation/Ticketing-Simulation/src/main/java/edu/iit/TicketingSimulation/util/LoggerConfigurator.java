package edu.iit.TicketingSimulation.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

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
            String color = switch (record.getLevel().getName()) {
                case "SEVERE" -> RED;
                case "WARNING" -> YELLOW;
                case "INFO" -> GREEN;
                default -> RESET;
            };
            return String.format("%s%s: %s%s%n", color, record.getLevel(), record.getMessage(), RESET);
        }
    }

    // Method to configure a logger with the custom formatter
    public static void configureLogger(Logger logger, Level level) {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new ColoredLogFormatter());
        logger.setUseParentHandlers(false); // Disable default handlers
        logger.addHandler(handler);
        logger.setLevel(level); // Set the logging level
    }
}
