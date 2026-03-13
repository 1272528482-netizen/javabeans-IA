package com.animahub.javabeans.ia.prompt;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class PromptTemplateService {

    public String loadPrompt(String path) {
        try (InputStream is = new ClassPathResource("prompts/" + path).getInputStream()) {
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Erro ao carregar prompt: " + path, e);
        }
    }

    public String renderPrompt(String path, Map<String, String> variables) {
        String template = loadPrompt(path);
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            template = template.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return template;
    }
}
