@echo off
echo Starting Velvet Spring Boot Application...
echo.

REM Try to find Java
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Java is not installed or not in PATH
    echo Please install Java 17 or higher
    pause
    exit /b 1
)

REM Check if we have a compiled jar
if exist "target\Velvet-0.0.1-SNAPSHOT.jar" (
    echo Running from JAR file...
    java -jar target\Velvet-0.0.1-SNAPSHOT.jar
) else (
    echo JAR file not found. Trying to run with Maven...
    if exist "mvnw.cmd" (
        mvnw.cmd spring-boot:run
    ) else (
        echo Maven wrapper not found. Please compile the project first.
        echo Run: mvn clean package
        pause
        exit /b 1
    )
)