package com.projarq.ms2.application.usecases;

import com.projarq.ms2.domain.dto.OrcamentoResponseDTO;
import com.projarq.ms2.domain.service.ServicoOrcamento;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListaOrcamentos_UC {
    @Autowired
    private final ServicoOrcamento servicoOrcamento;

    public List<OrcamentoResponseDTO> getListaOrcamentos(LocalDate dataOrcamento) {
        return servicoOrcamento.getOrcamentoByDate(dataOrcamento);
    }
}
