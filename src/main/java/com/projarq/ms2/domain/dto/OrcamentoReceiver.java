package com.projarq.ms2.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrcamentoReceiver {
    private String cepOrigem;

    private String cepDestino;

    private BigDecimal peso;
}
