package com.animahub.javabeans.ia.dto;

import com.animahub.javabeans.ia.model.Receita;

public record ResultadoPreparar(
        boolean acertou,
        int pontosNestaRodada,
        int pontuacaoTotal,
        String feedbackIA,
        Receita proximoCliente
) {}
