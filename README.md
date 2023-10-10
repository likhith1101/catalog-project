# Catalog Project

Catalog Project is a web-based catalog management system that allows users to manage products, features, and parameters in a structured manner. It provides user authentication, CRUD operations for catalog entities, and a hierarchical tree structure view.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Features](#features)
- [API Documentation](#api-documentation)


## Technologies Used

- Frontend:
  - Angular 16
  - HTML5
- Backend:
  - Java with Spring Boot
- Database:
  - PostgreSQL
- Middleware:
  - Spring Boot
- Server:
  - Embedded Tomcat
- Build Tool:
  - Maven

## Getting Started

To get started with the Catalog Project, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/likhith1101/catalog-project.git
2. Set up the frontend:
   ```bash
   cd frontend
   npm install
   ng serve
3. Set up the backend:
   ```bash
   cd backend
   ./mvnw clean
   ./mvnw install
4. Access the application:
   * Frontend: [http://localhost:4200](#4200link)
   * Backend: [http://localhost:8080](#8080link)

## Project Structure

The project structure is organized as follows:
  * frontend/: Contains the Angular frontend code.
  * backend/: Contains the Spring Boot backend code.
  * docs/: Documentation and resources.
  * sql/: Database schema and initialization scripts.

## Features

  * User Authentication: Register, log in, and log out.
  * Product Management: Add, edit, and delete products with fields for Name, Internal Name, and Details.
  * Feature Management: Add, edit, and delete features under products with fields for Name, Internal Name, and Details.
  * Parameter Management: Add, edit, and delete parameters under features with fields for Name, Internal Name, Parameter Type, and Details (limited to quantity, price, or other).
  * Hierarchy View: View the hierarchy of products, features, and parameters in a tree structure.

## API Documentation

API documentation is available using Swagger. Access the Swagger UI to test and explore the APIs:
  - Swagger UI: [http://localhost:8080/swagger-ui.html](#swagger)

## Unit Testing

We follow a robust unit testing approach to ensure the reliability and correctness of our code. Our unit tests cover the following components:

- Component 1: Description of what this component does.
  - Provide specific details about the tests conducted for this component.
- Component 2: Description of what this component does.
  - Provide specific details about the tests conducted for this component.
  
### Running Unit Tests

To run the unit tests locally, follow these steps:

1. Navigate to the project's root directory.
2. Open a terminal and run the following command:

   ```bash
   mvn test
   
## UX Testing

User experience (UX) testing is a crucial aspect of our project to ensure a seamless and user-friendly interface. We conduct UX testing to gather feedback and make improvements in the following areas:

- **Usability:** Ensuring the application is intuitive and easy to navigate.
- **Accessibility:** Confirming the application meets accessibility standards.
- **Performance:** Testing the application's responsiveness and load times.
- **Compatibility:** Ensuring compatibility across various browsers and devices.

### How We Conduct UX Testing

We follow a structured approach to UX testing:

1. **Test Scenarios:** We create test scenarios that reflect real-world user interactions with the application. These scenarios cover typical user tasks and workflows.

2. **Test Participants:** We select a diverse group of test participants representing our target audience. This includes individuals with different backgrounds, skills, and abilities.

3. **Testing Environment:** Testing is conducted in a controlled environment to monitor user interactions and gather feedback effectively.

4. **Recording Feedback:** We use screen recording and user feedback forms to capture user actions, comments, and suggestions during the testing process.



