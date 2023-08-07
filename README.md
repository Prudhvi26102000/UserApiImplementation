# UserApiImplementation Backend
CRUD Operations of a User.
## Table of Contents

- [Introduction](#introduction)
- [Technologies](#technologies)
- [Database Schema](#database-schema)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [API Endpoints](#api-endpoints)
    - [1. User Registration](#1-user-registration)
    - [2. Generate Token](#2-generate-token)
    - [3. Store Data](#3-store-data)
    - [4. Update Data](#4-update-data)
    - [5. Delete Data](#5-delete-data)

## Introduction
User Api implementation backend has done by the Spring Boot framework.
It's about how a user can register and Authenticate through a JWT token and how can a user access the data provided the respective user.

## Technologies

Listing the technologies and frameworks used in the project.

- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Database used MySQL 

## Database Schema

```
User Table:
- id (Primary Key)
- username (Unique)
- email (Unique)
- password (Hashed)
- full_name
- age
- gender

Store Data:
-key_id(Primary Key)
-value
```

## Getting Started

Instructions on how to set up the project locally for development and testing.

### Prerequisites

List of software and tools required to run the project locally.

- Java 11 or higher
- Maven
- Database: MySQL

### Installation

Step-by-step instructions on how to install and run the project:

1. Clone the repository to your local machine:

```bash
git clone https://github.com/your-username/your-repository.git
cd your-repository
```

2. Build the project using Maven:

```bash
mvn clean install
```

3. Set up the database schema and connection:

   - Create a database in your chosen database server MySQL
   - Update the database configuration in `application.properties` with the correct database URL, username, and password.

4. Run the application:

```bash
mvn spring-boot:run
or
You can open the Spring Boot Main Application and can run the applicationfrom there.
```

The application should now be running on `http://localhost:8080`.

## API Endpoints

Listing and explaining each API endpoint with sample request and response data.

### 1. User Registration

- **Endpoint**: `POST /api/register`
- **Request**:
  ```json
  {
    "username": "example_user",
    "email": "user@example.com",
    "password": "secure_password123",
    "full_name": "John Doe",
    "age": 30,
    "gender": "male"
  }
  ```
- **Success Response**:
  ```json
  {
    "status": "success",
    "message": "User successfully registered!",
    "data": {
      "user_id": "12345",
      "username": "example_user",
      "email": "user@example.com",
      "full_name": "John Doe",
      "age": 30,
      "gender": "male"
    }
  }
  ```
- **Error Response**:
  ```json
  {
    "status": "error",
    "code": "INVALID_REQUEST",
    "message": "Invalid request. Please provide all required fields: username, email, password, full_name."
  }
  ```

### 2. Generate Token

- **Endpoint**: `POST /api/token`
- **Request**:
  ```json
  {
    "username": "example_user",
    "password": "secure_password123"
  }
  ```
- **Response**:
  ```json
  {
    "status": "success",
    "message": "Access token generated successfully.",
    "data": {
      "access_token": "<TOKEN>",
      "expires_in": 3600
    }
  }
  ```

For Accessing the data you need to use Authentication header to store the required token and token will be starts with "Bearer token"
### 3. Store Data

- **Endpoint**: `POST /api/data`
- **Request**:
  ```json
  {
    "key": "unique_key",
    "value": "data_value"
  }
  ```
- **Response**:
  ```json
  {
    "status": "success",
    "message": "Data stored successfully."
  }
  ```

### 4. Update Data

- **Endpoint**: `PUT /api/data/{key}`
- **Request**:
  ```json
  {
    "value": "updated_data_value"
  }
  ```
- **Response**:
  ```json
  {
    "status": "success",
    "message": "Data updated successfully."
  }
  ```

### 5. Delete Data

- **Endpoint**: `DELETE /api/data/{key}`
- **Response**:
  ```json
  {
    "status": "success",
    "message": "Data deleted successfully."
  }
  ```


