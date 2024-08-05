package tech.inno.payment.dto;

import java.math.BigDecimal;

public record BalanceDto(
        Long id,
        String account,
        String type,
        BigDecimal balance) {
}
