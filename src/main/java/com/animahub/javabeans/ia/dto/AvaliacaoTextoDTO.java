package com.animahub.javabeans.ia.dto;

public class AvaliacaoTextoDTO {
    private String falaDoCliente;
    private String feedbackEducativo;
    private boolean cenarioConcluido;

    public AvaliacaoTextoDTO() {}

    public AvaliacaoTextoDTO(String falaDoCliente, String feedbackEducativo, boolean cenarioConcluido) {
        this.falaDoCliente = falaDoCliente;
        this.feedbackEducativo = feedbackEducativo;
        this.cenarioConcluido = cenarioConcluido;
    }

    public String getFalaDoCliente() { return falaDoCliente; }
    public void setFalaDoCliente(String falaDoCliente) { this.falaDoCliente = falaDoCliente; }
    public String getFeedbackEducativo() { return feedbackEducativo; }
    public void setFeedbackEducativo(String feedbackEducativo) { this.feedbackEducativo = feedbackEducativo; }
    public boolean isCenarioConcluido() { return cenarioConcluido; }
    public void setCenarioConcluido(boolean cenarioConcluido) { this.cenarioConcluido = cenarioConcluido; }
}