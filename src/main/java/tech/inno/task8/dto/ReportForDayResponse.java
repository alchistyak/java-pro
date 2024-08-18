package tech.inno.task8.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ReportForDayResponse(
        Long id,
        BigDecimal unusedLimit,
        LocalDateTime setupDateTime,
        Boolean active,
        List<PaymentResponse> payments
) {
}
