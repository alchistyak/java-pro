package tech.inno.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.inno.payment.dto.PaymentsErrorDto;
import tech.inno.payment.exception.PaymentsException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(PaymentsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public PaymentsErrorDto handlePaymentsException(PaymentsException exception) {
        return new PaymentsErrorDto(exception.getHttpStatus().value(), exception.getMessage());
    }
}
