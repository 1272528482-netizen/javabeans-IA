package com.animahub.javabeans.ia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class DeepseekConfig {

    private String endpoint = "https://api.deepseek.com/v1";
    private String apiKey = "sk-fed2ac352da0422a9f512dcd740ec6a7";

    public String getEndpoint() {
        return endpoint;
    }

    public String getApiKey() {
        return apiKey;
    }
}