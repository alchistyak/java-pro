package tech.inno.product.dto;

import java.math.BigDecimal;

public record ProductResponseDto(Long id, String account, BigDecimal balance, String type) {
}
