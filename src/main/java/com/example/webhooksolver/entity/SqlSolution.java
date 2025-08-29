package com.example.webhooksolver.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sql_solutions")
public class SqlSolution {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "TEXT")
    private String sqlProblem;
    
    @Column(columnDefinition = "TEXT")
    private String sqlSolution;
    
    private String webhookUrl;
    private String jwtToken;
    private String name;
    private String regNo;
    private String email;
    private LocalDateTime createdAt;
    private boolean submitted;

    public SqlSolution() {
        this.createdAt = LocalDateTime.now();
    }

    public SqlSolution(String sqlProblem, String webhookUrl, String jwtToken, String name, String regNo, String email) {
        this();
        this.sqlProblem = sqlProblem;
        this.webhookUrl = webhookUrl;
        this.jwtToken = jwtToken;
        this.name = name;
        this.regNo = regNo;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSqlProblem() {
        return sqlProblem;
    }

    public void setSqlProblem(String sqlProblem) {
        this.sqlProblem = sqlProblem;
    }

    public String getSqlSolution() {
        return sqlSolution;
    }

    public void setSqlSolution(String sqlSolution) {
        this.sqlSolution = sqlSolution;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }
}
