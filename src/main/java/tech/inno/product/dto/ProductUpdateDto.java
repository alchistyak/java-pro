package tech.inno.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductUpdateDto(
        @NotNull Long id,
        @NotBlank String account,
        @NotNull BigDecimal balance,
        @NotBlank String type
) {
}
