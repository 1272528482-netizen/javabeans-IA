package com.animahub.javabeans.ia.dto;

import java.util.List;

public class CenarioDTO {
    private String id;
    private String titulo;
    private String descricaoContexto;
    private String objetivoSoftSkill;

    // Novos campos para o fluxo de preparo de bebida
    private String nomeBebida;
    private List<String> ingredientesEsperados;

    public CenarioDTO() {}

    public CenarioDTO(String id, String titulo, String descricaoContexto, String objetivoSoftSkill) {
        this.id = id;
        this.titulo = titulo;
        this.descricaoContexto = descricaoContexto;
        this.objetivoSoftSkill = objetivoSoftSkill;
    }

    public CenarioDTO(String id, String titulo, String descricaoContexto, String objetivoSoftSkill,
                      String nomeBebida, List<String> ingredientesEsperados) {
        this(id, titulo, descricaoContexto, objetivoSoftSkill);
        this.nomeBebida = nomeBebida;
        this.ingredientesEsperados = ingredientesEsperados;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricaoContexto() { return descricaoContexto; }
    public void setDescricaoContexto(String descricaoContexto) { this.descricaoContexto = descricaoContexto; }
    public String getObjetivoSoftSkill() { return objetivoSoftSkill; }
    public void setObjetivoSoftSkill(String objetivoSoftSkill) { this.objetivoSoftSkill = objetivoSoftSkill; }
    public String getNomeBebida() { return nomeBebida; }
    public void setNomeBebida(String nomeBebida) { this.nomeBebida = nomeBebida; }
    public List<String> getIngredientesEsperados() { return ingredientesEsperados; }
    public void setIngredientesEsperados(List<String> ingredientesEsperados) { this.ingredientesEsperados = ingredientesEsperados; }
}
