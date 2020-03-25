package com.bridgelabz.quantity.services;

import com.bridgelabz.quantity.dto.ValueAndUnitDTO;
import com.bridgelabz.quantity.enumarator.UnitType;

import static com.bridgelabz.quantity.enumarator.UnitType.*;
import static com.bridgelabz.quantity.enumarator.UnitType.TEMPERATURE;

public class QuantityConversion {

    public void convertedValue(ValueAndUnitDTO a, ValueAndUnitDTO b) {
        if (a.unit.unitType.equals(b.unit.unitType) && a.unit.unitType != TEMPERATURE) {
            b.value = a.unit.getUnitsSame(b.unit.getConverted(b.value));
            b.unit = a.unit;
        }
        if (a.unit.unitType.equals(b.unit.unitType) && a.unit.unitType == TEMPERATURE) {
            if(a.unit.equals(b.unit)){
                b.value = a.value;
            }
            else if(a.unit!=b.unit){
                b.value = b.unit.getConverted(b.value);
                b.unit = a.unit;
            }
        }
    }

    public enum MeasurementUnit {
        FEET(12, 1, LENGTH), INCH(1, 1, LENGTH), YARD(36, 1, LENGTH), CENTIMETER(1, 2.54, LENGTH),
        LITRE(1, 1, VOLUME), MILLI_LITRE( 1,1000, VOLUME), GALLON(3.78, 1, VOLUME),
        KILO_GRAMS(1, 1, WEIGHT), GRAMS(1, 1000, WEIGHT), TON(1000, 1, WEIGHT),
        FAHRENHEIT(9, 5, TEMPERATURE), CELSIUS(9, 5, TEMPERATURE);

        private static final int TEMPERATURE_CONSTANT = 32;
        private final double i;
        private final double j;
        public final UnitType unitType;

        MeasurementUnit(double i, double j, UnitType unitType) {
            this.i = i;
            this.j = j;
            this.unitType = unitType;
        }

        public double getConverted(double value) {
            if(this.name().equals(CELSIUS.toString()))
                return TEMPERATURE_CONSTANT + (value * i / j);
            if(this.name().equals(FAHRENHEIT.toString()))
                return (j / i)*(value - TEMPERATURE_CONSTANT);
            return value * i / j;
        }

        public double getUnitsSame(double value) {
            return value * j / i;
        }
    }
}