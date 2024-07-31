package tech.inno.payments.dto;

public record PaymentsErrorDto(
        int status,
        String message) {
}
