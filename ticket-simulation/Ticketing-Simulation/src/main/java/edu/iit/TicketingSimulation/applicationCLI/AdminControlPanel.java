package edu.iit.ticketingservice.applicationCLI;

public class AdminControlPanel {

    private void start() {
        while (true) {
            ConsoleUIManager.displayTitle("System");
            ConsoleUIManager.displayMainMenu();
            int option = InputUtils.getOptionFromMenu(1,7);
            switch (option) {
                case 1:
                    TicketConfigurator.configureTicketing();
                    break;
                case 2:
                    DatabaseConfigurator.configureDatabase();
                    break;
                case 3:
                    startSpringBootApplication();
                    break;
                case 4:
                    monitorSystem();
                    break;
                case 5:
                    stopSpringBootApplication();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

        }
    }

    private void stopSpringBootApplication() {
    }


    private void configureSystem() {
    }


    private void monitorSystem() {
    }


    private void startSpringBootApplication() {
    }

    public static void main(String[] args) {
        AdminControlPanel adminControlPanel = new AdminControlPanel();
        adminControlPanel.start();

    }
}
