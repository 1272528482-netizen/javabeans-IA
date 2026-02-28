package com.animahub.javabeans.ia.dto;

public class SessaoInicioDTO {
    private String sessionId;
    private CenarioDTO cenarioInicial;

    public SessaoInicioDTO() {}

    public SessaoInicioDTO(String sessionId, CenarioDTO cenarioInicial) {
        this.sessionId = sessionId;
        this.cenarioInicial = cenarioInicial;
    }

    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    public CenarioDTO getCenarioInicial() { return cenarioInicial; }
    public void setCenarioInicial(CenarioDTO cenarioInicial) { this.cenarioInicial = cenarioInicial; }
}
