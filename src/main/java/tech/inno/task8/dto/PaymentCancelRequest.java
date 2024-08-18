package tech.inno.task8.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PaymentCancelRequest(
        @NotNull
        Long userId,
        @NotNull
        @NotBlank
        String transactionId,
        @NotNull
        BigDecimal paymentAmount) {
}
