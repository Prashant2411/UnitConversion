package com.bridgelabz.quantity.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.constraints.Null;

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
}