@echo off
echo ========================================
echo VELVET INVESTMENT API COMPREHENSIVE TESTS
echo ========================================
echo.

REM Check if Node.js is installed
node --version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Node.js is not installed or not in PATH
    echo Please install Node.js from https://nodejs.org/
    pause
    exit /b 1
)

echo Node.js version:
node --version
echo.

REM Check if the Spring Boot application is running
echo Checking if Spring Boot application is running on port 8080...
netstat -an | find "8080" >nul 2>&1
if %errorlevel% neq 0 (
    echo.
    echo WARNING: No application detected on port 8080
    echo Please start the Spring Boot application first:
    echo   mvnw spring-boot:run
    echo.
    echo Press any key to continue anyway, or Ctrl+C to exit...
    pause >nul
)

REM Install axios if not present
echo Installing required dependencies...
if not exist node_modules\axios (
    npm install axios
    if %errorlevel% neq 0 (
        echo ERROR: Failed to install axios
        pause
        exit /b 1
    )
)

echo.
echo ========================================
echo STARTING COMPREHENSIVE API TESTS...
echo ========================================
echo.

REM Run the comprehensive test script
node comprehensive-api-test.js

echo.
echo ========================================
echo TESTS COMPLETED
echo ========================================
echo.
echo Check the output above for detailed results.
echo.
pause