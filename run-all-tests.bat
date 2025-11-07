@echo off
echo ========================================
echo   Comprehensive API Test Suite
echo ========================================
echo.

REM Check if Node.js is installed
where node >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Node.js is not installed or not in PATH
    echo Please install Node.js from https://nodejs.org/
    pause
    exit /b 1
)

REM Check if the application is running
echo Checking if application is running...
curl -s http://localhost:8080/actuator/health >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERROR: Application is not running on http://localhost:8080
    echo Please start the application first using: start-app.bat
    echo.
    pause
    exit /b 1
)

echo Application is running!
echo.
echo Starting comprehensive API tests...
echo.

REM Run the test script
node test-all-apis.js

echo.
echo ========================================
echo   Test execution completed
echo ========================================
pause
