package com.projarq.ms2.domain.dto;

import com.projarq.ms2.domain.Orcamento;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class OrcamentoResponseDTO {

    private Long id;

    private String origem;

    private String destino;

    private Double peso;

    private BigDecimal custoBase;

    private BigDecimal custoAdicional;

    private BigDecimal valorImpostos;

    private BigDecimal descontoAplicado;

    private BigDecimal valorFinal;

    private LocalDate dataCriacao;

    public static OrcamentoResponseDTO toDTO(Orcamento orcamento) {
        return OrcamentoResponseDTO.builder()
                .id(orcamento.getId())
                .origem(orcamento.getCepOrigem())
                .destino(orcamento.getCepDestino())
                .peso(orcamento.getPeso())
                .custoBase(orcamento.getCustoBase())
                .custoAdicional(orcamento.getCustoAdicional())
                .valorImpostos(orcamento.getValorImpostos())
                .descontoAplicado(orcamento.getDescontoAplicado())
                .valorFinal(orcamento.getValorFinal())
                .dataCriacao(orcamento.getDataCriacao())
                .build();
    }
}
