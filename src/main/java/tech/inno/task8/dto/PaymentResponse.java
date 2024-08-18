package tech.inno.task8.dto;

import tech.inno.task8.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponse(
        String transactionId,
        LocalDateTime paymentDateTime,
        BigDecimal paymentAmount,
        PaymentStatus paymentStatus,
        BigDecimal unusedLimit) {
}
