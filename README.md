# Webhook SQL Problem Solver

A Spring Boot application that automatically solves SQL problems through a webhook-based workflow.

## Features

- **Automatic Webhook Generation**: Sends POST request to generate webhook on startup
- **SQL Problem Solving**: Analyzes and solves SQL problems using pattern matching
- **Solution Storage**: Stores problems and solutions in H2 database
- **JWT Authentication**: Uses JWT tokens for secure solution submission
- **REST API**: Provides endpoints for manual execution and monitoring

## Workflow

1. **Startup**: Application automatically sends a POST request to generate a webhook
2. **Problem Reception**: Receives SQL problem from the webhook response
3. **Problem Solving**: Analyzes the SQL problem and generates a solution
4. **Storage**: Stores the problem and solution in the database
5. **Submission**: Sends the solution back to the webhook URL using JWT authentication

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Setup and Running

### Prerequisites

- **Java 17 or higher** - Download from [Eclipse Temurin](https://adoptium.net/)
- **Maven 3.6 or higher** (optional - Maven wrapper is included)

### Windows Setup

1. **Install Java 17+**:
   - Download OpenJDK 17 from https://adoptium.net/
   - Install and add to PATH
   - Set JAVA_HOME environment variable

2. **Quick Setup**:
   ```cmd
   # Run the setup script
   setup.bat
   ```

3. **Run the Application**:
   ```cmd
   # Run the application
   run.bat
   
   # Or manually
   .\mvnw.cmd spring-boot:run
   ```

### Linux/Mac Setup

1. **Install Java 17+**:
   ```bash
   # Ubuntu/Debian
   sudo apt update
   sudo apt install openjdk-17-jdk
   
   # macOS (using Homebrew)
   brew install openjdk@17
   ```

2. **Build and Run**:
   ```bash
   # Build the project
   ./mvnw clean install
   
   # Run the application
   ./mvnw spring-boot:run
   ```

### 3. Access Points

- **Application**: http://localhost:8080
- **H2 Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: `password`

## API Endpoints

### Manual Execution
- `POST /api/webhook/execute` - Manually trigger the webhook workflow

### Monitoring
- `GET /api/webhook/solutions` - Get all stored solutions
- `GET /api/webhook/solutions/{id}` - Get specific solution by ID
- `GET /api/webhook/health` - Health check endpoint

## Configuration

The application uses the following configuration in `application.properties`:

- **Server Port**: 8080
- **Database**: H2 in-memory database
- **Logging**: DEBUG level for detailed logging
- **Webhook URL**: https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA

## SQL Problem Solver

The application includes a smart SQL problem solver that can handle various types of SQL problems:

- **Employee/Salary Problems**: Finding highest, lowest, average salaries
- **Department Problems**: Counting employees by department
- **Join Operations**: Employee-department, order-customer joins
- **Aggregate Functions**: SUM, COUNT, AVG operations
- **Subqueries**: Nested queries and IN clauses
- **DML Operations**: INSERT, UPDATE, DELETE statements

## Project Structure

```
src/main/java/com/example/webhooksolver/
├── WebhookSolverApplication.java    # Main application class
├── controller/
│   └── WebhookController.java       # REST API endpoints
├── dto/
│   ├── WebhookRequest.java          # Webhook generation request
│   ├── WebhookResponse.java         # Webhook response
│   └── SolutionRequest.java         # Solution submission request
├── entity/
│   └── SqlSolution.java             # Database entity
├── repository/
│   └── SqlSolutionRepository.java   # Data access layer
└── service/
    ├── WebhookService.java          # Main workflow service
    └── SqlProblemSolver.java        # SQL problem solving logic
```

## Logging

The application provides detailed logging for:
- Webhook generation requests and responses
- SQL problem analysis and solution generation
- Database operations
- Solution submission attempts

## Error Handling

The application includes comprehensive error handling for:
- Network connectivity issues
- Invalid webhook responses
- Database errors
- JWT token issues

## Testing

To test the application:

1. Start the application
2. Check the console logs for workflow execution
3. Access the H2 console to view stored solutions
4. Use the REST API endpoints for manual testing

## Dependencies

- Spring Boot 3.2.0
- Spring WebFlux (for HTTP client)
- Spring Data JPA
- H2 Database
- JJWT (JWT library)
- SLF4J (logging)

## License

This project is for educational and demonstration purposes.
