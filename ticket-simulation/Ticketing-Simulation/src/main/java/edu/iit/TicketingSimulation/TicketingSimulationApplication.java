package edu.iit.TicketingSimulation;

import edu.iit.TicketingSimulation.util.LoggingServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketingSimulationApplication {

	public static void main(String[] args) {
		LoggingServer.start();
		SpringApplication.run(TicketingSimulationApplication.class, args);
	}

}