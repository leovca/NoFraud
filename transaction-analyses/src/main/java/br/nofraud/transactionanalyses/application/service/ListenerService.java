package br.nofraud.transactionanalyses.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import br.nofraud.transactionanalyses.application.dto.TransactionResult;
import br.nofraud.transactionanalyses.domain.CreditCardTransaction;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListenerService {

  private final ObjectMapper objectMapper;
  private final TransactionService transactionAnalyses;
  private final RabbitTemplate rabbitTemplate;

  @RabbitListener(queues="transaction")
  public void onMessage(Message message) throws JsonProcessingException {
    String jsonTransaction = new String(message.getBody(), StandardCharsets.UTF_8);

    log.debug("Transação recebida:");
    log.debug(jsonTransaction);

    var transaction = objectMapper.readValue(jsonTransaction,
        CreditCardTransaction.class);

    var result = transactionAnalyses.evalute(transaction);

    registerResult(transaction.getTransactionKey(), result.toString());
    updateProfile(transaction.getCreditCardNumber());

    log.debug("Resultado da análise: {}", result.toString());

  }

  private void updateProfile(String creditCardNumber){
    rabbitTemplate.convertAndSend("profileUpdate", creditCardNumber);
  }

  private void registerResult(String key, String status) throws JsonProcessingException {
    ObjectMapper obj = new ObjectMapper();
    String transactionResult = obj.writeValueAsString(TransactionResult
        .builder()
        .transactionKey(key)
        .status(status)
        .build());
    rabbitTemplate.convertAndSend("finished", transactionResult);
  }
}
