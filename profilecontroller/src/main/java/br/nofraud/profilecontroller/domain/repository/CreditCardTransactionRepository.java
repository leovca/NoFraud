package br.nofraud.profilecontroller.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.nofraud.profilecontroller.domain.entity.CreditCardTransaction;


public interface CreditCardTransactionRepository extends MongoRepository<CreditCardTransaction, String>{

  CreditCardTransaction findTopByCreditCardNumberAndStatusOrderByValueDesc(String creditCard,
      String status);
}
