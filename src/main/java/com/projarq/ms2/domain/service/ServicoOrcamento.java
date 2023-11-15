package com.projarq.ms2.domain.service;

import com.projarq.ms2.domain.dto.OrcamentoResponseDTO;
import com.projarq.ms2.persistence.OrcamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoOrcamento {

    @Autowired
    private final OrcamentoRepository orcamentoRepository;

    public List<OrcamentoResponseDTO> getOrcamentoByDate(LocalDate dataCriacao) {
        return orcamentoRepository.findByDataCriacao(dataCriacao)
                .stream()
                .map(OrcamentoResponseDTO::toDTO)
                .toList();
    }
}
