package tech.inno.task8.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentCancelResponse (String transactionId, BigDecimal paymentAmount, LocalDateTime paymentDateTime, String paymentStatus, String message) {
}
