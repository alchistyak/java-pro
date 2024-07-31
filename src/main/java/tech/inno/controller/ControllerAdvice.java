package tech.inno.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.inno.dto.ErrorDto;
import tech.inno.exception.ServiceException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDto handleServiceException(ServiceException exception) {
        return new ErrorDto(exception.getHttpStatus(), exception.getMessage());
    }
}
