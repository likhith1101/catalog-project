# API Documentation

## Overview

This document outlines the API endpoints, their descriptions, supported methods, request parameters, request bodies, and responses for various operations related to products, features, parameters, and authentication.

## Base URL

All endpoints are based on the following base URL:

- Base URL: `http://localhost:8080`

## Endpoints

### Product Endpoints

#### Get Product by ID

- Method: `GET`
- Endpoint: `/api/products/{productId}`
- Description: Retrieves product information by ID.
- Request Parameters: `productId` (integer)
- Response: Product object

#### Update Product

- Method: `PUT`
- Endpoint: `/api/products/{productId}`
- Description: Updates product details.
- Request Parameters: `productId` (integer)
- Request Body: Product object
- Response: Updated Product object

#### Delete Product

- Method: `DELETE`
- Endpoint: `/api/products/{productId}`
- Description: Deletes a product by ID.
- Request Parameters: `productId` (integer)
- Response: Confirmation message

#### Add Feature to Product

- Method: `POST`
- Endpoint: `/api/products/{productId}/add-feature`
- Description: Adds a feature to a product.
- Request Parameters: `productId` (integer)
- Request Body: Feature object
- Response: Updated Product object

#### Add New Product

- Method: `POST`
- Endpoint: `/api/products/add`
- Description: Adds a new product.
- Request Body: Product object
- Response: Newly added Product object

#### Get Features by Product ID

- Method: `GET`
- Endpoint: `/api/products/{productId}/features`
- Description: Retrieves features associated with a product by ID.
- Request Parameters: `productId` (integer)
- Response: Array of Feature objects

#### Get All Products

- Method: `GET`
- Endpoint: `/api/products/list`
- Description: Retrieves a list of all products.
- Response: Array of Product objects

### Parameter Endpoints

#### Update Parameter

- Method: `PUT`
- Endpoint: `/api/parameters/{parameterId}`
- Description: Updates parameter details.
- Request Parameters: `parameterId` (integer)
- Request Body: Parameter object
- Response: Updated Parameter object

#### Delete Parameter

- Method: `DELETE`
- Endpoint: `/api/parameters/{parameterId}`
- Description: Deletes a parameter by ID.
- Request Parameters: `parameterId` (integer)
- Response: Confirmation message

#### Add New Parameter

- Method: `POST`
- Endpoint: `/api/parameters/add`
- Description: Adds a new parameter.
- Request Body: Parameter object
- Response: Newly added Parameter object

#### Get All Parameters

- Method: `GET`
- Endpoint: `/api/parameters/list`
- Description: Retrieves a list of all parameters.
- Response: Array of Parameter objects

### Feature Endpoints

#### Get Feature by ID

(Other feature endpoints will follow a similar structure to product and parameter endpoints, performing operations specific to features.)

### Authentication Endpoints

(Endpoints related to user authentication and authorization.)

## Schemas

### Product

(Definition of a product object and its properties.)

### Feature

(Definition of a feature object and its properties.)

### Parameter

(Definition of a parameter object and its properties.)

### LoginBody

(Structure of the request body for user login.)

### JwtResponse

(Response structure for JWT token generation.)

### User

(Definition of a user object and its properties.)

---

This API documentation outlines the endpoints, request methods, expected parameters, request bodies, and responses for various operations related to products, features, parameters, and user authentication.
