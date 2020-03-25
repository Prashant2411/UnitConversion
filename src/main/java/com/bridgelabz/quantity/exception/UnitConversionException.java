package com.bridgelabz.quantity.exception;

public class UnitConversionException extends RuntimeException{
    public enum ExceptionType {
        NO_SUCH_UNIT_TYPE, UNIT_TYPE_DIFFERENT
    }

    public ExceptionType type;

    public UnitConversionException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
