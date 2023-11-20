package com.projarq.ms2.domain.service;

import com.projarq.ms2.application.usecases.CEPEncontrarPorNumero_UC;
import com.projarq.ms2.domain.CEP;
import com.projarq.ms2.domain.Cidade;
import com.projarq.ms2.domain.Orcamento;
import com.projarq.ms2.domain.dto.OrcamentoResponseDTO;
import com.projarq.ms2.persistence.OrcamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicoOrcamento {

    @Autowired
    private final OrcamentoRepository orcamentoRepository;

    @Autowired
    CEPEncontrarPorNumero_UC cepEncontrarPorNumero_uc;

    public List<OrcamentoResponseDTO> getOrcamentoByDate(LocalDate dataCriacao) {
        return orcamentoRepository.findByDataCriacao(dataCriacao)
                .stream()
                .map(OrcamentoResponseDTO::toDTO)
                .toList();
    }

    public Orcamento criarOrcamento(String cepOrigem, String cepDestino, BigDecimal peso) {
        Optional<CEP> origem = cepEncontrarPorNumero_uc.findByNumero(cepOrigem);
        Optional<CEP> destino = cepEncontrarPorNumero_uc.findByNumero(cepDestino);

        if (origem.isEmpty() || destino.isEmpty()) {
            throw new IllegalArgumentException("CEP não encontrado");
        }

        BigDecimal custoBase = origem.get().getCidade().getPrecoBase();
        BigDecimal custoAdicional = calcularCustoAdicional(peso);
        BigDecimal valorImpostos = origem.get().getCidade().getPrecoImposto() != null ?
                origem.get().getCidade().getPrecoImposto() : new BigDecimal("0.05");

        BigDecimal descontoAplicado = calcularDesconto(origem.get().getCidade(), peso);

        BigDecimal valorFinal = custoBase.add(custoAdicional).setScale(2);
        BigDecimal valorImpostoTotal = valorFinal.multiply(valorImpostos);
        valorFinal = valorFinal.add(valorImpostoTotal);

        valorFinal = valorFinal.subtract(descontoAplicado);

        Orcamento orcamento = Orcamento.builder()
                .origem(origem.get())
                .destino(destino.get())
                .peso(peso.doubleValue())
                .custoBase(custoBase)
                .custoAdicional(custoAdicional)
                .valorImpostos(valorImpostos)
                .descontoAplicado(descontoAplicado)
                .valorFinal(valorFinal)
                .dataCriacao(LocalDate.now())
                .build();

        return orcamentoRepository.save(orcamento);
    }

    private BigDecimal calcularDesconto(Cidade cidadeOrigem, BigDecimal peso) {
        if (cidadeOrigem.getNome().equals("Porto Alegre") && peso.compareTo(new BigDecimal("5.0")) >= 0) {
            return new BigDecimal("10.0");
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal calcularCustoAdicional(BigDecimal peso) {
        if (peso.compareTo(BigDecimal.valueOf(2000)) <= 0) {
            return BigDecimal.ZERO;
        } else if (peso.compareTo(BigDecimal.valueOf(12000)) <= 0) {
            return peso.subtract(BigDecimal.valueOf(2000)).multiply(BigDecimal.valueOf(10));
        } else {
            BigDecimal pesoAte12Kg = BigDecimal.valueOf(10000); // 12Kg - 2Kg
            BigDecimal restante = peso.subtract(BigDecimal.valueOf(12000));
            return pesoAte12Kg.multiply(BigDecimal.valueOf(10)).add(restante.multiply(BigDecimal.valueOf(15)));
        }
    }
}
