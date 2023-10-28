# API Documentation

This document outlines the API endpoints, request/response formats, and schemas defined in the OpenAPI specification.

## Base URL
Base URL for API requests: `http://localhost:8080`

## Endpoints

### Products

#### `GET /api/products/{productId}`
- Description: Retrieve product details by ID.
- Request: Fetches product information based on the provided ID.
- Response: Returns the product details.

#### `PUT /api/products/{productId}`
- Description: Update product information by ID.
- Request: Modifies product details identified by the provided ID.
- Response: Returns the updated product information.

#### `DELETE /api/products/{productId}`
- Description: Delete a product by ID.
- Request: Deletes the product associated with the given ID.
- Response: Confirms successful deletion of the product.

#### `POST /api/products/{productId}/add-feature`
- Description: Add a feature to a product.
- Request: Attaches a feature to the product specified by ID.
- Response: Returns the updated product information after adding the feature.

#### `POST /api/products/add`
- Description: Add a new product.
- Request: Creates a new product with provided details.
- Response: Returns the newly created product information.

#### `GET /api/products/list`
- Description: Retrieve a list of all products.
- Response: Returns an array containing all available products.

### Parameters

#### `PUT /api/parameters/{parameterId}`
- Description: Update parameter details by ID.
- Request: Modifies parameter details based on the provided ID.
- Response: Returns the updated parameter information.

#### `DELETE /api/parameters/{parameterId}`
- Description: Delete a parameter by ID.
- Request: Deletes the parameter identified by the given ID.
- Response: Confirms successful deletion of the parameter.

#### `POST /api/parameters/add`
- Description: Add a new parameter.
- Request: Creates a new parameter with provided details.
- Response: Returns the newly created parameter information.

### Features

#### `GET /api/features/{featureId}`
- Description: Retrieve feature details by ID.
- Request: Fetches feature information based on the provided ID.
- Response: Returns the feature details.

#### `PUT /api/features/{featureId}`
- Description: Update feature information by ID.
- Request: Modifies feature details identified by the provided ID.
- Response: Returns the updated feature information.

#### `DELETE /api/features/{featureId}`
- Description: Delete a feature by ID.
- Request: Deletes the feature associated with the given ID.
- Response: Confirms successful deletion of the feature.

#### `POST /api/features/{featureId}/add-parameter`
- Description: Add a parameter to a feature.
- Request: Attaches a parameter to the feature specified by ID.
- Response: Returns the updated feature information after adding the parameter.

#### `POST /api/features/add`
- Description: Add a new feature.
- Request: Creates a new feature with provided details.
- Response: Returns the newly created feature information.

### Authentication

#### `POST /api/auth/token`
- Description: Generate authentication token.
- Request: Validates credentials and generates a token for authentication.
- Response: Returns the authentication token.

#### `POST /api/auth/signup`
- Description: Register a new user.
- Request: Registers a new user with provided credentials.
- Response: Confirms successful user registration.

## Schemas

### Feature Schema
- `featureId`: Unique identifier for the feature.
- `name`: Name of the feature.
- `internalName`: Internal name of the feature.
- `details`: Description/details about the feature.
- `parameters`: List of parameters associated with the feature.
- `product_id`: Unique identifier (deprecated, use `featureId`).

### Parameter Schema
- `parameterId`: Unique identifier for the parameter.
- `name`: Name of the parameter.
- `internalName`: Internal name of the parameter.
- `details`: Description/details about the parameter.
- `parameterType`: Type of the parameter (QUANTITY, PRICE, OTHER).
- `values`: Value(s) associated with the parameter.
- `id`: Unique identifier (deprecated, use `parameterId`).

### Product Schema
- `id`: Unique identifier for the product.
- `name`: Name of the product.
- `internalName`: Internal name of the product.
- `details`: Description/details about the product.
- `maxProductsPerLocation`: Maximum number of products per location.
- `features`: List of features associated with the product.

### LoginBody Schema
- `username`: Username for authentication.
- `password`: Password for authentication.

### JwtResponse Schema
- `token`: Authentication token.
- `username`: Username of the authenticated user.
- `role`: Role of the authenticated user.

### User Schema
- `id`: Unique identifier for the user.
- `name`: Name of the user.
- `password`: User's password.
- `role`: Role of the user.

