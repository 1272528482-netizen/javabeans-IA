package com.animahub.javabeans.ia.dto;

public class ProcessarFalaResponseDTO {
    private CenarioDTO cenario;
    private AvaliacaoTextoDTO avaliacao;

    public ProcessarFalaResponseDTO() {}

    public ProcessarFalaResponseDTO(CenarioDTO cenario, AvaliacaoTextoDTO avaliacao) {
        this.cenario = cenario;
        this.avaliacao = avaliacao;
    }

    public CenarioDTO getCenario() {
        return cenario;
    }

    public void setCenario(CenarioDTO cenario) {
        this.cenario = cenario;
    }

    public AvaliacaoTextoDTO getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(AvaliacaoTextoDTO avaliacao) {
        this.avaliacao = avaliacao;
    }
}