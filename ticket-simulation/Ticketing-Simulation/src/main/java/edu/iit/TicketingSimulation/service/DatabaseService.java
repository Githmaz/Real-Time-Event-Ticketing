package edu.iit.TicketingSimulation.service;

import org.springframework.stereotype.Service;
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
 * DatabaseService handles database configuration updates programmatically.
 * This class provides methods to update the database configuration fields in the application.yml file.
 * Main Features:
 * - Update URL, username, and password for the database configuration.
 * - Ensure proper YAML formatting when saving changes.
 */
@Service
public class DatabaseService {

    private static final Logger logger = Logger.getLogger(DatabaseService.class.getName());
    private static final String CONFIG_PATH = "src/main/resources/application.yml";

    /**
     * Updates the database configuration with the provided values.
     * Only non-null values are updated.
     *
     * @param url      the new database URL, or null to keep the current value
     * @param username the new database username, or null to keep the current value
     * @param password the new database password, or null to keep the current value
     */
    public void updateDatabaseConfig(String url, String username, String password) {
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_PATH)) {
            // Load existing YAML data
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(fileInputStream);

            // Create or get 'spring' and 'datasource' sections
            Map<String, Object> springData = (Map<String, Object>) data.getOrDefault("spring", new HashMap<>());
            data.put("spring", springData);

            Map<String, Object> datasourceData = (Map<String, Object>) springData.getOrDefault("datasource", new HashMap<>());
            springData.put("datasource", datasourceData);

            // Update database connection details
            if (url != null) {
                datasourceData.put("url", url);
            }
            if (username != null) {
                datasourceData.put("username", username);
            }
            if (password != null) {
                datasourceData.put("password", password);
            }

            // Save updated configuration back to the file
            saveYamlToFile(data);

        } catch (IOException e) {
            logger.log(Level.SEVERE, e, () -> "Failed to update the database configuration: " + e.getMessage());
        }
    }

    // Save YAML data to the file with proper formatting
    private void saveYamlToFile(Map<String, Object> data) {
        try (FileWriter fileWriter = new FileWriter(CONFIG_PATH)) {
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            options.setIndent(2);
            options.setPrettyFlow(true);

            Yaml yamlWriter = new Yaml(options);
            yamlWriter.dump(data, fileWriter);

            logger.info("Database configuration saved successfully.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, e, () -> "Failed to save the database configuration: " + e.getMessage());
        }
    }
}
