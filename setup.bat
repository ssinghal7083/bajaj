@echo off
echo ========================================
echo Webhook SQL Problem Solver Setup
echo ========================================
echo.

echo Checking Java installation...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Java is not installed or not in PATH.
    echo.
    echo Please install Java 17 or higher:
    echo 1. Download OpenJDK 17 from: https://adoptium.net/
    echo 2. Install it on your system
    echo 3. Add Java to your PATH environment variable
    echo 4. Set JAVA_HOME environment variable
    echo.
    echo After installing Java, run this script again.
    pause
    exit /b 1
)

echo Java is installed.
echo.

echo Checking Maven installation...
mvn -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Maven is not installed, but we can use the Maven wrapper.
    echo.
)

echo Building the project...
call .\mvnw.cmd clean compile
if %errorlevel% neq 0 (
    echo Build failed. Please check the error messages above.
    pause
    exit /b 1
)

echo.
echo ========================================
echo Setup completed successfully!
echo ========================================
echo.
echo To run the application:
echo 1. Run: .\mvnw.cmd spring-boot:run
echo 2. Open browser to: http://localhost:8080
echo 3. Access H2 console at: http://localhost:8080/h2-console
echo.
echo The application will automatically:
echo - Send a webhook generation request on startup
echo - Solve the received SQL problem
echo - Store the solution in the database
echo - Submit the solution back to the webhook
echo.
pause
