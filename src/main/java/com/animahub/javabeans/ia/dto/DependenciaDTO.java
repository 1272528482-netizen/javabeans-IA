package com.animahub.javabeans.ia.dto;

public class DependenciaDTO {
    private boolean pronto;
    private String status;
    private String mensagem;
    private long latenciaMs;

    public DependenciaDTO() {}

    public DependenciaDTO(boolean pronto, String status, String mensagem, long latenciaMs) {
        this.pronto = pronto;
        this.status = status;
        this.mensagem = mensagem;
        this.latenciaMs = latenciaMs;
    }

    public boolean isPronto() { return pronto; }
    public void setPronto(boolean pronto) { this.pronto = pronto; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public long getLatenciaMs() { return latenciaMs; }
    public void setLatenciaMs(long latenciaMs) { this.latenciaMs = latenciaMs; }
}