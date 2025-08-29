package com.example.webhooksolver.service;

import com.example.webhooksolver.dto.WebhookRequest;
import com.example.webhooksolver.dto.WebhookResponse;
import com.example.webhooksolver.dto.SolutionRequest;
import com.example.webhooksolver.entity.SqlSolution;
import com.example.webhooksolver.repository.SqlSolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WebhookService {
    
    private static final Logger logger = LoggerFactory.getLogger(WebhookService.class);
    private static final String WEBHOOK_GENERATION_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    
    @Autowired
    private SqlSolutionRepository sqlSolutionRepository;
    
    @Autowired
    private SqlProblemSolver sqlProblemSolver;
    
    private final WebClient webClient;
    
    public WebhookService() {
        this.webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    
    public void executeWebhookWorkflow() {
        try {
            logger.info("Starting webhook workflow...");
            
            // Step 1: Generate webhook
            WebhookResponse webhookResponse = generateWebhook();
            if (webhookResponse == null) {
                logger.error("Failed to generate webhook");
                return;
            }
            
            logger.info("Webhook generated successfully: {}", webhookResponse.getWebhookUrl());
            logger.info("SQL Problem received: {}", webhookResponse.getSqlProblem());
            
            // Step 2: Solve SQL problem
            String sqlSolution = sqlProblemSolver.solveSqlProblem(webhookResponse.getSqlProblem());
            logger.info("SQL Solution generated: {}", sqlSolution);
            
            // Step 3: Store the solution
            SqlSolution solution = new SqlSolution(
                webhookResponse.getSqlProblem(),
                webhookResponse.getWebhookUrl(),
                webhookResponse.getJwtToken(),
                "John Doe",
                "REG12347",
                "john@example.com"
            );
            solution.setSqlSolution(sqlSolution);
            sqlSolutionRepository.save(solution);
            logger.info("Solution stored in database with ID: {}", solution.getId());
            
            // Step 4: Submit solution to webhook
            boolean submissionSuccess = submitSolution(webhookResponse.getWebhookUrl(), 
                                                     webhookResponse.getJwtToken(), 
                                                     sqlSolution);
            
            if (submissionSuccess) {
                solution.setSubmitted(true);
                sqlSolutionRepository.save(solution);
                logger.info("Solution submitted successfully to webhook");
            } else {
                logger.error("Failed to submit solution to webhook");
            }
            
        } catch (Exception e) {
            logger.error("Error in webhook workflow", e);
        }
    }
    
    private WebhookResponse generateWebhook() {
        try {
            WebhookRequest request = new WebhookRequest("John Doe", "REG12347", "john@example.com");
            
            logger.info("Sending webhook generation request to: {}", WEBHOOK_GENERATION_URL);
            
            return webClient.post()
                    .uri(WEBHOOK_GENERATION_URL)
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(WebhookResponse.class)
                    .doOnSuccess(response -> logger.info("Webhook generation successful"))
                    .doOnError(error -> logger.error("Webhook generation failed: {}", error.getMessage()))
                    .block();
                    
        } catch (Exception e) {
            logger.error("Error generating webhook", e);
            return null;
        }
    }
    
    private boolean submitSolution(String webhookUrl, String jwtToken, String sqlQuery) {
        try {
            SolutionRequest solutionRequest = new SolutionRequest(sqlQuery, "John Doe", "REG12347", "john@example.com");
            
            logger.info("Submitting solution to webhook: {}", webhookUrl);
            
            String response = webClient.post()
                    .uri(webhookUrl)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                    .bodyValue(solutionRequest)
                    .retrieve()
                    .bodyToMono(String.class)
                    .doOnSuccess(result -> logger.info("Solution submission successful: {}", result))
                    .doOnError(error -> logger.error("Solution submission failed: {}", error.getMessage()))
                    .block();
            
            return response != null;
            
        } catch (Exception e) {
            logger.error("Error submitting solution", e);
            return false;
        }
    }
}
