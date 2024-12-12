package edu.iit.TicketingSimulation.applicationCLI;

import edu.iit.TicketingSimulation.service.impl.DatabaseServiceImpl;

import java.util.logging.Logger;

/**
 * DatabaseConfigurator is responsible for providing a CLI interface to manage database configurations.
 * It interacts with the `DatabaseService` to apply changes to the application.yml file.
 */
public class DatabaseConfigurator {

    private static final Logger logger = Logger.getLogger(DatabaseConfigurator.class.getName());
    private static final DatabaseServiceImpl databaseService = new DatabaseServiceImpl();

    public static void configureDatabase() {
        while (true) {
            ConsoleUIManager.displayTitle("Database Configuration");
            ConsoleUIManager.displayDatabaseConfigMenu();
            int option = InputUtils.getOptionFromMenu(1, 2);
            switch (option) {
                case 1:
                    updateDatabaseSettings();
                    break;
                case 2:
                    return;
                default:
                    logger.warning("Invalid option selected. Please try again.");
            }
        }
    }

    private static void updateDatabaseSettings() {
        // Use optional input to allow skipping certain fields
        String url = InputUtils.getOptionalStringInput("Enter new Database URL");
        String username = InputUtils.getOptionalStringInput("Enter new Username");
        String password = InputUtils.getOptionalStringInput("Enter new Password");

        databaseService.updateDatabaseConfig(url, username, password);
    }
}
