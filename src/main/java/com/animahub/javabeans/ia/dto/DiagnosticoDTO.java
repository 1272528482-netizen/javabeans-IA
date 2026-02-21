package com.animahub.javabeans.ia.dto;

public class DiagnosticoDTO {
    private String statusAplicacao;
    private boolean inteligenciaArtificialPronta;
    private boolean bancoDeDadosPronto;
    private String timestamp;

    public DiagnosticoDTO() {}

    public DiagnosticoDTO(String statusAplicacao, boolean inteligenciaArtificialPronta, boolean bancoDeDadosPronto, String timestamp) {
        this.statusAplicacao = statusAplicacao;
        this.inteligenciaArtificialPronta = inteligenciaArtificialPronta;
        this.bancoDeDadosPronto = bancoDeDadosPronto;
        this.timestamp = timestamp;
    }

    public String getStatusAplicacao() { return statusAplicacao; }
    public void setStatusAplicacao(String statusAplicacao) { this.statusAplicacao = statusAplicacao; }
    public boolean isInteligenciaArtificialPronta() { return inteligenciaArtificialPronta; }
    public void setInteligenciaArtificialPronta(boolean inteligenciaArtificialPronta) { this.inteligenciaArtificialPronta = inteligenciaArtificialPronta; }
    public boolean isBancoDeDadosPronto() { return bancoDeDadosPronto; }
    public void setBancoDeDadosPronto(boolean bancoDeDadosPronto) { this.bancoDeDadosPronto = bancoDeDadosPronto; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
