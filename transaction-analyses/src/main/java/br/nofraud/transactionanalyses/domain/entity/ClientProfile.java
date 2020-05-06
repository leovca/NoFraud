package br.nofraud.transactionanalyses.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.nofraud.transactionanalyses.domain.CreditCardTransaction;
import br.nofraud.transactionanalyses.domain.enums.AnalyseResult;

import java.math.BigDecimal;

@Document(collection = "clientProfile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientProfile {

  @Id
  private String creditCardNumber;
  private String location;
  private BigDecimal maximumPayment;
  private BigDecimal tolerance;

  /*
  Método simples para validar uma transação:

    1 - Caso a transação for em realizada em localização difernte da origem do cliente será
    considerada fraude

    2 - Caso o valor da transação seja maior do que o valor máximo já registrado mais a tolerancia
    então a transação será considerada fraude

   */
  public AnalyseResult evaluteTransaction(CreditCardTransaction transaction){
    if(locationIsDiferent(transaction) || valueIsIncompatible(transaction))
      return AnalyseResult.FRAUD;
    else
      return AnalyseResult.OK;
  }

  private boolean valueIsIncompatible(CreditCardTransaction transaction){
    if (!isFirstTransaction() && this.maximumPayment.multiply(this.tolerance)
        .compareTo(transaction.getValue()) < 0){
      return  true;
    } else {
      return false;
    }
  }

  private boolean isFirstTransaction(){
    return maximumPayment == null || maximumPayment.equals(BigDecimal.ZERO);
  }

  private boolean locationIsDiferent(CreditCardTransaction transaction){
    return !this.location.equals(transaction.getPaymentLocation());
  }
}
