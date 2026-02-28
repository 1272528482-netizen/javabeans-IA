package com.animahub.javabeans.ia.controller;

import com.animahub.javabeans.ia.dto.AcaoJogadorDTO;
import com.animahub.javabeans.ia.dto.AvaliacaoDTO;
import com.animahub.javabeans.ia.dto.CenarioDTO;
import com.animahub.javabeans.ia.dto.DiagnosticoDTO;
import com.animahub.javabeans.ia.dto.SessaoInicioDTO;
import com.animahub.javabeans.ia.engine.GameEngine;
import com.animahub.javabeans.ia.repository.SessaoRepository;
import com.animahub.javabeans.ia.service.SessionService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameEngine gameEngine;
    private final SessaoRepository sessaoRepository;
    private final SessionService sessionService;

    public GameController(GameEngine gameEngine,
                          SessaoRepository sessaoRepository,
                          SessionService sessionService) {
        this.gameEngine = gameEngine;
        this.sessaoRepository = sessaoRepository;
        this.sessionService = sessionService;
    }

    @GetMapping("/health")
    public String health() {
        return "Game is running!";
    }

    @GetMapping("/iniciar-sessao")
    public SessaoInicioDTO iniciarSessao() {
        return sessionService.iniciarNovaSessao();
    }

    @GetMapping("/cenario")
    public CenarioDTO cenario() {
        return gameEngine.carregarCenarioInicial();
    }

    @PostMapping("/avaliar")
    public AvaliacaoDTO avaliarJogada(@RequestBody AcaoJogadorDTO acao) {
        AvaliacaoDTO avaliacao = gameEngine.processarTurno(acao.getSessionId(), acao.getResposta());
        
        if (avaliacao.getXpGanho() > 0) {
            sessionService.atualizarProgresso(acao.getSessionId(), avaliacao.getXpGanho());
        }
        
        return avaliacao;
    }

    @GetMapping("/diagnostico")
    public DiagnosticoDTO diagnostico() {
        String apiKey = System.getenv("DEEPSEEK_API_KEY");
        boolean iaPronta = (apiKey != null && !apiKey.isBlank());
        String iaMensagem = iaPronta ? "Conectado com sucesso ao DeepSeek" : "Chave API não encontrada";

        boolean dbPronto = true;
        long totalSessoes = 0;
        int xpTotal = 0;
        try {
            totalSessoes = sessaoRepository.count();
            xpTotal = sessaoRepository.findAll()
                    .stream()
                    .mapToInt(s -> s.getXpTotal())
                    .sum();
        } catch (Exception e) {
            dbPronto = false;
        }

        return new DiagnosticoDTO(
            iaPronta && dbPronto ? "HEALTHY" : "DEGRADED",   // statusGeral
            "ONLINE",
            iaPronta,
            iaMensagem,
            dbPronto,
            (int) totalSessoes,
            xpTotal,
            "1.0.0-MVP",
            "development",
            LocalDateTime.now().toString()
        );
    }
}
