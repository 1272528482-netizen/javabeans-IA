package com.animahub.javabeans.ia.controller;

import com.animahub.javabeans.ia.dto.AcaoJogadorDTO;
import com.animahub.javabeans.ia.dto.CenarioDTO;
import com.animahub.javabeans.ia.dto.DiagnosticoDTO;
import com.animahub.javabeans.ia.dto.ResultadoTurnoDTO;
import com.animahub.javabeans.ia.service.GameOrchestratorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameOrchestratorService gameOrchestratorService;

    public GameController(GameOrchestratorService gameOrchestratorService) {
        this.gameOrchestratorService = gameOrchestratorService;
    }

    @GetMapping("/health")
    public ResponseEntity<DiagnosticoDTO> health() {
        return ResponseEntity.ok(gameOrchestratorService.gerarDiagnostico());
    }

    @GetMapping("/cenario")
    public ResponseEntity<CenarioDTO> getCenario(HttpSession session) {
        CenarioDTO cenario = gameOrchestratorService.getCenarioAtual(session);
        return ResponseEntity.ok(cenario);
    }

    @GetMapping("/scenario/initial")
    public ResponseEntity<CenarioDTO> getScenarioInitial(HttpSession session) {
        return getCenario(session);
    }

    @PostMapping("/avaliar")
    public ResponseEntity<ResultadoTurnoDTO> avaliar(@RequestBody AcaoJogadorDTO acao) {
        return ResponseEntity.ok(gameOrchestratorService.avaliarTurnoCompleto(acao));
    }

    @PostMapping("/cafe/prepare")
    public ResponseEntity<ResultadoTurnoDTO> prepareCafe(@RequestBody AcaoJogadorDTO acao) {
        return avaliar(acao);
    }

    @GetMapping("/debug-sessao")
    public ResponseEntity<Map<String, Object>> debugSessao(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", session.getId());
        map.put("cenarioAtual", session.getAttribute("cenarioAtual"));
        map.put("pontuacaoTotal", session.getAttribute("pontuacaoTotal"));
        return ResponseEntity.ok(map);
    }
}
