# 🚆 Railway Ticket Booking Microservices  

This project is built on a **microservices architecture** for the railway ticket booking system.  
The system is designed to be **scalable**, **event-driven**, and a **high-performance** application.  

## 📌 Project Structure  
![Project Structure]()  

## 🚀 Functional Requirements  
✅ Users can search for routes  
✅ Users can purchase tickets  
✅ Users can view their booked tickets  
✅ If payment is not completed within 12 minutes, the ticket is automatically canceled  
✅ A **notification service** is triggered for canceled or modified tickets  
✅ A **dynamic pricing** system is implemented for each ticket  
✅ Redis is used for fast caching and **performance optimization**  

## ⚡ Tech Stack  
🔹 **Spring Boot** - Core microservices (User, Booking, Notification)  
🔹 **Kafka** - Event-driven system  
🔹 **Redis** - Availability & caching  
🔹 **MongoDB & PostgreSQL** - Data storage  
🔹 **Docker & Kubernetes** - Containerization & orchestration  
🔹 **Spring Security & JWT** - Authentication & authorization  

## 🛠 Deployment  
The project can be easily deployed using **Docker Compose**.
