package com.animahub.javabeans.ia.dto;

public class DiagnosticoDTO {

    private String statusGeral;
    private String statusAplicacao;
    private String versaoAplicacao;
    private String ambiente;
    private String timestamp;
    private long uptimeSegundos;
    private DependenciaDTO ia;
    private DependenciaDTO banco;
    private DominioDTO dominio;
    private int totalSessoesCriadas;
    private int xpTotalDistribuido;

    public DiagnosticoDTO() {}

    public DiagnosticoDTO(String statusGeral, String statusAplicacao, String versaoAplicacao,
                          String ambiente, String timestamp, long uptimeSegundos,
                          DependenciaDTO ia, DependenciaDTO banco, DominioDTO dominio,
                          int totalSessoesCriadas, int xpTotalDistribuido) {
        this.statusGeral = statusGeral;
        this.statusAplicacao = statusAplicacao;
        this.versaoAplicacao = versaoAplicacao;
        this.ambiente = ambiente;
        this.timestamp = timestamp;
        this.uptimeSegundos = uptimeSegundos;
        this.ia = ia;
        this.banco = banco;
        this.dominio = dominio;
        this.totalSessoesCriadas = totalSessoesCriadas;
        this.xpTotalDistribuido = xpTotalDistribuido;
    }

    public String getStatusGeral() { return statusGeral; }
    public void setStatusGeral(String statusGeral) { this.statusGeral = statusGeral; }
    public String getStatusAplicacao() { return statusAplicacao; }
    public void setStatusAplicacao(String statusAplicacao) { this.statusAplicacao = statusAplicacao; }
    public String getVersaoAplicacao() { return versaoAplicacao; }
    public void setVersaoAplicacao(String versaoAplicacao) { this.versaoAplicacao = versaoAplicacao; }
    public String getAmbiente() { return ambiente; }
    public void setAmbiente(String ambiente) { this.ambiente = ambiente; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public long getUptimeSegundos() { return uptimeSegundos; }
    public void setUptimeSegundos(long uptimeSegundos) { this.uptimeSegundos = uptimeSegundos; }
    public DependenciaDTO getIa() { return ia; }
    public void setIa(DependenciaDTO ia) { this.ia = ia; }
    public DependenciaDTO getBanco() { return banco; }
    public void setBanco(DependenciaDTO banco) { this.banco = banco; }
    public DominioDTO getDominio() { return dominio; }
    public void setDominio(DominioDTO dominio) { this.dominio = dominio; }
    public int getTotalSessoesCriadas() { return totalSessoesCriadas; }
    public void setTotalSessoesCriadas(int totalSessoesCriadas) { this.totalSessoesCriadas = totalSessoesCriadas; }
    public int getXpTotalDistribuido() { return xpTotalDistribuido; }
    public void setXpTotalDistribuido(int xpTotalDistribuido) { this.xpTotalDistribuido = xpTotalDistribuido; }
}
