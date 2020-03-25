package com.bridgelabz.quantity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UnitExceptionHandler {

    @ExceptionHandler(value = UnitConversionException.class)
    public ResponseEntity<Object> exceptionHandler(UnitConversionException unitException){
        return new ResponseEntity<>(unitException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<Object> nullExceptionHandler(NullPointerException nullException){
        return new ResponseEntity<>("Enter Valid Data", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> invalidEnumExcpetionHandler(HttpMessageNotReadableException invalidEnumException){
        return new ResponseEntity<>("Invalid unit entered", HttpStatus.BAD_REQUEST);
    }
}