package com.example.webhooksolver.service;

import org.springframework.stereotype.Service;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
public class SqlProblemSolver {
    
    public String solveSqlProblem(String sqlProblem) {
        if (sqlProblem == null || sqlProblem.trim().isEmpty()) {
            return "SELECT 1; -- No problem provided";
        }
        
        String problem = sqlProblem.toLowerCase();
        
        // Pattern matching for common SQL problems
        if (problem.contains("find") && problem.contains("employee") && problem.contains("salary")) {
            return solveEmployeeSalaryProblem(problem);
        } else if (problem.contains("count") && problem.contains("department")) {
            return solveDepartmentCountProblem(problem);
        } else if (problem.contains("join") && problem.contains("table")) {
            return solveJoinProblem(problem);
        } else if (problem.contains("group by") || problem.contains("aggregate")) {
            return solveAggregateProblem(problem);
        } else if (problem.contains("subquery") || problem.contains("nested")) {
            return solveSubqueryProblem(problem);
        } else if (problem.contains("update") || problem.contains("modify")) {
            return solveUpdateProblem(problem);
        } else if (problem.contains("delete") || problem.contains("remove")) {
            return solveDeleteProblem(problem);
        } else if (problem.contains("insert") || problem.contains("add")) {
            return solveInsertProblem(problem);
        } else {
            // Generic SELECT query for unknown problems
            return generateGenericSelectQuery(problem);
        }
    }
    
    private String solveEmployeeSalaryProblem(String problem) {
        if (problem.contains("highest") || problem.contains("maximum")) {
            return "SELECT * FROM employees WHERE salary = (SELECT MAX(salary) FROM employees);";
        } else if (problem.contains("lowest") || problem.contains("minimum")) {
            return "SELECT * FROM employees WHERE salary = (SELECT MIN(salary) FROM employees);";
        } else if (problem.contains("average") || problem.contains("avg")) {
            return "SELECT AVG(salary) as average_salary FROM employees;";
        } else if (problem.contains("above") || problem.contains("greater")) {
            return "SELECT * FROM employees WHERE salary > (SELECT AVG(salary) FROM employees);";
        } else {
            return "SELECT * FROM employees ORDER BY salary DESC;";
        }
    }
    
    private String solveDepartmentCountProblem(String problem) {
        if (problem.contains("employee") && problem.contains("count")) {
            return "SELECT department_id, COUNT(*) as employee_count FROM employees GROUP BY department_id;";
        } else if (problem.contains("department") && problem.contains("list")) {
            return "SELECT DISTINCT department_name FROM departments;";
        } else {
            return "SELECT department_id, COUNT(*) as count FROM employees GROUP BY department_id;";
        }
    }
    
    private String solveJoinProblem(String problem) {
        if (problem.contains("employee") && problem.contains("department")) {
            return "SELECT e.name, d.department_name FROM employees e JOIN departments d ON e.department_id = d.id;";
        } else if (problem.contains("order") && problem.contains("customer")) {
            return "SELECT o.order_id, c.customer_name FROM orders o JOIN customers c ON o.customer_id = c.id;";
        } else {
            return "SELECT * FROM table1 t1 JOIN table2 t2 ON t1.id = t2.id;";
        }
    }
    
    private String solveAggregateProblem(String problem) {
        if (problem.contains("sum")) {
            return "SELECT category, SUM(amount) as total FROM transactions GROUP BY category;";
        } else if (problem.contains("count")) {
            return "SELECT category, COUNT(*) as count FROM items GROUP BY category;";
        } else if (problem.contains("average") || problem.contains("avg")) {
            return "SELECT category, AVG(value) as average_value FROM data GROUP BY category;";
        } else {
            return "SELECT column1, COUNT(*) as count FROM table_name GROUP BY column1;";
        }
    }
    
    private String solveSubqueryProblem(String problem) {
        if (problem.contains("employee") && problem.contains("salary")) {
            return "SELECT * FROM employees WHERE salary > (SELECT AVG(salary) FROM employees);";
        } else if (problem.contains("department") && problem.contains("employee")) {
            return "SELECT * FROM departments WHERE id IN (SELECT DISTINCT department_id FROM employees);";
        } else {
            return "SELECT * FROM table1 WHERE column1 IN (SELECT column1 FROM table2 WHERE condition);";
        }
    }
    
    private String solveUpdateProblem(String problem) {
        if (problem.contains("salary") && problem.contains("increase")) {
            return "UPDATE employees SET salary = salary * 1.1 WHERE department_id = 1;";
        } else if (problem.contains("status")) {
            return "UPDATE orders SET status = 'completed' WHERE order_date < '2023-12-31';";
        } else {
            return "UPDATE table_name SET column1 = 'new_value' WHERE condition;";
        }
    }
    
    private String solveDeleteProblem(String problem) {
        if (problem.contains("inactive") || problem.contains("old")) {
            return "DELETE FROM employees WHERE last_login < '2023-01-01';";
        } else if (problem.contains("duplicate")) {
            return "DELETE FROM table_name WHERE id NOT IN (SELECT MIN(id) FROM table_name GROUP BY column1);";
        } else {
            return "DELETE FROM table_name WHERE condition;";
        }
    }
    
    private String solveInsertProblem(String problem) {
        if (problem.contains("employee")) {
            return "INSERT INTO employees (name, email, department_id) VALUES ('John Doe', 'john@example.com', 1);";
        } else if (problem.contains("department")) {
            return "INSERT INTO departments (name, location) VALUES ('IT', 'Building A');";
        } else {
            return "INSERT INTO table_name (column1, column2) VALUES ('value1', 'value2');";
        }
    }
    
    private String generateGenericSelectQuery(String problem) {
        // Extract potential table names from the problem
        Pattern tablePattern = Pattern.compile("\\b(employees?|departments?|orders?|customers?|products?|users?)\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = tablePattern.matcher(problem);
        
        if (matcher.find()) {
            String tableName = matcher.group(1).toLowerCase();
            return "SELECT * FROM " + tableName + ";";
        } else {
            return "SELECT * FROM table_name;";
        }
    }
}
