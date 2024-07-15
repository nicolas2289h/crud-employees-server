<!-- ABOUT THE PROJECT -->
## About The Project
## CRUD Employees Server
This project is the backend for an employee CRUD (Create, Read, Update, Delete) application. It provides RESTful APIs to manage employee data, including creating, retrieving, updating, and deleting employee records.

## Features

- **Create**: Add new employees.
- **Read**: Retrieve employee details.
- **Update**: Modify existing employee information.
- **Delete**: Remove employee records.

## Technologies

- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL** (or any other preferred relational database)
- **Maven** (for dependency management)

## Setup and Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/nicolas2289h/crud-employees-server
    cd crud-employees-server
    ```

2. **Configure the database**:
    - Ensure you have MySQL installed and running.
    - Add your database configuration:
     ```sh
     DB_HOST=your-database-host
     DB_USER=your-database-user
     DB_PASSWORD=your-database-password
     DB_NAME=your-database-name
     DB_PORT=your-database-port
     ```

3. **Run the application**:
    ```bash
    ./mvnw spring-boot:run
    ```

4. **Access the application**:
    - The application will start on `http://localhost:8080`.

## Usage

Once the server is running, you can interact with the API using tools like Postman or cURL. You can also integrate it with a frontend application to provide a complete employee management system.

## API Endpoints

Here are some of the main endpoints provided by this application:

- **GET** `/api/employees`: Retrieve a list of employees.
- **GET** `/api/employees/{id}`: Retrieve a specific employee by ID.
- **POST** `/api/employee`: Create a new employee.
- **PUT** `/api/employee/{id}`: Update an existing employee.
- **DELETE** `/api/employee/{id}`: Delete an employee by ID.
