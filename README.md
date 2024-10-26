# 🏆 Welcome to eSportiva! 🏆

## Hello, eSports Enthusiasts! 👋

Welcome to **eSportiva**, a comprehensive web-based application designed to manage eSports tournaments efficiently. **eSportiva** leverages modern technologies like **Java, Spring**, and **Hibernate** to provide a robust and scalable platform for handling tournaments, teams, and games.

## 🚀 About eSportiva

**eSportiva** enables organizers to manage tournaments, teams, and games through an intuitive web interface. With **CRUD** functionality for tournaments, teams, and games, **eSportiva** ensures a seamless experience for managing eSports events.

## 📁 Project Structure

Here's an overview of the project structure for **eSportiva**:

- `controllers`: Handles HTTP requests for managing tournaments, teams, and games.
- `dto`: Data Transfer Objects facilitating data transfer between layers.
- `models`: Classes representing core entities: `Tournament`, `Team`, `Game`, and associated enums.
- `repositories`: Managing database queries.
- `services`: Business logic layer for managing tournaments, teams, and games.
- `utils`: Utility classes for validation and other common tasks.
- `resources`: Contains configuration files such as `application.properties` and database setup scripts.

## 🧩 Key Features

- **Tournament Management**: Full CRUD functionality for creating, updating, and deleting tournaments.
- **Team Management**: Manage teams participating in tournaments.
- **Game Management**: Manage games associated with tournaments.
- **Secure Data Handling**: Ensures data integrity and security.
- **Unit Tests**: Using **JUnit** and **Mockito** to test business and data access components.

## 🌐 Web Application Pages

### Tournament Management
- **Tournament Listing**: View a list of all tournaments.
- **CRUD Operations**: Create, update, and delete tournaments.

### Team Management
- **Team Listing**: View a list of all teams.
- **CRUD Operations**: Create, update, and delete teams.

### Game Management
- **Game Listing**: View a list of all games.
- **CRUD Operations**: Create, update, and delete games.

## 🎯 Project Objectives

- Develop a robust system for managing eSports tournaments.
- Implement CRUD operations for tournaments, teams, and games.
- Use **JUnit** and **Mockito** for unit testing.
- Maintain clear separation of concerns with an MVC architecture.

## 🛠️ How to Use eSportiva

### Prerequisites

Before running **eSportiva**, ensure you have the following installed:

- **Java 8** or later
- **Maven** for project build and dependency management
- **MySQL** or any other relational database

### Running the Application

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/zinebMachrouh/eSportiva.git
   cd eSportiva
   ```

2. Update the `applicationContext` file in the `resources` directory with your database connection details.

3. Build and run the application using Maven:
   ```bash
   mvn clean install
   ```

## 🎉 Get Started with eSportiva Today!

For any questions, feedback, or suggestions, feel free to reach out to us. 📧**