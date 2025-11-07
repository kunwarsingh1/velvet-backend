@echo off
echo ========================================
echo VELVET INVESTMENT API + NGROK LAUNCHER
echo ========================================
echo.

REM Check if ngrok is installed
if not exist "ngrok.exe" (
    echo ERROR: ngrok.exe not found
    echo Please run install-ngrok.bat first
    pause
    exit /b 1
)

echo Starting Spring Boot application...
start "Velvet API" cmd /c "mvnw.cmd spring-boot:run"

echo Waiting for application to start...
timeout /t 30 /nobreak

echo.
echo Starting ngrok tunnel...
start "Ngrok Tunnel" cmd /c "ngrok.exe http 8080"

echo.
echo ========================================
echo SETUP COMPLETE!
echo ========================================
echo.
echo 1. Spring Boot API: http://localhost:8080
echo 2. Ngrok Tunnel: Check the ngrok window for public URL
echo 3. API Documentation: [ngrok-url]/swagger-ui/index.html
echo 4. H2 Database Console: [ngrok-url]/h2-console
echo.
echo Press any key to exit...
pause >nul