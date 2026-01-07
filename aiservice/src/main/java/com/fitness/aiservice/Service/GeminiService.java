package com.fitness.aiservice.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String getAnswer(String question) {
        // 1. Prepare request body
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(Map.of("text", question)))
                )
        );

        // 2. Execute call
        return webClient.post()
                .uri(geminiApiUrl + "?key=" + geminiApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), response ->
                        response.bodyToMono(String.class).flatMap(errorBody -> {
                            System.err.println("GEMINI API ERROR: " + errorBody);
                            return Mono.error(new RuntimeException("API Error: " + errorBody));
                        })
                )
                .bodyToMono(Map.class)
                .map(this::extractTextFromResponse)
                .onErrorReturn("Error: Unable to process request at this time.")
                .block();
    }

    private String extractTextFromResponse(Map<String, Object> response) {
        try {
            // Correct JSON Path Parsing:
            // candidates is a List
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");

            // content is a Map (NOT a List)
            Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");

            // parts is a List
            List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");

            return (String) parts.get(0).get("text");
        } catch (Exception e) {
            System.err.println("Parsing error: " + e.getMessage());
            return "Could not parse response: " + e.getMessage();
        }
    }
}