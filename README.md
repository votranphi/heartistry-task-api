# Heartistry Backend Task API

![Repo Size](https://img.shields.io/github/repo-size/votranphi/heartistry-task-api)
![Last Commit](https://img.shields.io/github/last-commit/votranphi/heartistry-task-api)
![Open Issues](https://img.shields.io/github/issues/votranphi/heartistry-task-api)
![License](https://img.shields.io/github/license/votranphi/heartistry-task-api)

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-green)
![OpenAPI](https://img.shields.io/badge/OpenAPI-Enabled-blue)

This repository contains the Spring Boot backend API for the Hearistry website, a web application for learning English through Flashcards. This backend service is responsible for managing WordSets, Words, and Documents, as well as handling authentication and logging functionalities.

## Features

- **WordSet Management**: Store and manage information about users' vocabulary sets.
- **Word Management**: Store and manage information about individual words in the WordSets.
- **Document Management**: Store and manage documents for extended learning materials.
- **JWT Authentication**: Validate JSON Web Tokens (JWTs) created by the NestJS backend for secure authentication.
- **Audit Logs**: Maintain logs for auditing user actions and application events.

## Related Projects

This backend is part of the **Heartistry** application. You can find the related repositories here:

- [Heartistry Frontend](https://github.com/votranphi/heartistry): The web-based user interface, built with React and Vite.
- [Heartistry Backend User/Auth API](https://github.com/votranphi/heartistry-user-api): The backend API built with NestJS that handles authentication, email verification, and user management.
- [Heartistry Backend Task API (this repo)](https://github.com/votranphi/heartistry-task-api): A Spring Boot service that manages tasks and metrics.

The Heartistry application is designed for users to create, manage, and learn from custom vocabulary Wordsets via Flashcards. Check out the main frontend repository to explore how this backend integrates with the user interface.

## Project Structure

- **Controllers**: Handle incoming HTTP requests and route them to the appropriate services.
- **Services**: Contain the business logic for managing WordSets, Words, Documents, and other features.
- **Repositories**: Manage interactions with the database for storing and retrieving data.
- **JWT Validation**: Secure authentication with JWT validation shared between this service and the NestJS project.

## Technologies Used

- **Spring Boot**: Backend framework for building RESTful APIs.
- **MySQL**: Relational database for storing data.
- **Spring Security**: For handling authentication and JWT validation.
- **Hibernate**: ORM for managing database entities.

## Prerequisites

Before running the project, ensure you have the following installed:

- Java 11 or later
- Maven
- MySQL Server

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/votranphi/hearistry-task-api.git
   cd hearistry-task-api
   ```

2. Set up environment variables:
   - Create a `application.properties` file in `\heartistry-task-api\src\main\resources` of the project.
   - Add the following configuration (update the values as needed):
    ```env
    # Mail Configuration
    spring.application.name=heartistry-task-api

    # Azure MySQL configuration
    spring.datasource.url=jdbc:mysql://<database-ip>:<database-port>/<database-name>
    spring.datasource.username=<database-username>
    spring.datasource.password=<database-password>
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    # Enable SQL logging
    spring.jpa.show-sql=true
    spring.jpa.hibernate.ddl-auto=update

    # swagger-ui custom path
    springdoc.swagger-ui.path=/swagger-ui.html

    # jwt configuration
    jwt.secret=<jwt-secret>

    # change the expose port
    server.port=8000
     ```

3. Run the project:
   ```bash
   mvn spring-boot:run
   ```

The API will be available at `http://localhost:8000`.

## Contributing

Contributions are welcome! Please follow the steps below to contribute:

1. Fork the repository.
2. Create a new branch for your feature:
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add your message here"
   ```
4. Push to your branch:
   ```bash
   git push origin feature-name
   ```
5. Open a pull request.

## License

This project is licensed under the [Apache License 2.0](LICENSE). See the `LICENSE` file for details.

## Credits
Contributors:
- Vo Tran Phi (Student ID: 22521081)  
Github link: [votranphi](https://github.com/votranphi) 
- Le Duong Minh Phuc (Student ID: 22521116)  
Github link: [minhphuc2544](https://github.com/minhphuc2544)