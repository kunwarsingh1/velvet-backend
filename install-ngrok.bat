@echo off
echo ========================================
echo INSTALLING NGROK FOR VELVET PROJECT
echo ========================================
echo.

echo Downloading ngrok...
powershell -Command "Invoke-WebRequest -Uri 'https://bin.equinox.io/c/bNyj1mQVY4c/ngrok-v3-stable-windows-amd64.zip' -OutFile 'ngrok.zip'"

echo Extracting ngrok...
powershell -Command "Expand-Archive -Path 'ngrok.zip' -DestinationPath '.' -Force"

echo Cleaning up...
del ngrok.zip

echo.
echo ========================================
echo NGROK INSTALLED SUCCESSFULLY!
echo ========================================
echo.
echo Next steps:
echo 1. Get auth token from https://dashboard.ngrok.com/get-started/your-authtoken
echo 2. Run: ngrok.exe authtoken YOUR_TOKEN
echo 3. Start tunnel: setup-ngrok.bat
echo.
pause