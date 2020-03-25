package com.bridgelabz.quantity.dto;

import com.bridgelabz.quantity.services.QuantityConversion;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
public class ValueAndUnitDTO {

    @NotNull
    public double value;

    @NotNull
    public QuantityConversion.MeasurementUnit unit;

    public ValueAndUnitDTO(QuantityConversion.MeasurementUnit unit, double value) {
        this.value = value;
        this.unit = unit;
    }

    public ValueAndUnitDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueAndUnitDTO that = (ValueAndUnitDTO) o;
        QuantityConversion quantityConversion = new QuantityConversion();
        quantityConversion.convertedValue(this,that);
        return (Double.compare(that.value, value) == 0 &&
                (unit == that.unit || (that.value == 0 && value == 0)));
    }
}