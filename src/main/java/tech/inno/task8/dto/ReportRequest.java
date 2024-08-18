package tech.inno.task8.dto;

import jakarta.validation.constraints.NotNull;

public record ReportRequest(@NotNull Long userId) {
}
