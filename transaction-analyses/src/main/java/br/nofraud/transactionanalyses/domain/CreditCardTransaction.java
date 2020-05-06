package br.nofraud.transactionanalyses.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardTransaction {

  private String transactionKey;
  private BigDecimal value;
  private String creditCardNumber;
  private String merchantId;
  private String paymentLocation;
  private String status;

}
