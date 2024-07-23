package tech.inno.dto;

import org.springframework.http.HttpStatus;

public class ErrorDto {
    private final int status;
    private final String message;

    public ErrorDto(HttpStatus httpStatus, String message) {
        this.status = httpStatus.value();
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
