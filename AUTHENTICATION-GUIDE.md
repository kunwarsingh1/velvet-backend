# Authentication & Authorization System

This Spring Boot application includes a complete JWT-based authentication and authorization system.

## Features

- **User Registration & Login**: Secure user registration with email validation and password strength requirements
- **JWT Authentication**: Stateless authentication using JSON Web Tokens
- **Role-Based Access Control**: Support for USER and ADMIN roles
- **Password Security**: BCrypt password hashing
- **Method-Level Security**: @PreAuthorize annotations for fine-grained access control
- **Exception Handling**: Comprehensive error handling for authentication failures
- **Swagger Integration**: API documentation with JWT authentication support

## API Endpoints

### Authentication Endpoints (Public)
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - User login

### Protected Endpoints (Requires Authentication)
- `GET /api/auth/profile` - Get current user profile
- `POST /api/auth/logout` - Logout user
- `GET /api/networth/{userId}` - Get net worth (USER/ADMIN)
- `GET /api/admin/users` - Get all users (ADMIN only)

## Usage

### 1. Register a User
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com", 
    "password": "Test@123456"
  }'
```

### 2. Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "Test@123456"
  }'
```

### 3. Access Protected Endpoints
```bash
curl -X GET http://localhost:8080/api/auth/profile \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## Password Requirements

- Minimum 8 characters
- At least one uppercase letter
- At least one lowercase letter  
- At least one digit
- At least one special character (@$!%*?&)

## JWT Configuration

Configure JWT settings in `application.properties`:
```properties
app.jwt.secret=your-secret-key
app.jwt.expiration=86400  # 24 hours in seconds
```

## Database Schema

The User entity includes:
- `id` - Unique 4-digit user ID
- `username` - Unique username
- `email` - Unique email address
- `password` - BCrypt hashed password
- `role` - USER or ADMIN role
- Profile fields (name, city, mobile, etc.)
- Timestamps (createdAt, updatedAt)

## Security Features

- **CORS Configuration**: Cross-origin requests handled
- **CSRF Protection**: Disabled for stateless JWT authentication
- **Session Management**: Stateless session policy
- **Authentication Entry Point**: Custom unauthorized access handling
- **Access Denied Handler**: Custom forbidden access handling
- **Method Security**: Enabled with @PreAuthorize support

## Testing

Use the provided `test-auth-api.http` file to test all authentication endpoints, or access the Swagger UI at `http://localhost:8080/swagger-ui.html` for interactive API testing.