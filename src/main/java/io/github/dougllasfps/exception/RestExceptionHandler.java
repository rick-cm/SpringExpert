package io.github.dougllasfps.exception;

import io.github.dougllasfps.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> responseStatusException (ResponseStatusException rse){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(new Date().getTime())
                .status(rse.getStatus().value())
                .title(rse.getStatus().getReasonPhrase())
                .detail(rse.getMessage())
                .developerMessage(rse.getClass().getName())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
