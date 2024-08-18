package tech.inno.task8.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PaymentRequest(
        @NotNull
        Long userId,
        @NotNull
        BigDecimal paymentAmount) {
}
