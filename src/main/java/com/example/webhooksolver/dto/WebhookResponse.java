package com.example.webhooksolver.dto;

public class WebhookResponse {
    private String webhookUrl;
    private String jwtToken;
    private String sqlProblem;
    private String message;

    public WebhookResponse() {}

    public WebhookResponse(String webhookUrl, String jwtToken, String sqlProblem, String message) {
        this.webhookUrl = webhookUrl;
        this.jwtToken = jwtToken;
        this.sqlProblem = sqlProblem;
        this.message = message;
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

    public String getSqlProblem() {
        return sqlProblem;
    }

    public void setSqlProblem(String sqlProblem) {
        this.sqlProblem = sqlProblem;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
