package tech.inno.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.inno.product.dto.UserExceptionDto;
import tech.inno.product.exception.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<UserExceptionDto> handleUserException(ServiceException exception) {
        return new ResponseEntity<>(new UserExceptionDto(exception.getHttpStatus().value(), exception.getMessage()), exception.getHttpStatus());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserExceptionDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> listFields = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> (x.getField() + " " + x.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new UserExceptionDto(HttpStatus.BAD_REQUEST.value(), listFields.toString()), HttpStatus.BAD_REQUEST);
    }
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public UserExceptionDto handleUserException(UserNotFoundException exception) {
//        return new UserExceptionDto(exception.getHttpStatus().value(), exception.getMessage());
//    }
}
