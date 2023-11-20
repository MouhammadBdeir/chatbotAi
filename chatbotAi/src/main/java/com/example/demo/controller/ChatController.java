package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final WebClient webClient;

    @Value("${chatgpt.api.url}")
    private String chatGptApiUrl;

    public ChatController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(chatGptApiUrl).build();
    }

    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestBody String question) {
        // Sende die Anfrage an die ChatGPT-API
        String apiUrl = "/v1/chat";

        String response = webClient.post()
                .uri(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(question)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Blockiert den Thread, um die Antwort zu erhalten (in einer Produktionsumgebung besser vermeiden)

        return ResponseEntity.ok(response);
    }
}


