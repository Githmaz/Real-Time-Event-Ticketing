package edu.iit.TicketingSimulation.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import edu.iit.TicketingSimulation.config.TicketConfig;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimulationConfigUtill {
    private static final String JSON_FILE_PATH = "src/main/java/edu/iit/TicketingSimulation/config/config.json";
    private static final Logger logger = Logger.getLogger(SimulationConfigUtill.class.getName());

    public static TicketConfig readConfig() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            return gson.fromJson(reader, TicketConfig.class); // Deserialize JSON into TicketConfig object
        } catch (IOException | JsonSyntaxException e) {
            logger.log(Level.SEVERE, "Failed to read configuration: " + e.getMessage(), e);
            return null; // Return null if reading fails
        }
    }

    public static void saveConfig(TicketConfig config) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            gson.toJson(config, writer); // Serialize the config object into JSON and write to file
            logger.info("Configuration saved successfully.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to save configuration: " + e.getMessage(), e);
        }
    }
}
