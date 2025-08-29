package com.example.webhooksolver.dto;

public class SolutionRequest {
    private String sqlQuery;
    private String name;
    private String regNo;
    private String email;

    public SolutionRequest() {}

    public SolutionRequest(String sqlQuery, String name, String regNo, String email) {
        this.sqlQuery = sqlQuery;
        this.name = name;
        this.regNo = regNo;
        this.email = email;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
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
}
