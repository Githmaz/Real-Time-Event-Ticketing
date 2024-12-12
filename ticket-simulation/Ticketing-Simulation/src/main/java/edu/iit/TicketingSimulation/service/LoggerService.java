package edu.iit.TicketingSimulation.service;

import edu.iit.TicketingSimulation.config.WebSocketHandler;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
    private WebSocketHandler webSocketHandler;

    public LoggerService(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    public void sendLoggerToClient(String message) {
        webSocketHandler.sendMessageToClient(message);
    }

    public boolean checkSession() {
        return webSocketHandler.isSessionAlive();
    }

}
