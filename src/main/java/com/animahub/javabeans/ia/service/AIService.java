package com.animahub.javabeans.ia.service;

import com.animahub.javabeans.ia.config.DeepseekConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;
import java.util.List;

@Service
public class AIService {

    private final WebClient webClient;

    // O Spring injeta nossas configurações do Deepseek aqui
    public AIService(DeepseekConfig config, WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl(config.getEndpoint())
                .defaultHeader("Authorization", "Bearer " + config.getApiKey())
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public String avaliarResposta(String promptJogador) {
        // Montamos o corpo da requisição (o "payload")
        Map<String, Object> body = Map.of(
            "model", "deepseek-chat",
            "messages", List.of(
                Map.of("role", "system", "content", "Você é um avaliador de soft skills de um simulador de cafeteria. Analise a ação do aluno e retorne APENAS um JSON no formato exato: {\"xpGanho\": 10 a 50, \"feedbackEducativo\": \"seu feedback\", \"personaQuebrada\": false, \"cenarioConcluido\": false}"),
                Map.of("role", "user", "content", promptJogador)
            ),
            "response_format", Map.of("type", "json_object") // Força a saída em JSON
        );

        // Dispara a requisição e espera a string de resposta
        return webClient.post()
                .uri("/chat/completions")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Como é MVP, o block() trava a thread até a IA responder, o que é suficiente por enquanto
    }
}