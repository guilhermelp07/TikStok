package com.utfpr.tikstok.api_estoques.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HashMap<String,String>> handleValidationErrors(MethodArgumentNotValidException ex){
        HashMap<String,String> errors = new HashMap<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleValidationErrorsException(Exception ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
