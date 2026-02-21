package com.animahub.javabeans.ia.repository;

import com.animahub.javabeans.ia.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    // Busca automática de uma sessão baseada no UUID do jogador
    Optional<Sessao> findBySessionId(String sessionId);
}
