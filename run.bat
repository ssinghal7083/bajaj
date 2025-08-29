@echo off
echo ========================================
echo Starting Webhook SQL Problem Solver
echo ========================================
echo.

echo Checking if Java is available...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Java is not installed or not in PATH.
    echo Please install Java 17 or higher and try again.
    pause
    exit /b 1
)

echo Starting the application...
echo.
echo The application will:
echo 1. Send a webhook generation request
echo 2. Receive and solve a SQL problem
echo 3. Store the solution in the database
echo 4. Submit the solution back to the webhook
echo.
echo Press Ctrl+C to stop the application
echo.

call .\mvnw.cmd spring-boot:run

echo.
echo Application stopped.
pause
