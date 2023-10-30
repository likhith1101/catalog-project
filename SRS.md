# Catalog Project

Catalog Project is a web-based catalog management system that allows users to manage products, features, and parameters pages. It provides user authentication, CRUD operations for catalog entities, and a hierarchical tree structure view in the summary page.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Features](#features)
- [API Documentation](#api-documentation)
- [Unit Testing](#unit)



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
  * Hierarchy View: View the hierarchy of products, features, and parameters in hierarichal view.

## API Documentation

API documentation is available using Swagger. Access the Swagger UI to test and explore the APIs:
  - Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui/index.html#/)

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
   ./mvnw test
   




