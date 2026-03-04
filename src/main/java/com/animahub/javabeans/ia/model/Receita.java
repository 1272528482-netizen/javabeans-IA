package com.animahub.javabeans.ia.model;

import java.util.List;

public record Receita(
        String nomeBebida,
        List<Ingrediente> ingredientesEsperados,
        int pontosBase
) {}
