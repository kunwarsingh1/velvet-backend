# Authentication API - Curl Commands & Responses

## 1. User Registration

**Request:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "Test@123456"
  }'
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ0ZXN0QGV4YW1wbGUuY29tIiwiaWF0IjoxNzYyMjc2MjM2LCJleHAiOjE3NjIzNjI2MzZ9.gSdhwSezJvi0vcpObBxDROnUTD5tvUpYl0yyjJSowXBXaI2YrJ_RE5N8yAsAy7i0",
  "type": "Bearer",
  "userId": "7651",
  "username": "testuser",
  "email": "test@example.com",
  "expiresAt": "2025-11-05T17:10:36Z"
}
```

## 2. User Login

**Request:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "Test@123456"
  }'
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ0ZXN0QGV4YW1wbGUuY29tIiwiaWF0IjoxNzYyMjc2MjQ2LCJleHAiOjE3NjIzNjI2NDZ9.7GnGxhYmp_1bMFNZyD1OAwawdow1kir6AizLrb5YIRskyQF_hE8bSk1_OqLrhESM",
  "type": "Bearer",
  "userId": "7651",
  "username": "testuser",
  "email": "test@example.com",
  "expiresAt": "2025-11-05T17:10:46Z"
}
```

## 3. Get User Profile (Protected)

**Request:**
```bash
curl -X GET http://localhost:8080/api/auth/profile \
  -H "Authorization: Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ0ZXN0QGV4YW1wbGUuY29tIiwiaWF0IjoxNzYyMjc2MjQ2LCJleHAiOjE3NjIzNjI2NDZ9.7GnGxhYmp_1bMFNZyD1OAwawdow1kir6AizLrb5YIRskyQF_hE8bSk1_OqLrhESM"
```

**Response:**
```json
{
  "id": "7651",
  "username": "testuser",
  "email": "test@example.com",
  "name": null,
  "city": null,
  "mobileNumber": null,
  "dateOfBirth": null,
  "retirementYear": null,
  "createdAt": "2025-11-04T17:10:36.148428Z"
}
```

## 4. Get Net Worth (Protected)

**Request:**
```bash
curl -X GET http://localhost:8080/api/networth/7651 \
  -H "Authorization: Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ0ZXN0QGV4YW1wbGUuY29tIiwiaWF0IjoxNzYyMjc2MjQ2LCJleHAiOjE3NjIzNjI2NDZ9.7GnGxhYmp_1bMFNZyD1OAwawdow1kir6AizLrb5YIRskyQF_hE8bSk1_OqLrhESM"
```

**Response:**
```json
{
  "totalAssets": 0.0,
  "totalLiabilities": 0.0,
  "netWorth": 0.0
}
```

## 5. Logout (Protected)

**Request:**
```bash
curl -X POST http://localhost:8080/api/auth/logout \
  -H "Authorization: Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ0ZXN0QGV4YW1wbGUuY29tIiwiaWF0IjoxNzYyMjc2MjQ2LCJleHAiOjE3NjIzNjI2NDZ9.7GnGxhYmp_1bMFNZyD1OAwawdow1kir6AizLrb5YIRskyQF_hE8bSk1_OqLrhESM"
```

**Response:**
```json
{
  "message": "Logged out successfully"
}
```

## Error Responses

### 6. Unauthorized Access (No Token)

**Request:**
```bash
curl -X GET http://localhost:8080/api/auth/profile
```

**Response:**
```json
{
  "error": "Access denied",
  "type": "AuthorizationError"
}
```

### 7. Validation Error (Weak Password)

**Request:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "test2",
    "email": "test2@example.com",
    "password": "weak"
  }'
```

**Response:**
```json
{
  "type": "ValidationError",
  "details": {
    "password": "Password must be between 8 and 100 characters"
  },
  "error": "Validation failed"
}
```

### 8. Duplicate Email Error

**Request:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "duplicate",
    "email": "test@example.com",
    "password": "Test@123456"
  }'
```

**Response:**
```json
{
  "error": "Email is already in use",
  "type": "AuthenticationError"
}
```

### 9. Invalid Login Credentials

**Request:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "wrongpassword"
  }'
```

**Response:**
```json
{
  "error": "Invalid email or password",
  "type": "AuthenticationError"
}
```

## Notes:
- Replace JWT tokens with actual tokens from login/register responses
- All protected endpoints require `Authorization: Bearer <token>` header
- Tokens expire in 24 hours (86400 seconds)
- Server runs on http://localhost:8080