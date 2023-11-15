package com.projarq.ms2.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cepOrigem;
    private String cepDestino;

    private Double peso;
    private BigDecimal custoBase;
    private BigDecimal custoAdicional;
    private BigDecimal valorImpostos;
    private BigDecimal descontoAplicado;
    private BigDecimal valorFinal;
    private LocalDate dataCriacao;
}
