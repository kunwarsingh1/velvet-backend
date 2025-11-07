@echo off
echo ========================================
echo VELVET INVESTMENT API - NGROK SETUP
echo ========================================
echo.

REM Check if ngrok is installed
if not exist "ngrok.exe" (
    echo ERROR: ngrok.exe not found in current directory
    echo Please run install-ngrok.bat first
    pause
    exit /b 1
)

echo ngrok version:
ngrok.exe version
echo.

REM Check if Spring Boot app is running on port 8080
echo Checking if Spring Boot application is running on port 8080...
netstat -an | find "8080" >nul 2>&1
if %errorlevel% neq 0 (
    echo.
    echo WARNING: No application detected on port 8080
    echo Please start the Spring Boot application first:
    echo   mvnw.cmd spring-boot:run
    echo.
    echo Press any key to continue anyway, or Ctrl+C to exit...
    pause >nul
)

echo.
echo ========================================
echo STARTING NGROK TUNNEL...
echo ========================================
echo.
echo Your Velvet Investment API will be accessible via:
echo - Local: http://localhost:8080
echo - Public: [ngrok will provide the URL]
echo.
echo Press Ctrl+C to stop the tunnel
echo.

REM Start ngrok tunnel for port 8080
ngrok.exe http 8080