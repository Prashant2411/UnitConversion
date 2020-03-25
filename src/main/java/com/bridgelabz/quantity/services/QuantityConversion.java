package com.bridgelabz.quantity.services;

import com.bridgelabz.quantity.dto.ValueAndUnitDTO;
import com.bridgelabz.quantity.enumarator.UnitType;

import static com.bridgelabz.quantity.enumarator.UnitType.*;
import static com.bridgelabz.quantity.enumarator.UnitType.TEMPERATURE;

public class QuantityConversion {

    public void convertedValue(ValueAndUnitDTO a, ValueAndUnitDTO b) {
        if (a.unit.getUnitType().equals(b.unit.getUnitType())) {
            b.value = a.unit.getUnitsSame(b.unit.getConverted(b.value));
            b.unit = a.unit;
        }
    }

    public enum MeasurementUnit {
        FEET(12, 1, LENGTH), INCH(1, 1, LENGTH), YARD(36, 1, LENGTH), CENTIMETER(1, 2.54, LENGTH),
        LITRE(1, 1, VOLUME), MILLI_LITRE( 1,1000, VOLUME), GALLON(3.78, 1, VOLUME),
        KILO_GRAMS(1, 1, WEIGHT), GRAMS(1, 1000, WEIGHT), TON(1000, 1, WEIGHT),
        FAHRENHEIT(9, 5, TEMPERATURE), CELSIUS(9, 5, TEMPERATURE);

        private final double i;
        private final double j;
        private final UnitType unitType;

        MeasurementUnit(double i, double j, UnitType unitType) {
            this.i = i;
            this.j = j;
            this.unitType = unitType;
        }

        public double getConverted(double value) {
            if(this.name().equals(CELSIUS.toString()))
                return 32 + (value * i / j);
            if(this.name().equals(FAHRENHEIT.toString()))
                return (j / i)*(value - 32);
            return value * i / j;
        }

        public double getUnitsSame(double value) {
            if(this.unitType.equals(TEMPERATURE))
                return value;
            return value * j / i;
        }

        public UnitType getUnitType() {
            return unitType;
        }
    }
}