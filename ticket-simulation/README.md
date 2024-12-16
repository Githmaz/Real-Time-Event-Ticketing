# Real-Time Ticketing Simulation App

This application is an educational project that demonstrates the producer-consumer design pattern with multithreading. It is a comprehensive simulation of a real-world ticketing system, showcasing concepts like concurrency, real-time updates, and dynamic user interactions.

## Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [Installation and Setup](#installation-and-setup)
5. [Usage](#usage)
6. [Backend Structure](#backend-structure)
7. [API Endpoints](#api-endpoints)
8. [Future Enhancements](#future-enhancements)
9. [Credits](#credits)

---

## Overview

The Real-Time Ticketing Simulation App allows users to simulate ticket distribution and retrieval in a multithreaded environment. The application is designed to:

- OOP concepts 
- Simulate vendor ticket addition and customer ticket retrieval in real time.
- Demonstrate the producer-consumer design pattern.
- Provide real-time logs for all ticket-related activities.

This project is suitable for educational purposes and to understand how multithreading works in a practical scenario.

## Features

- **Configuration Module**: Set up simulation parameters such as ticket pool size, release rates, and retrieval rates.
- **Dynamic Vendor and Customer Addition**: Add vendors and customers dynamically during the simulation.
- **Real-Time Logs**: View logs of all activities, including ticket additions and retrievals.
- **CLI and Web Interface**: Access the simulation via a command-line interface or a Spring Boot-powered web interface.
- **Responsive Design**: User-friendly interface compatible with desktop and mobile devices.

## Technologies Used

### Frontend
- **Angular 18**: For building the interactive and dynamic user interface.
- **Tailwind CSS**: For styling and responsive design.

### Backend
- **Java (Spring Boot)**: For managing simulation logic and APIs.
- **Multithreading**: To simulate real-time ticketing operations.
- **WebSockets**: For real-time log updates.

### Other Tools
- **MySQL**: Database to store simulation configurations and logs.
- **GitHub**: For version control and collaboration.

## Installation and Setup

### Prerequisites

- Node.js and npm
- Angular CLI 18
- Java Development Kit (JDK) 23
- MySQL database

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/Githmaz/Real-Time-Event-Ticketing.git
   ```
2. Navigate to the frontend directory and install dependencies:
   ```bash
   cd frontend
   npm install
   ```
3. Start the Angular development server:
   ```bash
   ng serve
   ```
4. Navigate to the backend directory and run the Spring Boot application:
   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```
5. Configure your MySQL database settings in the backend `application.properties` file.

## Usage

1. **Configuration**: Begin by setting up the simulation parameters through the configuration module.
2. **Start Simulation**: Use the start button to begin the simulation.
3. **Add Customers and Vendors**: Dynamically add vendors and customers through the respective forms.
4. **View Logs**: Monitor real-time logs to track all activities.

## Backend Structure

```plaintext
tree
.
├── applicationCLI/                     # Command-line interface for admin controls
├── client/                             # Real-time logging client
├── config/                             # WebSocket configuration
├── controller/                         # REST controllers
├── dao/                                # Data access objects
├── dto/                                # Data transfer objects
├── model/                              # Core models (e.g., Customer, Ticket)
├── repository/                         # Database repositories
├── service/                            # Business logic and service layer
├── util/                               # Utilities for logging, file handling, etc.
└── TicketingSimulationApplication.java # Main Spring Boot entry point

```

### CLI Integration

- To run the CLI-based simulation, execute the `AdminControlPanel` class.
- For real-time logs, ensure the `LoggingClient` is active.


## Future Enhancements

- **Analytics Dashboard**: Visualize simulation performance and statistics.
- **Customizable Roles**: Add more roles to the simulation.
- **Advanced Multithreading Scenarios**: Include priority-based ticket allocation.
- **Cloud Deployment**: Deploy the application to platforms like AWS or Azure.

## Credits

This project incorporates knowledge gained from the following LinkedIn Learning courses:

1. **Java Threads**
2. **Spring Boot**
3. **Angular:Api Communicaiton**

Certificates for these courses are included in the `resources/certificates` directory.





