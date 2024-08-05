package tech.inno.payment.dto;

public record BalanceDto(
        Long productId,
        String account,
        String type,
        Long balance) {
}
