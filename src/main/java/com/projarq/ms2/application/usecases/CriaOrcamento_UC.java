package com.projarq.ms2.application.usecases;

import com.projarq.ms2.domain.Orcamento;
import com.projarq.ms2.domain.dto.OrcamentoResponseDTO;
import com.projarq.ms2.domain.service.ServicoOrcamento;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CriaOrcamento_UC {

    @Autowired
    private final ServicoOrcamento servicoOrcamento;

    public OrcamentoResponseDTO criarOrcamento(String cepOrigem, String cepDestino, BigDecimal peso) {
        Orcamento orcamento = servicoOrcamento.criarOrcamento(cepOrigem, cepDestino, peso);

        return OrcamentoResponseDTO.toDTO(orcamento);
    }
}
