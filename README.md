# BookMyScreen - Movie Ticket Booking Platform

BookMyScreen is a modern, microservices-based movie ticket booking platform built with Spring Boot and React. It provides a seamless experience for users to browse movies, select theaters, and book tickets.

## 🚀 Features

- **User Authentication**
  - Secure registration and login
  - JWT-based authentication
  - Role-based access control (Customer/Admin)

- **Movie Management**
  - Browse available movies
  - View movie details and showtimes
  - Filter by theater and date

- **Booking System**
  - Select seats
  - Secure payment processing

## 🛠 Tech Stack

### Backend
- **Framework**: Spring Boot
- **Architecture**: Microservices
- **Services**:
  - User Service (Authentication, User Management)
  - Movie Service (Movie Information, Theater Management)
  - Notification Service (Notification Management)
  - Discovery Server (Eureka)
- **Database**: MongoDB, PostgreSQL
- **Security**: Spring Security, JWT

### Frontend
- **Framework**: React
- **State Management**: Redux Toolkit
- **UI Library**: Material-UI
- **Form Handling**: Formik + Yup
- **HTTP Client**: Axios

## 🏗 Architecture

The application follows a microservices architecture with:
- Service Discovery using Netflix Eureka
- API Gateway for routing
- JWT-based authentication
- Inter-service communication

## 🚦 Getting Started

### Prerequisites
- Java 17+
- Node.js 14+
- MongoDB
- Maven

### Backend Setup
1. Run docker-compose.yml to set up the environment

2. Start Discovery Server
```bash
cd discovery-server
mvn spring:boot run
```

3. Start Services
```bash
# Start each service in a separate terminal
cd api-gateway
mvn spring:boot run

cd user-service
mvn spring:boot run

cd movie-service
mvn spring:boot run

cd notification-service
mvn spring:boot run
```

### Frontend Setup
```bash
cd frontend
npm install
npm start
```

The application will be available at:
- Frontend: http://localhost:3000
- Backend Services: Various ports (dynamically assigned)
- Eureka Dashboard: http://localhost:8761


## 🔒 Security

- Passwords are encrypted using BCrypt
- JWT tokens for authentication
- Role-based access control
- Secure endpoints
- Input validation

## 🧪 Testing

```bash
# Backend
mvn test

# Frontend
npm test
```

## 📦 Deployment

The application can be deployed using Docker:
```bash
# Build Docker images
docker-compose build

# Run the application
docker-compose up
```

