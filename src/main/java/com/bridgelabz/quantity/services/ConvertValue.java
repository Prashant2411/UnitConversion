package com.bridgelabz.quantity.services;

import com.bridgelabz.quantity.DTO.ValueAndUnitDTO;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class ConvertValue {

    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public double convertValue(ValueAndUnitDTO first, ValueAndUnitDTO second){
        QuantityConversion quantityConversion = new QuantityConversion();
        quantityConversion.convertedValue(first, second);
        return Double.parseDouble(decimalFormat.format(second.value));
    }
}
