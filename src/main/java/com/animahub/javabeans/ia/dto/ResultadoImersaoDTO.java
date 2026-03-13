package com.animahub.javabeans.ia.dto;

public class ResultadoImersaoDTO {
    private boolean receitaCorreta;
    private int xpGanho;
    private boolean cenarioConcluido;

    public ResultadoImersaoDTO() {}

    public ResultadoImersaoDTO(boolean receitaCorreta, int xpGanho, boolean cenarioConcluido) {
        this.receitaCorreta = receitaCorreta;
        this.xpGanho = xpGanho;
        this.cenarioConcluido = cenarioConcluido;
    }

    public boolean isReceitaCorreta() { return receitaCorreta; }
    public void setReceitaCorreta(boolean receitaCorreta) { this.receitaCorreta = receitaCorreta; }
    public int getXpGanho() { return xpGanho; }
    public void setXpGanho(int xpGanho) { this.xpGanho = xpGanho; }
    public boolean isCenarioConcluido() { return cenarioConcluido; }
    public void setCenarioConcluido(boolean cenarioConcluido) { this.cenarioConcluido = cenarioConcluido; }
}