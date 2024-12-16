package edu.iit.ticketingservice.applicationCLI;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConfigurator {

    public static void configureDatabase() {
        while (true) {
            ConsoleUIManager.displayTitle("Database Configuration");
            ConsoleUIManager.displayDatabaseConfigMenu();
            int option = InputUtils.getOptionFromMenu(1, 5);
            switch (option) {
                case 1:
                    updateAllSettings();
                    break;
                case 2:
                    updateDatabaseUrl();
                    break;
                case 3:
                    updateUsername();
                    break;
                case 4:
                    updatePassword();
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void updateAllSettings() {
        String url = InputUtils.getStringInput("Enter new Database URL: ");
        String username = InputUtils.getStringInput("Enter new Username: ");
        String password = InputUtils.getStringInput("Enter new Password: ");
        saveDatabaseConfig(url, username, password);
    }

    private static void updateDatabaseUrl() {
        String url = InputUtils.getStringInput("Enter new Database URL: ");
        saveDatabaseConfig(url, null, null);
    }

    private static void updateUsername() {
        String username = InputUtils.getStringInput("Enter new Username: ");
        saveDatabaseConfig(null, username, null);
    }

    private static void updatePassword() {
        String password = InputUtils.getStringInput("Enter new Password: ");
        saveDatabaseConfig(null, null, password);
    }


    private static void saveDatabaseConfig(String url, String username, String password) {
        String path = "src/main/resources/application.yml";  // Make sure this path is correct

        try (FileInputStream fileInputStream = new FileInputStream(path)) {
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
            try (FileWriter fileWriter = new FileWriter(path)) {
                Yaml yamlWriter = new Yaml(options);
                yamlWriter.dump(data, fileWriter);
                System.out.println("Database configuration saved successfully.");
            }

        } catch (IOException e) {
            System.err.println("Failed to save the configuration: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
