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
   mvn spring-boot:run
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

## Author
  Gaddam Likhitheshwar


