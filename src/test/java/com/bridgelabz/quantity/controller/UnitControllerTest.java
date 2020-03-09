package com.bridgelabz.quantity.controller;

import com.bridgelabz.quantity.DTO.ValueAndUnitDTO;
import com.bridgelabz.quantity.Exception.UnitConversionException;
import com.bridgelabz.quantity.services.QuantityConversion;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest
public class UnitControllerTest {
    ValueAndUnitDTO[] obj = new ValueAndUnitDTO[2];
    Gson gson = new Gson();

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        obj[0] = new ValueAndUnitDTO();
        obj[1] = new ValueAndUnitDTO();
        obj[0].unit = QuantityConversion.MeasurementUnit.KILO_GRAMS;
        obj[1].unit = QuantityConversion.MeasurementUnit.GRAMS;
        obj[0].value = 0;
        obj[1].value = 1;
    }

    // /unit/convert

    @Test
    void givenValueAndUnitDtoJson_whenGetConverted_thenReturnStatus200() throws Exception {
        String jsonDTO = gson.toJson(obj);
        MvcResult mvcResult = this.mockMvc.perform(post("/unit/convert").content(jsonDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    void givenValueAndUnitDtoJson_whenContentTypeAtomXml_thenReturnStatus415() throws Exception {
        String jsonDTO = gson.toJson(obj);
        MvcResult mvcResult = this.mockMvc.perform(post("/unit/convert").content(jsonDTO)
                .contentType(MediaType.APPLICATION_ATOM_XML)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(415, status);
    }

    @Test
    void givenValueAndUnitDotJson_whenGetConverted_thenReturnConvertedValue() throws Exception {
        String jsonDTO = gson.toJson(obj);
        MvcResult mvcResult = this.mockMvc.perform(post("/unit/convert").content(jsonDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("0.001", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void givenValueAndUnitDotJson_whenGetConvertedForDifferentUnitTypes_thenThrowException() throws Exception {
        try {
            String jsonDTO = gson.toJson(obj);
            MvcResult mvcResult = this.mockMvc.perform(post("/unit/convert").content(jsonDTO)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        } catch (UnitConversionException e) {
            Assert.assertEquals(UnitConversionException.ExceptionType.UNIT_TYPE_DIFFERENT, e.type);
        }
    }

    // /unit/type

    @Test
    void whenGetUnitType_thenReturnUnitType() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/unit/type"))
                .andReturn();
        Assert.assertEquals("[\"LENGTH\",\"VOLUME\",\"WEIGHT\",\"TEMPERATURE\"]", mvcResult.getResponse().getContentAsString());
    }

    // /unit/units/{unitType}

    @Test
    void givenUnitType_whenGetUnits_thenReturnUnitType() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/unit/units/LENGTH"))
                .andReturn();
        Assert.assertEquals("[\"FEET\",\"INCH\",\"YARD\",\"CENTIMETER\"]", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void givenWrongUnitType_whenGetUnits_thenReturnException() throws Exception {
        try {
            MvcResult mvcResult = this.mockMvc.perform(get("/units/asd"))
                    .andReturn();
        } catch (UnitConversionException e) {
            Assert.assertEquals(UnitConversionException.ExceptionType.NO_SUCH_UNIT_TYPE, e.type);
        }
    }
}
