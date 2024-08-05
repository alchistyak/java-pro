package tech.inno.payment.dto;

public record PaymentsErrorDto(
        int status,
        String message) {
}
