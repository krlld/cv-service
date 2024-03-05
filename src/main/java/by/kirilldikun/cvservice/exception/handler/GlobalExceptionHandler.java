package by.kirilldikun.cvservice.exception.handler;

import by.kirilldikun.cvservice.dto.ErrorResponse;
import by.kirilldikun.cvservice.exception.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("VALIDATION_FAILED", "The request was not validated");
        e.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errorResponse.addField(fieldError.getField(), fieldError.getDefaultMessage()));
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        log.warn(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("VALIDATION_FAILED", "The request was not validated");
        e.getConstraintViolations().forEach(constraintViolation -> errorResponse.addField(
                constraintViolation.getPropertyPath().toString(),
                constraintViolation.getMessage()));
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleProjectAlreadyExistsException(NotFoundException e) {
        log.warn(e.getMessage());
        return new ErrorResponse("RESOURCE_NOT_FOUND", e.getMessage());
    }
}