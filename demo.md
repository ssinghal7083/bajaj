# Webhook SQL Problem Solver - Demonstration

## What This Application Does

This Spring Boot application demonstrates a complete webhook-based workflow for solving SQL problems automatically.

## Workflow Demonstration

### Step 1: Application Startup
When the application starts, it automatically sends a POST request to:
```
POST https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA
```

**Request Body:**
```json
{
  "name": "John Doe",
  "regNo": "REG12347", 
  "email": "john@example.com"
}
```

### Step 2: Webhook Response
The server responds with:
```json
{
  "webhookUrl": "https://example.com/webhook/abc123",
  "jwtToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "sqlProblem": "Find all employees with salary greater than the average salary",
  "message": "Webhook generated successfully"
}
```

### Step 3: SQL Problem Solving
The application analyzes the SQL problem and generates a solution:
```sql
SELECT * FROM employees WHERE salary > (SELECT AVG(salary) FROM employees);
```

### Step 4: Solution Storage
The problem and solution are stored in the H2 database:
```sql
INSERT INTO sql_solutions (sql_problem, sql_solution, webhook_url, jwt_token, name, reg_no, email, created_at, submitted) 
VALUES ('Find all employees...', 'SELECT * FROM employees...', 'https://example.com/webhook/abc123', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...', 'John Doe', 'REG12347', 'john@example.com', '2024-01-01 10:00:00', false);
```

### Step 5: Solution Submission
The application sends the solution back to the webhook URL:
```
POST https://example.com/webhook/abc123
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

**Request Body:**
```json
{
  "sqlQuery": "SELECT * FROM employees WHERE salary > (SELECT AVG(salary) FROM employees);",
  "name": "John Doe",
  "regNo": "REG12347",
  "email": "john@example.com"
}
```

## SQL Problem Solver Examples

The application can solve various types of SQL problems:

### Employee/Salary Problems
- **Problem**: "Find employee with highest salary"
- **Solution**: `SELECT * FROM employees WHERE salary = (SELECT MAX(salary) FROM employees);`

### Department Problems  
- **Problem**: "Count employees by department"
- **Solution**: `SELECT department_id, COUNT(*) as employee_count FROM employees GROUP BY department_id;`

### Join Operations
- **Problem**: "Join employee and department tables"
- **Solution**: `SELECT e.name, d.department_name FROM employees e JOIN departments d ON e.department_id = d.id;`

### Aggregate Functions
- **Problem**: "Calculate sum by category"
- **Solution**: `SELECT category, SUM(amount) as total FROM transactions GROUP BY category;`

## API Endpoints

### Manual Execution
```bash
# Trigger the webhook workflow manually
curl -X POST http://localhost:8080/api/webhook/execute
```

### View Solutions
```bash
# Get all stored solutions
curl http://localhost:8080/api/webhook/solutions

# Get specific solution
curl http://localhost:8080/api/webhook/solutions/1
```

### Health Check
```bash
# Check if service is running
curl http://localhost:8080/api/webhook/health
```

## Database Schema

The application uses an H2 in-memory database with the following table:

```sql
CREATE TABLE sql_solutions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sql_problem TEXT,
    sql_solution TEXT,
    webhook_url VARCHAR(255),
    jwt_token TEXT,
    name VARCHAR(255),
    reg_no VARCHAR(255),
    email VARCHAR(255),
    created_at TIMESTAMP,
    submitted BOOLEAN
);
```

## Monitoring and Debugging

### Console Logs
The application provides detailed logging:
```
2024-01-01 10:00:00.123 INFO  - Starting webhook workflow...
2024-01-01 10:00:01.456 INFO  - Webhook generated successfully: https://example.com/webhook/abc123
2024-01-01 10:00:01.789 INFO  - SQL Problem received: Find all employees with salary greater than the average salary
2024-01-01 10:00:02.012 INFO  - SQL Solution generated: SELECT * FROM employees WHERE salary > (SELECT AVG(salary) FROM employees);
2024-01-01 10:00:02.345 INFO  - Solution stored in database with ID: 1
2024-01-01 10:00:03.678 INFO  - Solution submitted successfully to webhook
```

### H2 Console
Access the database console at: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

## Error Handling

The application handles various error scenarios:
- Network connectivity issues
- Invalid webhook responses
- Database errors
- JWT token expiration
- Malformed SQL problems

## Security Features

- JWT token authentication for webhook submission
- Input validation for SQL problems
- Secure HTTP client configuration
- Database connection security

This demonstration shows how the application creates a complete automated workflow for solving SQL problems through webhook integration.
