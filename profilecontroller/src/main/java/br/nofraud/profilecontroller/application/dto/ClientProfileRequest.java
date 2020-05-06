package br.nofraud.profilecontroller.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientProfileRequest {
  private String creditCardNumber;
  private String location;
  private BigDecimal maximumPayment;
  private BigDecimal tolerance;
}
