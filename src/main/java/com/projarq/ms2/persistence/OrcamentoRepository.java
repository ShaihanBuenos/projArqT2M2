package com.projarq.ms2.persistence;


import com.projarq.ms2.domain.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

    Optional<Orcamento> findByDataCriacao (LocalDate dataCriacao);
}
