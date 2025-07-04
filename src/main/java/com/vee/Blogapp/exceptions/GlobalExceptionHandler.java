package com.vee.Blogapp.exceptions;

import com.vee.Blogapp.payloads.ResponsePayload;
import jakarta.validation.constraints.Null;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponsePayload> userNotFoundException(ResourceNotFoundException exception) {
        var message = exception.getMessage();
        var payload = ResponsePayload.error(message, HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponsePayload> userNotFoundException(MethodArgumentNotValidException exception) {
        String message = "";
        if(!exception.getBindingResult().getAllErrors().isEmpty()){
            var error = (FieldError) (exception.getBindingResult().getAllErrors().get(0));
            var field = error.getField();
            var errorMessage = error.getDefaultMessage();
            message ="Error: "+errorMessage;
        }

        var payload = ResponsePayload.error(message, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ResponsePayload> propertyReferenceException(PropertyReferenceException exception) {
        String message = "";
        message =exception.getMessage();
        var payload = ResponsePayload.error(message, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponsePayload> propertyReferenceException(IllegalArgumentException exception) {
        String message = "";
        message = exception.getMessage();
        var payload = ResponsePayload.error(message, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
    }

}
