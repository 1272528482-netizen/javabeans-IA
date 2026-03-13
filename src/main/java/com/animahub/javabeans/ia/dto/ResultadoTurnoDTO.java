package com.animahub.javabeans.ia.dto;

public class ResultadoTurnoDTO {
    private ResultadoImersaoDTO imersao;
    private AvaliacaoPedagogicaDTO pedagogia;

    public ResultadoTurnoDTO() {}

    public ResultadoTurnoDTO(ResultadoImersaoDTO imersao, AvaliacaoPedagogicaDTO pedagogia) {
        this.imersao = imersao;
        this.pedagogia = pedagogia;
    }

    public ResultadoImersaoDTO getImersao() { return imersao; }
    public void setImersao(ResultadoImersaoDTO imersao) { this.imersao = imersao; }
    public AvaliacaoPedagogicaDTO getPedagogia() { return pedagogia; }
    public void setPedagogia(AvaliacaoPedagogicaDTO pedagogia) { this.pedagogia = pedagogia; }
}