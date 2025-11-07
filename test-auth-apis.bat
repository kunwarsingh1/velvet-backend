@echo off
echo Testing Authentication APIs...
echo Make sure the Spring Boot application is running on http://localhost:8080
echo.

REM Test 1: Register a new user
echo 1. Testing User Registration...
curl -X POST http://localhost:8080/api/auth/register ^
  -H "Content-Type: application/json" ^
  -d "{\"username\":\"testuser\",\"email\":\"test@example.com\",\"password\":\"Test@123456\"}"
echo.
echo.

REM Test 2: Login with the user
echo 2. Testing User Login...
curl -X POST http://localhost:8080/api/auth/login ^
  -H "Content-Type: application/json" ^
  -d "{\"email\":\"test@example.com\",\"password\":\"Test@123456\"}"
echo.
echo.

REM Test 3: Register admin user
echo 3. Testing Admin Registration...
curl -X POST http://localhost:8080/api/auth/register ^
  -H "Content-Type: application/json" ^
  -d "{\"username\":\"admin\",\"email\":\"admin@example.com\",\"password\":\"Admin@123456\"}"
echo.
echo.

echo Tests completed!
echo.
echo Next steps:
echo 1. Copy the JWT token from login response
echo 2. Use it to test protected endpoints:
echo    - GET /api/auth/profile
echo    - GET /api/networth/1234
echo    - GET /api/admin/users (admin only)
echo.
pause