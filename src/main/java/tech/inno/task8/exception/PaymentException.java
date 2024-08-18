package tech.inno.task8.exception;

import org.springframework.http.HttpStatus;

public class PaymentException extends RuntimeException {
    private final HttpStatus httpStatus;

    public PaymentException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
