package com.projarq.ms2.persistence;

import com.projarq.ms2.domain.CEP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CEPSRepository extends JpaRepository<CEP, Long> {

    Optional<CEP> findByNumero(String cep);
}
