package com.animahub.javabeans.ia.dto;

public class DominioDTO {
        private int totalCenariosNarrativos;
        private int totalReceitasJogaveis;
    private boolean cenarioInicialDisponivel;
    private boolean persistenciaSessaoOk;
    private int totalCenariosConfigurados;
    private int totalReceitasDisponiveis;

    public DominioDTO() {}

    public DominioDTO(boolean cenarioInicialDisponivel, boolean persistenciaSessaoOk, int totalCenariosConfigurados, int totalReceitasDisponiveis) {
        this.cenarioInicialDisponivel = cenarioInicialDisponivel;
        this.persistenciaSessaoOk = persistenciaSessaoOk;
        this.totalCenariosConfigurados = totalCenariosConfigurados;
        this.totalReceitasDisponiveis = totalReceitasDisponiveis;
    }

    public boolean isCenarioInicialDisponivel() { return cenarioInicialDisponivel; }
    public void setCenarioInicialDisponivel(boolean cenarioInicialDisponivel) { this.cenarioInicialDisponivel = cenarioInicialDisponivel; }
    public boolean isPersistenciaSessaoOk() { return persistenciaSessaoOk; }
    public void setPersistenciaSessaoOk(boolean persistenciaSessaoOk) { this.persistenciaSessaoOk = persistenciaSessaoOk; }
    public int getTotalCenariosConfigurados() { return totalCenariosConfigurados; }
    public void setTotalCenariosConfigurados(int totalCenariosConfigurados) { this.totalCenariosConfigurados = totalCenariosConfigurados; }
    public int getTotalReceitasDisponiveis() { return totalReceitasDisponiveis; }
    public void setTotalReceitasDisponiveis(int totalReceitasDisponiveis) { this.totalReceitasDisponiveis = totalReceitasDisponiveis; }

    public void setTotalCenariosNarrativos(int totalCenariosNarrativos) {
        this.totalCenariosNarrativos = totalCenariosNarrativos;
    }

    public void setTotalReceitasJogaveis(int totalReceitasJogaveis) {
        this.totalReceitasJogaveis = totalReceitasJogaveis;
    }
}