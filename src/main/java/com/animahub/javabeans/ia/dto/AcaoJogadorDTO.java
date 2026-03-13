package com.animahub.javabeans.ia.dto;

import java.util.List;

public class AcaoJogadorDTO {
    private String sessionId;
    private String resposta;
    private List<String> ingredientes;

    public AcaoJogadorDTO() {}

    public AcaoJogadorDTO(String sessionId, String resposta, List<String> ingredientes) {
        this.sessionId = sessionId;
        this.resposta = resposta;
        this.ingredientes = ingredientes;
    }

    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    public String getResposta() { return resposta; }
    public void setResposta(String resposta) { this.resposta = resposta; }
    public List<String> getIngredientes() { return ingredientes; }
    public void setIngredientes(List<String> ingredientes) { this.ingredientes = ingredientes; }
}
