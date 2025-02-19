package edu.iit.ticketingservice;

import edu.iit.ticketingservice.service.impl.TicketCartServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TicketingServiceApplication {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        startApplication();

    }

    public static void startApplication() {
        context = SpringApplication.run(TicketingServiceApplication.class);

    }
    public static void stopApplication() {
        context.close();
    }
}
