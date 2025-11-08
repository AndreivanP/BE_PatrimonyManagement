# Patrimony Management API Documentation

## Base URL
`http://your-domain.com` (or `http://localhost:8080` for local development)

## Authentication
All endpoints except `/authenticate` require a valid JWT token in the `Authorization` header.

### 1. Authentication

#### Authenticate User
- **Endpoint:** `POST /authenticate`
- **Request Body:**
  ```json
  {
    "username": "string",
    "password": "string"
  }
  ```
- **Response:**
  ```json
  {
    "token": "jwt.token.here"
  }
  ```

#### Refresh Token
- **Endpoint:** `GET /refresh`
- **Headers:**
  - `Authorization: Bearer <current_token>`
- **Response:** New JWT token

## 2. Assets

### Get All Assets
- **Endpoint:** `GET /users/{username}/assets`
- **Response:** List of all assets for the specified user

### Get Asset by ID
- **Endpoint:** `GET /users/{username}/assets/{id}`
- **Response:** Asset details

### Get Current Total Value
- **Endpoint:** `GET /users/{username}/assets/current-total`
- **Response:** 
  ```json
  {
    "total": 10000.0
  }
  ```

### Create Asset
- **Endpoint:** `POST /users/{username}/assets`
- **Request Body:** Asset object
- **Response:** Created asset with ID

### Update Asset
- **Endpoint:** `PUT /users/{username}/assets/{id}`
- **Request Body:** Updated asset object
- **Response:** Updated asset

### Delete Asset
- **Endpoint:** `DELETE /users/{username}/assets/{id}`
- **Response:** 200 OK on success

## 3. Asset Control

### Create Asset Control
- **Endpoint:** `POST /users/{username}/assets-control`
- **Response:**
  ```json
  [
    {
        "id": "619ce740f74f1a4a8b82d820",
        "controlDate": "2018-12-02T00:00:00.000+0000",
        "currentTotalValue": 120000.0,
        "username": "Andreivan"
    }
  ]
  ```

### Create Custom Asset Control
- **Endpoint:** `POST /users/{username}/assets-control-custom`
- **Request Body:** AssetControl object
- **Response:** Created asset control

### Get All Asset Controls
- **Endpoint:** `GET /users/{username}/assets-control`
- **Query Parameters:**
  - `since` (optional): Filter controls after this date
  - `till` (optional): Filter controls before this date
- **Response:** List of asset controls
  ```json
    [
    {
        "id": "5fdfb26c9f43e0458cd276dd",
        "name": "Tesouro Direto",
        "date": "2024-03-07T00:00:00.000+0000",
        "initial_value": 62739.77,
        "company": "Easynvest - Andreivan",
        "interest_rate": 0.0,
        "is_active": true,
        "username": "Andreivan",
        "current_value": 286000.0,
        "is_variable_income": false,
        "expiryDate": null
    },
    {
        "id": "638e5dac76b1e0106ae9c7d0",
        "name": "Avenue",
        "date": "2023-12-22T00:00:00.000+0000",
        "initial_value": 50000.0,
        "company": "Avenue",
        "interest_rate": 0.0,
        "is_active": true,
        "username": "Andreivan",
        "current_value": 74000.0,
        "is_variable_income": false,
        "expiryDate": null
    },
    {
        "id": "5f8c97db0b63b42d5a934a1c",
        "name": "Equitas Selection FIC FIA",
        "date": "2020-12-31T00:00:00.000+0000",
        "initial_value": 12031.65,
        "company": "Easynvest - Andreivan",
        "interest_rate": 0.0,
        "is_active": true,
        "username": "Andreivan",
        "current_value": 6000.0,
        "is_variable_income": true,
        "expiryDate": null
    },
    {
        "id": "5f8ca15d0b63b42d5a934a4a",
        "name": "Poupanca",
        "date": "2022-09-02T00:00:00.000+0000",
        "initial_value": 1200.0,
        "company": "Caixa",
        "interest_rate": 0.0,
        "is_active": true,
        "username": "Andreivan",
        "current_value": 358.0,
        "is_variable_income": false,
        "expiryDate": null
    },
    {
        "id": "5f8c9c310b63b42d5a934a3c",
        "name": "NuConta",
        "date": "2024-03-07T00:00:00.000+0000",
        "initial_value": 10820.91,
        "company": "Nubank",
        "interest_rate": 100.0,
        "is_active": true,
        "username": "Andreivan",
        "current_value": 108000.0,
        "is_variable_income": false,
        "expiryDate": null
    },
    {
        "id": "5f8c97280b63b42d5a934a1a",
        "name": "Stocks && IVVB11 && HGLG11",
        "date": "2023-08-06T00:00:00.000+0000",
        "initial_value": 63000.0,
        "company": "Clear Corretora",
        "interest_rate": 0.0,
        "is_active": true,
        "username": "Andreivan",
        "current_value": 84000.0,
        "is_variable_income": true,
        "expiryDate": null
    },
    {
        "id": "62ad90f6caad313f1845ded0",
        "name": "FGTS Caixa",
        "date": "2023-02-04T00:00:00.000+0000",
        "initial_value": 25000.0,
        "company": "Caixa",
        "interest_rate": 0.0,
        "is_active": true,
        "username": "Andreivan",
        "current_value": 23000.0,
        "is_variable_income": false,
        "expiryDate": null
    },
    {
        "id": "5fdfb41e9f43e0458cd276e2",
        "name": "Renda Fixa Privada",
        "date": "2024-03-07T00:00:00.000+0000",
        "initial_value": 230012.0,
        "company": "Easynvest - Andreivan",
        "interest_rate": 0.0,
        "is_active": true,
        "username": "Andreivan",
        "current_value": 378000.0,
        "is_variable_income": false,
        "expiryDate": null
    },
    {
        "id": "642d82bf69a65278a3c8c71e",
        "name": "Sicredi",
        "date": "2024-03-07T00:00:00.000+0000",
        "initial_value": 170000.0,
        "company": "Sicredi PF",
        "interest_rate": 100.0,
        "is_active": true,
        "username": "Andreivan",
        "current_value": 192000.0,
        "is_variable_income": false,
        "expiryDate": null
    },
    {
        "id": "631734407d5c47340cbc4933",
        "name": "Wilson",
        "date": "2022-09-06T00:00:00.000+0000",
        "initial_value": 20000.0,
        "company": "",
        "interest_rate": 1.3,
        "is_active": true,
        "username": "Andreivan",
        "current_value": 23000.0,
        "is_variable_income": false,
        "expiryDate": "2023-02-10T00:00:00.000+0000"
    },
    {
        "id": "63724b02c8efaf5e748e34a3",
        "name": "Cruze",
        "date": "2022-11-14T00:00:00.000+0000",
        "initial_value": 93000.0,
        "company": "carro",
        "interest_rate": 0.0,
        "is_active": true,
        "username": "Andreivan",
        "current_value": 91000.0,
        "is_variable_income": false,
        "expiryDate": null
    },
    {
        "id": "6004573addab4c078d76d41a",
        "name": "Bitcoin",
        "date": "2024-03-07T00:00:00.000+0000",
        "initial_value": 8000.0,
        "company": "Mercado Bitcoin",
        "interest_rate": 0.0,
        "is_active": true,
        "username": "Andreivan",
        "current_value": 10000.0,
        "is_variable_income": true,
        "expiryDate": null
    },
    {
        "id": "61c06dd29edd5277e6710937",
        "name": "Nubank BDR",
        "date": "2021-12-08T00:00:00.000+0000",
        "initial_value": 1500.0,
        "company": "Nubank",
        "interest_rate": 0.0,
        "is_active": true,
        "username": "Andreivan",
        "current_value": 1100.0,
        "is_variable_income": true,
        "expiryDate": null
    },
    {
        "id": "642d870769a65278a3c8c724",
        "name": "Sicredi",
        "date": "2024-03-07T00:00:00.000+0000",
        "initial_value": 80000.0,
        "company": "Sicredi - pai",
        "interest_rate": 103.0,
        "is_active": true,
        "username": "Andreivan",
        "current_value": 82000.0,
        "is_variable_income": false,
        "expiryDate": "2024-08-05T00:00:00.000+0000"
    },
    {
        "id": "63e7f8a5d54f5b07f10284dc",
        "name": "Nuinvest",
        "date": "2023-03-03T00:00:00.000+0000",
        "initial_value": 105000.0,
        "company": "Nuinvest Mae",
        "interest_rate": 100.0,
        "is_active": true,
        "username": "Andreivan",
        "current_value": 124000.0,
        "is_variable_income": false,
        "expiryDate": null
    }
   ]
  ```

### Get Asset Control by ID
- **Endpoint:** `GET /assets-control/{id}`
- **Response:** Asset control details

### Update Asset Control
- **Endpoint:** `PUT /assets-control/{id}`
- **Request Body:** Updated asset control
- **Response:** Updated asset control

### Delete Asset Control
- **Endpoint:** `DELETE /assets-control/{id}`
- **Response:** 200 OK on success

## Error Responses

### 401 Unauthorized
- Missing or invalid JWT token

### 404 Not Found
- Requested resource not found

### 500 Internal Server Error
- Server-side error occurred

## CORS
All endpoints support CORS from any origin (`*`).

## Rate Limiting
Not currently implemented.

## Versioning
Current API version: v1
