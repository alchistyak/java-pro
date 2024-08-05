package tech.inno.payment.dto;

public record ProductDto(
        Long id,
        String account,
        Long balance,
        String type,
        Long userid) {
}
