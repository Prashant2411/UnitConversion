package com.bridgelabz.quantity.service;

import com.bridgelabz.quantity.dto.ValueAndUnitDTO;
import com.bridgelabz.quantity.exception.UnitConversionException;
import com.bridgelabz.quantity.services.QuantityConversion;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class QuantityConversionTests {

    //Length

    @Test
    public void givenFeetAndFeet_shouldReturnEqual() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.FEET, 0.0);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.FEET, 0.0);
        Assert.assertEquals(first, second);
    }

    @Test
    public void givenSameReferenceOfFeet_shouldReturnEqual() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.FEET, 0.0);
        Assert.assertEquals(first, new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.FEET, 0.0));
    }

    @Test
    public void givenSameClassOfFeet_shouldReturnEqual() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.FEET, 0.0);
        Assert.assertEquals(first.getClass(), (new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.FEET, 0.0).getClass()));
    }

    @Test
    public void givenInchAndInch_shouldReturnEqual() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.INCH, 0.0);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.INCH, 0.0);
        Assert.assertEquals(first, second);
    }

    @Test
    public void givenSameReferenceOfInch_shouldReturnTrue() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.INCH, 0.0);
        Assert.assertEquals(first, (new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.INCH, 0.0)));
    }

    @Test
    public void givenSameClassOfInch_shouldReturnTrue() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.INCH, 0.0);
        Assert.assertEquals(first.getClass(), (new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.INCH, 0.0).getClass()));
    }

    @Test
    public void givenFeetAndInch_shouldReturnEqual() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.FEET, 0.0);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.INCH, 0.0);
        Assert.assertEquals(first, second);
    }

    @Test
    public void given1FeetAnd1Inch_shouldReturnFalse() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.FEET, 1.0);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.INCH, 1.0);
        Assert.assertNotEquals(first, second);
    }

    @Test
    public void given1InchAnd1Feet_shouldReturnFalse() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.INCH, 1.0);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.FEET, 1.0);
        Assert.assertNotEquals(first, second);
    }

    @Test
    public void given1FeetAnd12Inch_shouldReturnTrue() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.FEET, 1.0);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.INCH, 12.0);
        Assert.assertEquals(first, second);
    }

    @Test
    public void given12InchAnd1Feet_shouldReturnTrue() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.INCH, 12.0);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.FEET, 1.0);
        Assert.assertEquals(first, second);
    }

    @Test
    public void given3FeetAnd1Yard_shouldReturnTrue() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.FEET, 3.0);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.YARD, 1.0);
        Assert.assertEquals(first, second);
    }

    @Test
    public void given1FeetAnd1Yard_shouldReturnFalse() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.FEET, 1.0);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.YARD, 1.0);
        Assert.assertNotEquals(first, second);
    }

    @Test
    public void given1InchAnd1Yard_shouldReturnFalse() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.INCH, 1.0);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.YARD, 1.0);
        Assert.assertNotEquals(first, second);
    }

    @Test
    public void given1YardAnd36Inch_shouldReturnTrue() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.YARD, 1.0);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.INCH, 36.0);
        Assert.assertEquals(first, second);
    }

    @Test
    public void given36InchAnd1Yard_shouldReturnTrue() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.INCH, 36.0);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.YARD, 1.0);
        Assert.assertEquals(first, second);
    }

    @Test
    public void given1YardAnd3Feet_shouldReturnTrue() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.YARD, 1.0);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.FEET, 3.0);
        Assert.assertEquals(first, second);
    }

    @Test
    public void given2InchAnd5CM_shouldReturnTrue() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.INCH, 2.0);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.CENTIMETER, 5.08);
        Assert.assertEquals(first, second);
    }

    //Volume

    @Test
    public void given1LiterAnd1000MilliLiter_shouldReturnTrue() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.LITRE, 1.0);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.MILLI_LITRE, 1000.0);
        Assert.assertEquals(first, second);
    }

    @Test
    public void given1GallonAnd3point78Liter_shouldReturnTrue() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.GALLON, 1.0);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.LITRE, 3.78);
        Assert.assertEquals(first, second);
    }

    //Weight

    @Test
    public void given1KiloGramAnd1000Gram_shouldReturnEqual() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.KILO_GRAMS, 1.0);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.GRAMS, 1000.0);
        Assert.assertEquals(first, second);
    }

    @Test
    public void given1TonAnd1000KG_shouldReturnEqual() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.TON, 1.0);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.KILO_GRAMS, 1000.0);
        Assert.assertEquals(first, second);
    }

    //Temperature

    @Test
    public void given212FahrenheitAnd100Celsius_shouldReturnEqual() {
        ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.FAHRENHEIT, 212);
        ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.CELSIUS, 100);
        Assert.assertEquals(first, second);
    }

    //Exception

    @Test
    public void givenDifferentUnitType_whenConvert_shouldReturnException() {
        try {
            ValueAndUnitDTO first = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.FAHRENHEIT, 212.0);
            ValueAndUnitDTO second = new ValueAndUnitDTO(QuantityConversion.MeasurementUnit.KILO_GRAMS, 100.0);
        } catch (UnitConversionException e) {
            Assert.assertEquals(UnitConversionException.ExceptionType.UNIT_TYPE_DIFFERENT, e.type);
        }
    }
}
