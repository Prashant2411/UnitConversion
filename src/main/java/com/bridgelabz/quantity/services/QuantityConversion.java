package com.bridgelabz.quantity.services;

import com.bridgelabz.quantity.DTO.ValueAndUnitDTO;

public class QuantityConversion {

    public void convertedValue(ValueAndUnitDTO a, ValueAndUnitDTO b) {
        if (a.unit.getUnitType().equals(b.unit.getUnitType())) {
            b.value = a.unit.getUnitsSame(
                    b.unit.getConverted(b.value));
            b.unit = a.unit;
        }
    }

    public enum MeasurementUnit {
        FEET(12, 1, "Length"), INCH(1, 1, "Length"), YARD(36, 1, "Length"), CENTIMETER(1, 2.54, "Length"),
        LITRE(1, 1, "Volume"), MILLI_LITRE( 1000,1, "Volume"), GALLON(3.78, 1, "Volume"),
        KILO_GRAMS(1, 1, "Weight"), GRAMS(1, 1000, "Weight"), TON(1000, 1, "Weight"),
        FAHRENHEIT(1, 1, "Temperature"), CELSIUS(2.12, 1, "Temperature");

        private final double i;
        private final double j;
        private final String unitType;

        MeasurementUnit(double i, double j, String unitType) {
            this.i = i;
            this.j = j;
            this.unitType = unitType;
        }

        public double getConverted(double value) {
            return value * i / j;
        }

        public double getUnitsSame(double value) {
            return value * j / i;
        }

        public String getUnitType() {
            return unitType;
        }
    }
}