# Notification API

A Spring Boot backend service for managing real-time notifications in the Vittles application. This service handles creating, fetching, and managing the read state of user notifications.

## Tech Stack

- **Language**: Java 17+
- **Framework**: Spring Boot 3.x
- **Database**: H2 (In-memory for development/testing)
- **Build Tool**: Maven
- **Deployment**: Render

## Getting Started

### Prerequisites

- Java 17 or higher installed.
- Maven (optional, wrapper included).

### Running Locally

1.  Clone the repository.
2.  Navigate to the project directory:
    ```bash
    cd Notification_API-main
    ```
3.  Run the application using the Maven wrapper:
    ```bash
    ./mvnw spring-boot:run
    ```
    The server will start on `http://localhost:8088` (or port 8080 depending on configuration).

## API Endpoints

Base URL: `https://notification-api-brl8.onrender.com` (Production) / `http://localhost:8088` (Local)

### 1. Send a Notification
Creates a new notification for a user.

- **URL**: `/notifications`
- **Method**: `POST`
- **Body**:
    ```json
    {
      "senderId": 1,
      "receiverId": 2,
      "message": "Your order is ready!",
      "type": "READY_TO_PICKUP"
    }
    ```

### 2. Get User Notifications
Fetches all notifications for a specific user.

- **URL**: `/notifications/{userId}`
- **Method**: `GET`
- **Response**:
    ```json
    [
      {
        "id": 1,
        "message": "Your order is ready!",
        "type": "READY_TO_PICKUP",
        "timestamp": "2023-10-27T10:00:00",
        "read": false
      }
    ]
    ```

### 3. Mark as Read
Marks a specific notification as read.

- **URL**: `/notifications/{id}/read`
- **Method**: `PATCH`
- **Response**: Returns the updated notification object.

### 4. Delete Notification
Deletes a specific notification.

- **URL**: `/notifications/{id}`
- **Method**: `DELETE`
- **Response**: `204 No Content`

### 5. Clear All Notifications
Deletes all notifications for a specific user.

- **URL**: `/notifications/user/{userId}/all`
- **Method**: `DELETE`
- **Response**: `204 No Content`

## Project Structure

```
src/main/java/com/example/Notifi
├── config/          # Security and App configuration
├── controller/      # REST API Controllers
├── model/           # JPA Entities (Notification)
├── repository/      # Data Access Layer
└── service/         # Business Logic
```

## Deployment

This project is configured for deployment on **Render**.
- **Build Command**: `./mvnw clean package -DskipTests`
- **Start Command**: `java -jar target/*.jar`
