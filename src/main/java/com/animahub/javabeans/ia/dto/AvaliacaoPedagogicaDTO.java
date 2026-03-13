package com.animahub.javabeans.ia.dto;

public class AvaliacaoPedagogicaDTO {
    private String falaDoCliente;
    private String feedbackEducativo;
    private boolean ofensaDetectada;

    public AvaliacaoPedagogicaDTO() {}

    public AvaliacaoPedagogicaDTO(String falaDoCliente, String feedbackEducativo, boolean ofensaDetectada) {
        this.falaDoCliente = falaDoCliente;
        this.feedbackEducativo = feedbackEducativo;
        this.ofensaDetectada = ofensaDetectada;
    }

    public String getFalaDoCliente() { return falaDoCliente; }
    public void setFalaDoCliente(String falaDoCliente) { this.falaDoCliente = falaDoCliente; }
    public String getFeedbackEducativo() { return feedbackEducativo; }
    public void setFeedbackEducativo(String feedbackEducativo) { this.feedbackEducativo = feedbackEducativo; }
    public boolean isOfensaDetectada() { return ofensaDetectada; }
    public void setOfensaDetectada(boolean ofensaDetectada) { this.ofensaDetectada = ofensaDetectada; }
}