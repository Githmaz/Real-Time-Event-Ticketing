package edu.iit.TicketingSimulation.applicationCLI;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DatabaseConfigurator is responsible for managing and updating database configurations.
 * This class interacts with the `application.yml` file to configure database connection settings,
 * such as the URL, username, and password.
 * Main Features:
 * - Update all database settings interactively.
 * - Save the updated configurations to the `application.yml` file.
 */
public class DatabaseConfigurator {

    private static final Logger logger = Logger.getLogger(DatabaseConfigurator.class.getName());
    private static final String CONFIG_PATH = "src/main/resources/application.yml";

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
                    logger.info("Returning to Main Menu...");
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
        saveDatabaseConfig(url, username, password);
        logger.info("Database settings updated successfully.");
    }

    private static void saveDatabaseConfig(String url, String username, String password) {
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_PATH)) {
            // Load existing YAML data
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(fileInputStream);

            // Create or get 'spring' and 'datasource' sections
            Map<String, Object> springData = (Map<String, Object>) data.getOrDefault("spring", new HashMap<>());
            data.put("spring", springData);

            Map<String, Object> datasourceData = (Map<String, Object>) springData.getOrDefault("datasource", new HashMap<>());
            springData.put("datasource", datasourceData);

            // Update or add database connection details only if they are not null
            if (url != null) {
                datasourceData.put("url", url);
            }
            if (username != null) {
                datasourceData.put("username", username);
            }
            if (password != null) {
                datasourceData.put("password", password);
            }

            // Configure YAML formatting options
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            options.setIndent(2);
            options.setPrettyFlow(true);

            // Write updated YAML data back to file
            try (FileWriter fileWriter = new FileWriter(CONFIG_PATH)) {
                Yaml yamlWriter = new Yaml(options);
                yamlWriter.dump(data, fileWriter);
                logger.info("Database configuration saved successfully.");
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, e, () -> "Failed to save the database configuration: " + e.getMessage());
        }
    }
}
