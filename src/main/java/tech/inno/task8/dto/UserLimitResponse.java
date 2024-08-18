package tech.inno.task8.dto;

import java.math.BigDecimal;

public record UserLimitResponse(
        Long id,
        Long userId,
        BigDecimal userLimit
) {
}
