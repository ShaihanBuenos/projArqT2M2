package com.projarq.ms2.application.consumer;

import com.projarq.ms2.application.usecases.CriaOrcamento_UC;
import com.projarq.ms2.domain.dto.OrcamentoReceiver;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Receiver {
    public static final String queueName = "conversions.v1.conversion-request.save-history";
    private static Logger logger = LogManager.getLogger(Receiver.class);

    @Autowired
    private CriaOrcamento_UC criaOrcamentoUc;

    @RabbitListener(queues = queueName) public void receive(OrcamentoReceiver dto) {
        logger.info("Mensagem recebida: " + dto);
        //criaOrcamentoUc.criarOrcamento(dto.getCepOrigem(), dto.getCepDestino(), dto.getPeso());
    }
}