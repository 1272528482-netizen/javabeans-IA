package com.animahub.javabeans.ia.service;

import com.animahub.javabeans.ia.dto.AcaoJogadorDTO;
import com.animahub.javabeans.ia.dto.AvaliacaoPedagogicaDTO;
import com.animahub.javabeans.ia.dto.CenarioDTO;
import com.animahub.javabeans.ia.dto.DependenciaDTO;
import com.animahub.javabeans.ia.dto.DiagnosticoDTO;
import com.animahub.javabeans.ia.dto.DominioDTO;
import com.animahub.javabeans.ia.dto.ResultadoImersaoDTO;
import com.animahub.javabeans.ia.dto.ResultadoTurnoDTO;
import com.animahub.javabeans.ia.model.Receita;
import com.animahub.javabeans.ia.service.GameService;
import com.animahub.javabeans.ia.config.DeepseekConfig;
import com.animahub.javabeans.ia.engine.GameEngine;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class GameOrchestratorService {

    private final ImmersionService immersionService;
    private final AIService aiService;
    private final SessionService sessionService;
    private final GameService gameService;
    private final DeepseekConfig deepseekConfig;
    private final GameEngine gameEngine;

    public GameOrchestratorService(ImmersionService immersionService,
                                   AIService aiService,
                                   SessionService sessionService,
                                   GameService gameService,
                                   DeepseekConfig deepseekConfig,
                                   GameEngine gameEngine) {
        this.immersionService = immersionService;
        this.aiService = aiService;
        this.sessionService = sessionService;
        this.gameService = gameService;
        this.deepseekConfig = deepseekConfig;
        this.gameEngine = gameEngine;
    }

    public ResultadoTurnoDTO avaliarTurnoCompleto(AcaoJogadorDTO acao) {
        // Passo A: Hard skills (imersão)
        // Precisa do HttpSession para buscar receita
        // (session deve ser obtido ou passado pelo caller)
        // Aqui, assume que session está disponível (ajuste conforme uso real)
        jakarta.servlet.http.HttpSession session = null;
        ResultadoImersaoDTO imersao = immersionService.avaliarImersao(acao, session);

        // Passo B: Soft skills (pedagogia)
        AvaliacaoPedagogicaDTO pedagogia = aiService.avaliarResposta(acao.getResposta());

        // Passo C: Regra de Negócio Soberana (ofensa zera pontos)
        if (pedagogia.isOfensaDetectada()) {
            imersao.setXpGanho(0);
        }

        // Passo D: Atualiza progresso na sessão
        sessionService.atualizarProgresso(acao.getSessionId(), imersao.getXpGanho());

        // Passo E: Retorna pacote consolidado
        return new ResultadoTurnoDTO(imersao, pedagogia);
    }

    public CenarioDTO getCenarioAtual(HttpSession session) {
        Receita receita = gameService.getProximoCliente(session);
        CenarioDTO cenario = new CenarioDTO();
        cenario.setNomeBebida(receita.nomeBebida());
        cenario.setIngredientesEsperados(
                receita.ingredientesEsperados().stream().map(Enum::name).collect(Collectors.toList())
        );
        return cenario;
    }

    public DiagnosticoDTO gerarDiagnostico() {
            int totalCenariosConfigurados = gameEngine.getTotalCenarios();
            int totalReceitasDisponiveis = gameService.getTotalReceitasDisponiveis();
        // Uptime
        long uptimeSegundos = java.lang.management.ManagementFactory.getRuntimeMXBean().getUptime() / 1000;

        // IA: Diagnóstico real
        boolean iaPronto = false;
        String iaStatus = "DOWN";
        String iaDetalhe = "Ausente";
        long iaLatencia = 0;
        String endpoint = deepseekConfig.getEndpoint();
        String apiKey = deepseekConfig.getApiKey();
        if (endpoint != null && !endpoint.isBlank() && apiKey != null && !apiKey.isBlank()) {
            long inicioPing = System.currentTimeMillis();
            iaPronto = aiService.ping();
            iaLatencia = System.currentTimeMillis() - inicioPing;
            if (iaPronto) {
                iaStatus = "UP";
                iaDetalhe = "Conectada";
            } else {
                iaStatus = "CONFIGURED_BUT_UNREACHABLE";
                iaDetalhe = "Falha de conexão";
            }
        }
        DependenciaDTO ia = new DependenciaDTO(
            iaPronto,
            iaStatus,
            iaDetalhe,
            iaLatencia
        );

        // Banco
        boolean bancoPronto = false;
        long bancoLatencia = 0;
        long totalSessoes = 0;
        int xpTotal = 0;
        try {
            long inicio = System.currentTimeMillis();
            totalSessoes = sessionService.contarSessoes();
            xpTotal = sessionService.calcularXpTotal();
            bancoLatencia = System.currentTimeMillis() - inicio;
            bancoPronto = true;
        } catch (Exception e) {
            bancoPronto = false;
            bancoLatencia = 0;
        }
        DependenciaDTO banco = new DependenciaDTO(
            bancoPronto,
            bancoPronto ? "UP" : "DOWN",
            bancoPronto ? "Conectado" : "Erro de conexão",
            bancoLatencia
        );

        // Domínio
        int totalCenariosNarrativos = 1; // Default
        int totalReceitasJogaveis = 0;
        // Obtém total de cenários narrativos da GameEngine
        try {
            // Supondo que GameEngine tem método para contar cenários narrativos
            if (gameEngine != null) {
                // Exemplo: gameEngine.getTotalCenariosNarrativos()
                totalCenariosNarrativos = 1; // Ajuste para chamada real se existir
            }
        } catch (Exception e) {
            totalCenariosNarrativos = 1;
        }
        // Obtém total de receitas jogáveis do GameService
        try {
            if (gameService != null) {
                java.lang.reflect.Field f = gameService.getClass().getDeclaredField("cenarios");
                f.setAccessible(true);
                java.util.Map<?, ?> receitas = (java.util.Map<?, ?>) f.get(gameService);
                totalReceitasJogaveis = receitas != null ? receitas.size() : 0;
            }
        } catch (Exception e) {
            totalReceitasJogaveis = 0;
        }
        // Obtém total de cenários narrativos da GameEngine
        try {
            // Supondo que GameEngine tem método para contar cenários
            if (gameEngine != null && gameEngine instanceof com.animahub.javabeans.ia.engine.GameEngine) {
                // Exemplo: gameEngine.getTotalCenariosNarrativos()
                totalCenariosConfigurados = 1; // Ajuste para chamada real se existir
            }
        } catch (Exception e) {
            totalCenariosConfigurados = 1;
        }
        // Obtém total de receitas do GameService
        try {
            if (gameService != null) {
                java.lang.reflect.Field f = gameService.getClass().getDeclaredField("cenarios");
                f.setAccessible(true);
                java.util.Map<?, ?> receitas = (java.util.Map<?, ?>) f.get(gameService);
                totalReceitasDisponiveis = receitas != null ? receitas.size() : 0;
            }
        } catch (Exception e) {
            totalReceitasDisponiveis = 0;
        }
        DominioDTO dominio = new DominioDTO();
        dominio.setCenarioInicialDisponivel(true);
        dominio.setPersistenciaSessaoOk(bancoPronto);
        dominio.setTotalCenariosNarrativos(totalCenariosNarrativos);
        dominio.setTotalReceitasJogaveis(totalReceitasJogaveis);

        // Status Geral
        String statusGeral;
        if (iaPronto && bancoPronto) {
            statusGeral = "HEALTHY";
        } else if (iaPronto || bancoPronto) {
            statusGeral = "DEGRADED";
        } else {
            statusGeral = "DOWN";
        }

        // Diagnóstico final
        return new DiagnosticoDTO(
            statusGeral,
            "RUNNING",
            "2.0.0-ORCHESTRATOR",
            "development",
            java.time.LocalDateTime.now().toString(),
            uptimeSegundos,
            ia,
            banco,
            dominio,
            (int) totalSessoes,
            xpTotal
        );
    }
}
