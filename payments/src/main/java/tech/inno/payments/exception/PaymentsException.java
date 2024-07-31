package tech.inno.payments.exception;

import org.springframework.http.HttpStatus;

public class PaymentsException extends RuntimeException {
    private HttpStatus httpStatus;

    public PaymentsException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
