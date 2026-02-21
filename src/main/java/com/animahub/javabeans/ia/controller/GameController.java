package com.animahub.javabeans.ia.controller;

import com.animahub.javabeans.ia.dto.CenarioDTO;
import com.animahub.javabeans.ia.dto.AvaliacaoDTO;
import com.animahub.javabeans.ia.dto.AcaoJogadorDTO;
import com.animahub.javabeans.ia.dto.DiagnosticoDTO;
import com.animahub.javabeans.ia.engine.GameEngine;
import com.animahub.javabeans.ia.repository.SessaoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/game")
public class GameController {
    
    private final GameEngine gameEngine;
    private final SessaoRepository sessaoRepository; // Injetando o Banco de Dados

    public GameController(GameEngine gameEngine, SessaoRepository sessaoRepository) {
        this.gameEngine = gameEngine;
        this.sessaoRepository = sessaoRepository;
    }
    
    @GetMapping("/health")
    public String health() {
        return "Game is running!";
    }
    
    @GetMapping("/cenario")
    public CenarioDTO cenario() {
        return gameEngine.carregarCenarioInicial();
    }

    @PostMapping("/avaliar")
    public AvaliacaoDTO avaliarJogada(@RequestBody AcaoJogadorDTO acao) {
        return gameEngine.processarTurno(acao.getSessionId(), acao.getResposta());
    }

    @GetMapping("/diagnostico")
    public DiagnosticoDTO diagnostico() {
        // Verifica a IA
        String apiKey = System.getenv("DEEPSEEK_API_KEY");
        boolean iaPronta = (apiKey != null && !apiKey.isBlank());
        
        // Verifica o Banco de Dados (H2)
        boolean dbPronto;
        try {
            sessaoRepository.count(); // Tenta contar as linhas para testar a conexão
            dbPronto = true;
        } catch (Exception e) {
            dbPronto = false;
        }
        
        return new DiagnosticoDTO(
            "ONLINE", 
            iaPronta, 
            dbPronto,
            LocalDateTime.now().toString()
        );
    }
}
