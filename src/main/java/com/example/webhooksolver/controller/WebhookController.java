package com.example.webhooksolver.controller;

import com.example.webhooksolver.entity.SqlSolution;
import com.example.webhooksolver.repository.SqlSolutionRepository;
import com.example.webhooksolver.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/webhook")
public class WebhookController {
    
    @Autowired
    private WebhookService webhookService;
    
    @Autowired
    private SqlSolutionRepository sqlSolutionRepository;
    
    @PostMapping("/execute")
    public ResponseEntity<String> executeWebhookWorkflow() {
        try {
            webhookService.executeWebhookWorkflow();
            return ResponseEntity.ok("Webhook workflow executed successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error executing webhook workflow: " + e.getMessage());
        }
    }
    
    @GetMapping("/solutions")
    public ResponseEntity<List<SqlSolution>> getAllSolutions() {
        List<SqlSolution> solutions = sqlSolutionRepository.findAll();
        return ResponseEntity.ok(solutions);
    }
    
    @GetMapping("/solutions/{id}")
    public ResponseEntity<SqlSolution> getSolutionById(@PathVariable Long id) {
        return sqlSolutionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Webhook Solver Service is running");
    }
}
