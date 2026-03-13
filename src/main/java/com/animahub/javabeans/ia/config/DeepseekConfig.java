package com.animahub.javabeans.ia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class DeepseekConfig {

    private String endpoint = "https://api.deepseek.com/v1";
    @Value("${deepseek.api.key}")
    private String apiKey;

    public String getEndpoint() {
        return endpoint;
    }

    public String getApiKey() {
        return apiKey;
    }
}