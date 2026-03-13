package com.animahub.javabeans.ia.engine;

import com.animahub.javabeans.ia.dto.CenarioDTO;
import com.animahub.javabeans.ia.dto.AvaliacaoDTO;
import com.animahub.javabeans.ia.service.AIService;
import org.springframework.stereotype.Service;

@Service
public class GameEngine {

    private final SessionEngine sessionEngine;
    private final AIService aiService;

    // Injetamos as dependências automaticamente
    public GameEngine(SessionEngine sessionEngine, AIService aiService) {
        this.sessionEngine = sessionEngine;
        this.aiService = aiService;
    }

    public CenarioDTO carregarCenarioInicial() {
        return new CenarioDTO(
            "CEN-001",
            "O Cliente Apressado",
            "Um cliente entra na cafeteria pedindo um espresso para viagem, mas o sistema de pagamentos acabou de cair.",
            "Comunicação Clara e Resolução de Problemas sob Pressão"
        );
    }

    public AvaliacaoDTO processarTurno(String sessionId, String respostaAluno) {
        // Este método deve retornar apenas feedback parcial (soft skill) para a fala do jogador.
        // O cálculo de XP / hard skills só acontece no prepararCafe do GameService.
        return avaliarSoftSkill(sessionId, respostaAluno);
    }

    private AvaliacaoDTO avaliarSoftSkill(String sessionId, String respostaAluno) {
        if (!sessionEngine.isSessaoValida(sessionId)) {
            throw new IllegalArgumentException("Sessão inválida ou expirada.");
        }

        AvaliacaoDTO avaliacao = aiService.avaliarResposta(respostaAluno);

        // Regra de Segurança: Força XP zero e cenário não concluído para evitar encerrar rodada apenas com fala.
        avaliacao.setXpGanho(0);
        avaliacao.setCenarioConcluido(false);

        return avaliacao;
    }
}


