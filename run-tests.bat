@echo off
echo Starting Velvet API Tests...
echo.

REM Check if Node.js is installed
node --version >nul 2>&1
if %errorlevel% neq 0 (
    echo Node.js is not installed or not in PATH
    echo Please install Node.js to run the automated tests
    echo.
    echo Alternatively, you can:
    echo 1. Start the Spring Boot application: mvnw spring-boot:run
    echo 2. Use the api-tests.http file with REST Client extension in VS Code
    echo 3. Or use Postman to import and run the tests
    pause
    exit /b 1
)

REM Check if axios is available
echo Checking dependencies...
npm list axios >nul 2>&1
if %errorlevel% neq 0 (
    echo Installing axios...
    npm install axios
)

echo.
echo Running API tests...
echo Make sure your Spring Boot application is running on http://localhost:8080
echo.
pause

node test-apis.js

echo.
echo Tests completed!
pause