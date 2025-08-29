package com.example.webhooksolver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebhookSolverApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebhookSolverApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(WebhookService webhookService) {
        return args -> {
            System.out.println("Starting webhook workflow...");
            webhookService.executeWebhookWorkflow();
        };
    }
}
