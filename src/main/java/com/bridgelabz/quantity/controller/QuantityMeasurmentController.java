package com.bridgelabz.quantity.controller;

import com.bridgelabz.quantity.DTO.ValueAndUnitDTO;
import com.bridgelabz.quantity.services.ConvertValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class QuantityMeasurmentController {

    @Autowired
    ConvertValue convertValue;

    @PostMapping("/unitconverter")
    public double getConverted(@Valid @RequestBody ValueAndUnitDTO... obj){
        return convertValue.convertValue(obj[0], obj[1]);
    }
}
