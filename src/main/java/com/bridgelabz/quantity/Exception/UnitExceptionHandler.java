package com.bridgelabz.quantity.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UnitExceptionHandler {

    @ExceptionHandler(value = UnitConversionException.class)
    public ResponseEntity<Object> exceptionHandler(UnitConversionException unitException){
        return new ResponseEntity<>(unitException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
