package tech.inno.task8.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UserLimitRequest(
        @NotNull
        Long userId,
        @NotNull
        BigDecimal userLimit
) {
}
