package com.projarq.ms2.application.usecases;

import com.projarq.ms2.domain.CEP;
import com.projarq.ms2.persistence.CEPSRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CEPEncontrarPorNumero_UC {

    @Autowired
    CEPSRepository cepRepository;

    public Optional<CEP> findByNumero(String cep) {
        return cepRepository.findByNumero(cep);
    }
}

