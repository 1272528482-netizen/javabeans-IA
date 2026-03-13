package com.animahub.javabeans.ia.service;

import com.animahub.javabeans.ia.config.DeepseekConfig;
import com.animahub.javabeans.ia.dto.AvaliacaoPedagogicaDTO;
import com.animahub.javabeans.ia.model.Ingrediente;
import com.animahub.javabeans.ia.prompt.PromptTemplateService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.Map;

@Service
public class AIService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    private final PromptTemplateService promptTemplateService;

    public AIService(DeepseekConfig config, WebClient.Builder builder, ObjectMapper objectMapper, PromptTemplateService promptTemplateService) {
        this.webClient = builder
                .baseUrl(config.getEndpoint())
                .defaultHeader("Authorization", "Bearer " + config.getApiKey())
                .defaultHeader("Content-Type", "application/json")
                .build();
        this.objectMapper = objectMapper;
        this.promptTemplateService = promptTemplateService;
    }

    public AvaliacaoPedagogicaDTO avaliarResposta(String promptJogador) {
        String systemPrompt = promptTemplateService.loadPrompt("avaliacao_pedagogica_system.txt");
        Map<String, Object> body = Map.of(
                "model", "deepseek-chat",
                "messages", List.of(
                        Map.of("role", "system", "content", systemPrompt),
                        Map.of("role", "user", "content", promptJogador)
                ),
                "response_format", Map.of("type", "json_object")
        );

        try {
            String rawResponse = webClient.post()
                    .uri("/chat/completions")
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            JsonNode root = objectMapper.readTree(rawResponse);
            String conteudoIA = root.path("choices").get(0).path("message").path("content").asText();

            // Limpa possíveis blocos de código Markdown que a IA possa incluir.
            String jsonLimpo = conteudoIA
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();

            AvaliacaoPedagogicaDTO avaliacao = objectMapper.readValue(jsonLimpo, AvaliacaoPedagogicaDTO.class);
            return avaliacao;

        } catch (Exception e) {
            System.err.println("Erro ao validar JSON da IA: " + e.getMessage());
            return new AvaliacaoPedagogicaDTO("Erro na IA", "Erro de conexão", false);
        }
    }

    public String gerarFeedbackBarista(String bebida, List<Ingrediente> escolhido, List<Ingrediente> esperado, boolean acertou) {
        String ingredientesEsperadosStr = esperado.stream().map(Ingrediente::name).reduce((a, b) -> a + ", " + b).orElse("");
        String ingredientesEscolhidosStr = escolhido.stream().map(Ingrediente::name).reduce((a, b) -> a + ", " + b).orElse("");

        String systemPrompt = promptTemplateService.loadPrompt("feedback_barista_system.txt");
        String userPrompt = promptTemplateService.renderPrompt(
            "feedback_barista_user.txt",
            Map.of(
                "bebida", bebida,
                "ingredientesEsperados", ingredientesEsperadosStr,
                "ingredientesEscolhidos", ingredientesEscolhidosStr,
                "acertou", acertou ? "sim" : "não"
            )
        );
        Map<String, Object> body = Map.of(
                "model", "deepseek-chat",
                "messages", List.of(
                        Map.of("role", "system", "content", systemPrompt),
                        Map.of("role", "user", "content", userPrompt)
                ),
                "temperature", 0.7
        );

        try {
            String rawResponse = webClient.post()
                    .uri("/chat/completions")
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            
            JsonNode root = objectMapper.readTree(rawResponse);
            String feedback = root.path("choices").get(0).path("message").path("content").asText().trim();
            
            // Limpar possíveis blocos de código Markdown
            feedback = feedback.replaceAll("```.*?```", "").trim();
            
            // Ensure it's plain text, no JSON
            if (feedback.startsWith("{") || feedback.startsWith("[") || feedback.isEmpty()) {
                throw new Exception("Resposta inválida ou em formato JSON");
            }
            return feedback;

        } catch (Exception e) {
            System.err.println("Erro ao gerar feedback da IA: " + e.getMessage());
            // Fallback amigável e imersivo, sem detalhes técnicos
            if (acertou) {
                return "Nossa, que delícia! Valeu mesmo!";
            } else {
                return "Ei, isso não era o que eu queria... Mas deixa pra lá.";
            }
        }
    }

    /**
     * Faz uma chamada mínima à API DeepSeek e retorna true se a resposta for 200 OK.
     */
    public boolean ping() {
        Map<String, Object> body = Map.of(
            "model", "deepseek-chat",
            "messages", List.of(
                Map.of("role", "user", "content", "ping")
            ),
            "max_tokens", 1
        );
        try {
            var response = webClient.post()
                .uri("/chat/completions")
                .bodyValue(body)
                .retrieve()
                .toBodilessEntity()
                .block();
            return response != null && response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            System.err.println("Ping DeepSeek falhou: " + e.getMessage());
            return false;
        }
    }
}
