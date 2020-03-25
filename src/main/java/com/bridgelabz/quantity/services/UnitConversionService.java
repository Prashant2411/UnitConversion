package com.bridgelabz.quantity.services;

import com.bridgelabz.quantity.dto.ValueAndUnitDTO;
import com.bridgelabz.quantity.exception.UnitConversionException;
import com.bridgelabz.quantity.enumarator.UnitType;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UnitConversionService implements IUnitConversionService{

    private static DecimalFormat decimalFormat = new DecimalFormat("0.000");
    Gson gson = new Gson();

    @Override
    public double convertValue(ValueAndUnitDTO first, ValueAndUnitDTO second){
        QuantityConversion quantityConversion = new QuantityConversion();
        quantityConversion.convertedValue(first, second);
        return Double.parseDouble(decimalFormat.format(second.value));
    }

    @Override
    public String getUnitType() {
        List<UnitType> unitTypes = Arrays.stream(UnitType.values())
                .collect(Collectors.toList());
        return gson.toJson(unitTypes);
    }

    @Override
    public String getUnits(String unitType) {
        List<QuantityConversion.MeasurementUnit> units = Arrays.stream(QuantityConversion.MeasurementUnit.values())
                .filter(enumrator -> enumrator.getUnitType().toString().equals(unitType))
                .collect(Collectors.toList());
        if (units.size()==0)
            throw new UnitConversionException("Invalid Unit Type", UnitConversionException.ExceptionType.NO_SUCH_UNIT_TYPE);
        return gson.toJson(units);
    }
}
