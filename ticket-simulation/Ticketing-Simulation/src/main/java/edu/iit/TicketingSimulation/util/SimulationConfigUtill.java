package edu.iit.TicketingSimulation.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import edu.iit.TicketingSimulation.dto.SimulationConfig;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimulationConfigUtill {
    private static final String JSON_FILE_PATH = "src/main/java/edu/iit/TicketingSimulation/config/config.json";
    private static final Logger logger = Logger.getLogger(SimulationConfigUtill.class.getName());


    public static SimulationConfig readConfig() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            return gson.fromJson(reader, SimulationConfig.class); // Deserialize JSON into TicketConfig object
        } catch (IOException | JsonSyntaxException e) {
            logger.log(Level.SEVERE, e, () -> "Failed to read the JSON configuration: " + e.getMessage());
            return null; // Return null if reading fails
        }
    }

    public static void saveConfig(SimulationConfig config) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            gson.toJson(config, writer);
            logger.info("Configuration saved successfully in JSON.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, e, () -> "Failed to save the JSON configuration: " + e.getMessage());
        }
    }

}
