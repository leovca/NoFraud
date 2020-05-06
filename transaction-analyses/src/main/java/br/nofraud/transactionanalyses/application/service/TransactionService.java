package br.nofraud.transactionanalyses.application.service;

import br.nofraud.transactionanalyses.domain.CreditCardTransaction;
import br.nofraud.transactionanalyses.domain.enums.AnalyseResult;

public interface TransactionService {

  AnalyseResult evalute(CreditCardTransaction creditCardTransaction);

}
