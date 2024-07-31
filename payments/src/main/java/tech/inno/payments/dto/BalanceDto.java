package tech.inno.payments.dto;

public record BalanceDto(
        Long productId,
        String account,
        String type,
        Long balance) {
}
