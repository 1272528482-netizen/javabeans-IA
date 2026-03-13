package com.animahub.javabeans.ia.service;

import com.animahub.javabeans.ia.dto.AcaoJogadorDTO;
import com.animahub.javabeans.ia.dto.ResultadoImersaoDTO;
import org.springframework.stereotype.Service;

@Service
public class ImmersionService {

    private final GameService gameService;

    public ImmersionService(GameService gameService) {
        this.gameService = gameService;
    }

    public ResultadoImersaoDTO avaliarImersao(AcaoJogadorDTO acao, jakarta.servlet.http.HttpSession session) {
        // Busca receita esperada do cenário atual
        var receitaEsperada = gameService.getProximoCliente(session);
        var ingredientesEsperados = receitaEsperada.ingredientesEsperados();
        var pontosBase = receitaEsperada.pontosBase();

        // Converte ingredientes enviados para enum
        java.util.List<com.animahub.javabeans.ia.model.Ingrediente> enviados;
        if (acao.getIngredientes() == null || acao.getIngredientes().isEmpty()) {
            enviados = java.util.Collections.emptyList();
        } else {
            enviados = acao.getIngredientes().stream()
                .map(String::toUpperCase)
                .map(com.animahub.javabeans.ia.model.Ingrediente::valueOf)
                .toList();
        }

        // Calcula acerto
        int total = ingredientesEsperados.size();
        int corretos = (int) enviados.stream().filter(ingredientesEsperados::contains).count();
        boolean receitaCorreta = total > 0 && corretos == total && enviados.size() == total;
        // xpGanho proporcional: pontosBase * (corretos / total)
        int xpGanho = total > 0 ? (int) Math.round(pontosBase * (corretos / (double) total)) : 0;

        boolean cenarioConcluido = true;
        return new ResultadoImersaoDTO(receitaCorreta, xpGanho, cenarioConcluido);
    }
}
