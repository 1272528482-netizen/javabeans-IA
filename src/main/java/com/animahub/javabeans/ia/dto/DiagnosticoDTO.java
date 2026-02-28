package com.animahub.javabeans.ia.dto;

public class DiagnosticoDTO {

    private String statusGeral;                    // HEALTHY, DEGRADED, DOWN
    private String statusAplicacao;
    private boolean inteligenciaArtificialPronta;
    private String iaMensagem;                     // mensagem amigável sobre a IA
    private boolean bancoDadosPronto;
    private int totalSessoesCriadas;               // quantas sessões já foram iniciadas
    private int xpTotalDistribuido;                // soma de todo XP dado no jogo
    private String versaoAplicacao;                // ex: "1.0.0-MVP"
    private String ambiente;                       // development / production
    private String timestamp;

    public DiagnosticoDTO() {}

    public DiagnosticoDTO(String statusGeral, String statusAplicacao, boolean inteligenciaArtificialPronta,
                          String iaMensagem, boolean bancoDadosPronto, int totalSessoesCriadas,
                          int xpTotalDistribuido, String versaoAplicacao, String ambiente, String timestamp) {
        this.statusGeral = statusGeral;
        this.statusAplicacao = statusAplicacao;
        this.inteligenciaArtificialPronta = inteligenciaArtificialPronta;
        this.iaMensagem = iaMensagem;
        this.bancoDadosPronto = bancoDadosPronto;
        this.totalSessoesCriadas = totalSessoesCriadas;
        this.xpTotalDistribuido = xpTotalDistribuido;
        this.versaoAplicacao = versaoAplicacao;
        this.ambiente = ambiente;
        this.timestamp = timestamp;
    }

    // Getters e Setters
    public String getStatusGeral() { return statusGeral; }
    public void setStatusGeral(String statusGeral) { this.statusGeral = statusGeral; }
    public String getStatusAplicacao() { return statusAplicacao; }
    public void setStatusAplicacao(String statusAplicacao) { this.statusAplicacao = statusAplicacao; }
    public boolean isInteligenciaArtificialPronta() { return inteligenciaArtificialPronta; }
    public void setInteligenciaArtificialPronta(boolean inteligenciaArtificialPronta) { this.inteligenciaArtificialPronta = inteligenciaArtificialPronta; }
    public String getIaMensagem() { return iaMensagem; }
    public void setIaMensagem(String iaMensagem) { this.iaMensagem = iaMensagem; }
    public boolean isBancoDadosPronto() { return bancoDadosPronto; }
    public void setBancoDadosPronto(boolean bancoDadosPronto) { this.bancoDadosPronto = bancoDadosPronto; }
    public int getTotalSessoesCriadas() { return totalSessoesCriadas; }
    public void setTotalSessoesCriadas(int totalSessoesCriadas) { this.totalSessoesCriadas = totalSessoesCriadas; }
    public int getXpTotalDistribuido() { return xpTotalDistribuido; }
    public void setXpTotalDistribuido(int xpTotalDistribuido) { this.xpTotalDistribuido = xpTotalDistribuido; }
    public String getVersaoAplicacao() { return versaoAplicacao; }
    public void setVersaoAplicacao(String versaoAplicacao) { this.versaoAplicacao = versaoAplicacao; }
    public String getAmbiente() { return ambiente; }
    public void setAmbiente(String ambiente) { this.ambiente = ambiente; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
