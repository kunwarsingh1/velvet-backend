# Testing the Authentication System

## Prerequisites
- Java 17+ installed
- Application running on port 8080
- curl or Postman for API testing

## Step 1: Start the Application

### Option A: Using Maven (if available)
```bash
mvn spring-boot:run
```

### Option B: Using the batch file
```bash
start-app.bat
```

### Option C: Manual compilation and run
```bash
mvn clean package
java -jar target/Velvet-0.0.1-SNAPSHOT.jar
```

## Step 2: Test Authentication APIs

### Quick Test (using batch file)
```bash
test-auth-apis.bat
```

### Manual Testing

#### 1. Register a User
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "Test@123456"
  }'
```

Expected Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "type": "Bearer",
  "userId": "1234",
  "username": "testuser",
  "email": "test@example.com",
  "role": "USER",
  "expiresAt": "2024-01-02T12:00:00Z"
}
```

#### 2. Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "Test@123456"
  }'
```

#### 3. Test Protected Endpoint (copy JWT token from login)
```bash
curl -X GET http://localhost:8080/api/auth/profile \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE"
```

#### 4. Test NetWorth Endpoint (requires authentication)
```bash
curl -X GET http://localhost:8080/api/networth/1234 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE"
```

## Step 3: Test Role-Based Access

#### 1. Try Admin Endpoint with USER role (should fail)
```bash
curl -X GET http://localhost:8080/api/admin/users \
  -H "Authorization: Bearer USER_JWT_TOKEN"
```

Expected: 403 Forbidden

#### 2. Create Admin User (manually update role in database)
Access H2 Console: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: password

Update user role:
```sql
UPDATE users SET role = 'ADMIN' WHERE email = 'admin@example.com';
```

## Step 4: Swagger UI Testing

Visit: http://localhost:8080/swagger-ui.html

1. Click "Authorize" button
2. Enter: `Bearer YOUR_JWT_TOKEN`
3. Test all endpoints interactively

## Troubleshooting

### Port Already in Use
```bash
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

### Database Issues
- Check H2 console: http://localhost:8080/h2-console
- Verify user table exists and has correct schema

### JWT Token Issues
- Ensure token is not expired (24 hours default)
- Check Authorization header format: `Bearer <token>`
- Verify token in JWT debugger: https://jwt.io

## Expected Behavior

✅ **Registration**: Creates user with hashed password and USER role
✅ **Login**: Returns JWT token with user details
✅ **Protected Endpoints**: Require valid JWT token
✅ **Role-Based Access**: ADMIN endpoints reject USER tokens
✅ **Validation**: Proper error messages for invalid input
✅ **Security**: Passwords hashed, tokens signed, CORS enabled