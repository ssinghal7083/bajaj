package com.example.webhooksolver.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class SqlProblemSolverTest {

    private SqlProblemSolver sqlProblemSolver;

    @BeforeEach
    void setUp() {
        sqlProblemSolver = new SqlProblemSolver();
    }

    @Test
    void testSolveEmployeeSalaryProblem() {
        String problem = "Find the employee with the highest salary";
        String solution = sqlProblemSolver.solveSqlProblem(problem);
        
        assertNotNull(solution);
        assertTrue(solution.contains("SELECT"));
        assertTrue(solution.contains("MAX(salary)"));
        assertTrue(solution.contains("employees"));
    }

    @Test
    void testSolveDepartmentCountProblem() {
        String problem = "Count employees by department";
        String solution = sqlProblemSolver.solveSqlProblem(problem);
        
        assertNotNull(solution);
        assertTrue(solution.contains("SELECT"));
        assertTrue(solution.contains("COUNT(*)"));
        assertTrue(solution.contains("GROUP BY"));
    }

    @Test
    void testSolveJoinProblem() {
        String problem = "Join employee and department tables";
        String solution = sqlProblemSolver.solveSqlProblem(problem);
        
        assertNotNull(solution);
        assertTrue(solution.contains("JOIN"));
        assertTrue(solution.contains("employees"));
        assertTrue(solution.contains("departments"));
    }

    @Test
    void testSolveAggregateProblem() {
        String problem = "Calculate sum by category";
        String solution = sqlProblemSolver.solveSqlProblem(problem);
        
        assertNotNull(solution);
        assertTrue(solution.contains("SUM"));
        assertTrue(solution.contains("GROUP BY"));
    }

    @Test
    void testSolveNullProblem() {
        String solution = sqlProblemSolver.solveSqlProblem(null);
        assertNotNull(solution);
        assertTrue(solution.contains("SELECT 1"));
    }

    @Test
    void testSolveEmptyProblem() {
        String solution = sqlProblemSolver.solveSqlProblem("");
        assertNotNull(solution);
        assertTrue(solution.contains("SELECT 1"));
    }
}
