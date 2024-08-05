package tech.inno.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductDto(
        @NotBlank String account,
        @NotNull BigDecimal balance,
        @NotBlank String type,
        @NotNull Long userid
) {
}
