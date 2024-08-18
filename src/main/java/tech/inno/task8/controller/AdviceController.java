package tech.inno.task8.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.inno.task8.dto.PaymentExceptionResponse;
import tech.inno.task8.exception.PaymentException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<PaymentExceptionResponse> handleUserException(PaymentException exception) {
        return new ResponseEntity<>(new PaymentExceptionResponse(exception.getHttpStatus().value(), exception.getMessage()), exception.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<PaymentExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> listFields = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> (x.getField() + " " + x.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new PaymentExceptionResponse(HttpStatus.BAD_REQUEST.value(), listFields.toString()), HttpStatus.BAD_REQUEST);
    }
}
