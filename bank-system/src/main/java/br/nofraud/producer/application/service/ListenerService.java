package br.nofraud.producer.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import br.nofraud.producer.application.dto.FraudDetectionResult;
import br.nofraud.producer.domain.entity.CreditCardTransaction;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListenerService {

  private final CreditCardTransactionService creditCardTransactionService;
  private final ObjectMapper objectMapper;

  @RabbitListener(queues="finished")
  public void onMessage(Message message) throws JsonProcessingException {
    String jsonTransaction = new String(message.getBody(), StandardCharsets.UTF_8);

    log.debug("Resultado de transacação recebida:");
    log.debug(jsonTransaction);

    FraudDetectionResult result = objectMapper.readValue(jsonTransaction, FraudDetectionResult.class);
    Optional<CreditCardTransaction> creditCardTransaction = creditCardTransactionService
        .findById(result.getTransactionKey());

    creditCardTransaction.ifPresent(t -> {
      t.setStatus(result.getStatus());
      creditCardTransactionService.save(t);
    });

    log.debug("Resultado da análise: {}", result.toString());

  }
}
