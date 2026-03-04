package com.animahub.javabeans.ia.service;

import com.animahub.javabeans.ia.config.DeepseekConfig;
import com.animahub.javabeans.ia.model.Ingrediente;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.Map;

@Service
public class AIService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AIService(DeepseekConfig config, WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl(config.getEndpoint())
                .defaultHeader("Authorization", "Bearer " + config.getApiKey())
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public String avaliarResposta(String promptJogador) {
        Map<String, Object> body = Map.of(
                "model", "deepseek-chat",
                "messages", List.of(
                        Map.of("role", "system", "content", "Você é um avaliador de soft skills... JSON: {\"xpGanho\":10-50, \"feedbackEducativo\":\"...\", \"personaQuebrada\":false, \"cenarioConcluido\":false}"),
                        Map.of("role", "user", "content", promptJogador)
                ),
                "response_format", Map.of("type", "json_object")
        );

        return webClient.post()
                .uri("/chat/completions")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String gerarFeedbackBarista(String bebida, List<Ingrediente> escolhido, List<Ingrediente> esperado, boolean acertou) {
        String tom = acertou ? "entusiasmado e elogiador" : "construtivo, educado, mas honesto";
        String abertura = acertou ? "Ótimo! O sabor está incrível!" : "Hmm... não foi exatamente isso que eu esperava...";

        String prompt = String.format("""
                Você é um cliente em uma cafeteria, falando diretamente com o barista.
                Tom: %s.
                Comece com: "%s"

                Bebida pedida: %s
                Ingredientes corretos esperados: %s
                O que o barista entregou: %s

                Dê feedback curto (2-4 frases), realista e imersivo.
                Se errou muito, seja sincero mas não agressivo.
                Nunca elogie se estiver claramente errado.
                """, tom, abertura, bebida, esperado, escolhido);

        Map<String, Object> body = Map.of(
                "model", "deepseek-chat",
                "messages", List.of(
                        Map.of("role", "system", "content", "Você é um cliente reagindo ao pedido."),
                        Map.of("role", "user", "content", prompt)
                )
        );

        try {
            String rawResponse = webClient.post()
                    .uri("/chat/completions")
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            
            // 🔥 O PULO DO GATO: Desempacota o JSON e pega só o texto!
            JsonNode root = objectMapper.readTree(rawResponse);
            return root.path("choices").get(0).path("message").path("content").asText();

        } catch (Exception e) {
            return "Desculpe, erro ao processar a resposta da IA: " + e.getMessage();
        }
    }
}
