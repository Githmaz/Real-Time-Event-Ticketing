# Ticketing System Project

## Overview
The Ticketing System Project is a comprehensive solution for managing ticketing processes, divided into:
- **InstaTicket**: A real-time ticketing app designed for live event management and seamless ticket booking.
- **Simulation Module**: A completed module simulating ticketing workflows in a controlled environment.

## Technologies Used
![Angular Badge](https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white)
![Tailwind CSS Badge](https://img.shields.io/badge/Tailwind_CSS-06B6D4?style=for-the-badge&logo=tailwindcss&logoColor=white)
![Java Badge](https://img.shields.io/badge/Java-FFA500?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot Badge](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Spring Security Badge](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Swagger Badge](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![MySQL Badge](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

## Project Breakdown

# (1) InstaTicket - Real-Time Ticketing System

## 🚀 Project Overview
InstaTicket is a real-time ticketing platform designed to streamline event management and ticket purchasing. It allows customers to browse events, purchase tickets, and manage bookings efficiently. Vendors can create and manage events, while the system ensures seamless transactions with secure authentication.

## 🎯 Features
- **User Authentication:** Secure login and registration using JWT tokens and Spring Security for enhanced protection.
- **Role-Based Access:** Control (RBAC): Ensure proper permissions for admins, vendors, and customers.
- **Dashboard:** A personalized dashboard for customers to track their bookings.
- **Mobile & Web Support:** Fully responsive UI with Tailwind CSS.
- **Event Management:** Vendors can create, edit, and delete events.
- **Ticket Booking:** Customers can browse events and purchase tickets.
- **Shopping Cart:** Users can manage ticket selections before finalizing purchases.
- **Lazy Loading Events:** Optimized loading of event listings for better performance.
- **Dark Mode Support:** UI theme toggle for better accessibility.

## 🛠 Tech Stack
| Technology | Purpose |
|------------|---------|
| **Java** | Core backend programming language |
| **Spring Boot** | Backend API & Business Logic |
| **MySQL** | Database for storing event and user data |
| **Angular** | Frontend framework |
| **Tailwind CSS** | Styling & UI components |
| **Node.js** | Server-side processing (if applicable) |

## 📸 Screenshots
Below are some snapshots of InstaTicket in action:

- **Customer Dashboard**  
  <div align="center" style="display: flex; align-items: center; gap: 10px;">
    <img src="https://github.com/user-attachments/assets/3b66b0da-8dd6-4052-8b69-9db2050dcb64" style="width: 75%; height: auto;"> 
    <img src="https://github.com/user-attachments/assets/5c5834b8-4803-48d5-b4aa-324a5c27d8d4" style="width: 19%; height: auto;">
  </div>

- **Event Page**  
  <div align="center" style="display: flex; align-items: center; gap: 10px;">
    <img src="https://github.com/user-attachments/assets/bba54526-5e32-4d05-b6f9-5e83f56b6f08" style="width: 75%; height: auto;"> 
    <img src="https://github.com/user-attachments/assets/1ed5ab62-7821-43e5-9e11-bd3ba933e839" style="width: 19%; height: auto;">
  </div>

- **Shopping Cart**  
  <div align="center" style="display: flex; align-items: center; gap: 10px;">
    <img src="https://github.com/user-attachments/assets/16863421-5102-4bd0-bfe8-00634938517e" style="width: 75%; height: auto;"> 
    <img src="https://github.com/user-attachments/assets/d669d0ea-d270-4f80-85be-82efeb6bfca4" style="width: 19%; height: auto;">
  </div>

- **User Login**  
  <div align="center" style="display: flex; align-items: center; gap: 10px;">
    <img src="https://github.com/user-attachments/assets/03b31358-e1c4-4352-8136-2484472bed63" style="width: 75%; height: auto;"> 
    <img src="https://github.com/user-attachments/assets/6dae8dc6-294f-48c6-848d-5d17b9d14eaa" style="width: 19%; height: auto;">
  </div>

- **User Register**  
  <div align="center" style="display: flex; align-items: center; gap: 10px;">
    <img src="https://github.com/user-attachments/assets/df597c4b-f773-4dbf-bcb7-20594a1254b0" style="width: 75%; height: auto;"> 
    <img src="https://github.com/user-attachments/assets/9cadf748-2cc3-45dd-9c57-bf10dae4488e" style="width: 19%; height: auto;">
  </div>

- **Error Handling & Validation**  
   <div align="center" style="display: flex; align-items: center; gap: 10px;">
    <img src="https://github.com/user-attachments/assets/0467d34b-0722-4675-8e45-0ca9353cc636" style="width: 75%; height: auto;"> 
    <img src="https://github.com/user-attachments/assets/7b67b6ff-efb9-45f8-967b-df5384377a6c" style="width: 19%; height: auto;">
  </div>
 <div align="center" style="display: flex; align-items: center; gap: 10px;">
    <img src="https://github.com/user-attachments/assets/52d52606-64f2-4914-bb0b-39cc3763a8c0" style="width: 75%; height: auto;"> 
    <img src="https://github.com/user-attachments/assets/4fe3f8f2-a3c4-4bd0-bc52-908c7a5444d7" style="width: 19%; height: auto;">
  </div>

## 📦 Installation Guide

### Backend (Spring Boot)
1. Clone the repository:
   ```sh
   git clone https://github.com/Githmaz/Real-Time-Event-Ticketing.git
   ```
2. Set up the MySQL database:
   - Create a database: `instaticket_db`
   - Configure `application.properties` with your DB credentials.
3. Build and run the application:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

### Frontend (Angular)
1. Navigate to the frontend directory:
   ```sh
   cd InstaTicket/frontend
   ```
2. Install dependencies:
   ```sh
   npm install
   ```
3. Run the application:
   ```sh
   ng serve
   ```
   The frontend will be available at `http://localhost:4200/`

## 📖 API Documentation
API documentation will be added soon. Stay tuned!

## 📜 License
This project is free to use and open for modification and distribution without restrictions.

# (2)Simulation Module
- **Event Setup**: Create and configure event parameters.
- **Ticket Management**: Simulate workflows for ticket booking.
- **Multithreading**: Implements the **Producer-Consumer Design Pattern** for efficient process simulation.
- **Testing Environment**: Analyze ticketing scenarios dynamically under various conditions.
