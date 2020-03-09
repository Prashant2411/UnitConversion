package com.bridgelabz.quantity.controller;

import com.bridgelabz.quantity.DTO.ValueAndUnitDTO;
import com.bridgelabz.quantity.services.UnitConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UnitController {

    @Autowired
    UnitConversionService unitConversionService;

    @PostMapping("/unitconverter")
    public double getConverted(@Valid @RequestBody ValueAndUnitDTO... obj){
        return unitConversionService.convertValue(obj[0], obj[1]);
    }

    @GetMapping("/getunittype")
    public String getUnitType() {
        return unitConversionService.getUnitType();
    }

    @GetMapping("/getunits/{unitType}")
    public String getUnits(@PathVariable String unitType){
        return unitConversionService.getUnits(unitType);
    }
}
