package br.nofraud.producer.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditCardPaymentRequest {

  @NotNull(message = "Valor do pagamento necessário")
  private BigDecimal value;
  @NotNull(message = "Número do cartão necessário")
  private String creditCardNumber;
  @NotNull(message = "Código do comerciante necessário")
  private String merchantId;
  private String paymentLocation;

}
