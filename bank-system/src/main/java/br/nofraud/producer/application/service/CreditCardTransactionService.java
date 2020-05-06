package br.nofraud.producer.application.service;

import br.nofraud.producer.domain.entity.CreditCardTransaction;

import java.util.Optional;

public interface CreditCardTransactionService {

  void save(CreditCardTransaction transaction);
  Optional<CreditCardTransaction> findById(String idTransaction);

}
