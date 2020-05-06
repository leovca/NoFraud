package br.nofraud.producer.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.nofraud.producer.domain.entity.CreditCardTransaction;

public interface CreditCardTransactionRepository extends MongoRepository<CreditCardTransaction, String> {

}
