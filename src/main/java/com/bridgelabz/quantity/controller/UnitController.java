package com.bridgelabz.quantity.controller;

import com.bridgelabz.quantity.dto.ValueAndUnitDTO;
import com.bridgelabz.quantity.services.UnitConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/unit")
@CrossOrigin(origins = "*")
public class UnitController {

    @Autowired
    UnitConversionService unitConversionService;

    @PostMapping("/convert")
    public double getConverted(@Valid @RequestBody ValueAndUnitDTO... obj) {
        return unitConversionService.convertValue(obj[0], obj[1]);
    }

    @GetMapping("/type")
    public String getUnitType() {
        return unitConversionService.getUnitType();
    }

    @GetMapping("/units/{unitType}")
    public String getUnits(@PathVariable String unitType) {
        return unitConversionService.getUnits(unitType);
    }
}
