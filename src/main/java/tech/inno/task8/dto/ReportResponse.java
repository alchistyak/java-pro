package tech.inno.task8.dto;

import java.util.List;

public record ReportResponse(
        Long userId,
        List<ReportForDayResponse> limits
) {
}
