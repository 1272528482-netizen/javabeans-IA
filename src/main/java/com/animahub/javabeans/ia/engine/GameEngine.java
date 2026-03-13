package com.animahub.javabeans.ia.engine;

import com.animahub.javabeans.ia.dto.CenarioDTO;
import org.springframework.stereotype.Service;

@Service
public class GameEngine {

    public CenarioDTO carregarCenarioInicial() {
        return new CenarioDTO(
            "CEN-001",
            "O Cliente Apressado",
            "Um cliente entra na cafeteria pedindo um espresso para viagem, mas o sistema de pagamentos acabou de cair.",
            "Comunicação Clara e Resolução de Problemas sob Pressão"
        );
    }

    public int getTotalCenarios() {
        return 1; // Substitua pelo valor real
    }
}


