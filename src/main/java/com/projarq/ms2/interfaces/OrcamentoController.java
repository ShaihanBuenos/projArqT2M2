package com.projarq.ms2.interfaces;

import com.projarq.ms2.application.usecases.ListaOrcamentos_UC;
import com.projarq.ms2.domain.dto.OrcamentoResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/orcamento")
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
public class OrcamentoController {

    @Autowired
    private final ListaOrcamentos_UC listaOrcamentosUc;

    @PostMapping("/get/{dataCriacao}")
    public ResponseEntity<List<OrcamentoResponseDTO>> getOrcamentPorData(@PathVariable LocalDate dataCriacao) {
        log.info("Recebendo requisição para listar orçamentos por data - {}.", dataCriacao);
        return ResponseEntity.ok(listaOrcamentosUc.getListaOrcamentos(dataCriacao));
    }

}
