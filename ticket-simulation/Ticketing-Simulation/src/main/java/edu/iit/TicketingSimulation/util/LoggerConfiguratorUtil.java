package edu.iit.TicketingSimulation.util;

import java.util.logging.*;

public class LoggerConfiguratorUtil {

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
        logger.setUseParentHandlers(false);


        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new ColoredLogFormatter());
        handler.setLevel(level);

        logger.addHandler(handler);
        logger.setLevel(level);
    }

    // Method to configure the global logger (root logger) with the custom formatter
    public static void configureGlobalLogger() {
        LogManager logManager = LogManager.getLogManager();
        logManager.reset();

        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.ALL);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new ColoredLogFormatter());
        rootLogger.addHandler(consoleHandler);
    }
}
