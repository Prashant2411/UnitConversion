package com.bridgelabz.quantity.controller;

import com.bridgelabz.quantity.DTO.ValueAndUnitDTO;
import com.bridgelabz.quantity.Exception.UnitConversionException;
import com.bridgelabz.quantity.services.UnitConversionService;
import com.bridgelabz.quantity.services.QuantityConversion;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class UnitControllerMockTest {

    ValueAndUnitDTO[] obj = new ValueAndUnitDTO[2];
    Gson gson = new Gson();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UnitConversionService unitConversionService;

    // /unitconverter

    @Test
    void givenValueAndUnitDtoJson_whenGetConverted_thenReturnStatus200() throws Exception {
        when(unitConversionService.convertValue(any(), any())).thenReturn(1.1);
        String jsonDTO = gson.toJson(obj);
        MvcResult mvcResult = this.mockMvc.perform(post("/unit/convert").content(jsonDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    void givenValueAndUnitDtoJson_whenContentTypeAtomXml_thenReturnStatus415() throws Exception {
        when(unitConversionService.convertValue(any(), any())).thenReturn(1.1);
        String jsonDTO = gson.toJson(obj);
        MvcResult mvcResult = this.mockMvc.perform(post("/unit/convert").content(jsonDTO)
                .contentType(MediaType.APPLICATION_ATOM_XML)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(415, status);
    }

    @Test
    void givenValueAndUnitDotJson_whenGetConverted_thenReturnConvertedValue() throws Exception {
        when(unitConversionService.convertValue(any(), any())).thenReturn(1.0);
        String jsonDTO = gson.toJson(obj);
        MvcResult mvcResult = this.mockMvc.perform(post("/unit/convert").content(jsonDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("1.0", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void givenValueAndUnitDotJson_whenGetConvertedForDifferentUnitTypes_thenThrowException() throws Exception {
        try {
            when(unitConversionService.convertValue(any(), any())).thenThrow(new UnitConversionException("Invalid Unit Type", UnitConversionException.ExceptionType.UNIT_TYPE_DIFFERENT));
            String jsonDTO = gson.toJson(obj);
            MvcResult mvcResult = this.mockMvc.perform(post("/unit/convert").content(jsonDTO)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        } catch (UnitConversionException e) {
            Assert.assertEquals(UnitConversionException.ExceptionType.UNIT_TYPE_DIFFERENT, e.type);
        }
    }

    // /getunittype

    @Test
    void whenGetUnitType_thenReturnUnitType() throws Exception {
        when(unitConversionService.getUnitType()).thenReturn("LENGTH");
        MvcResult mvcResult = this.mockMvc.perform(get("/unit/type"))
                .andReturn();
        Assert.assertEquals("LENGTH", mvcResult.getResponse().getContentAsString());
    }

    // /getunits

    @Test
    void givenUnitType_whenGetUnits_thenReturnUnitType() throws Exception {
        when(unitConversionService.getUnits(any())).thenReturn("METER");
        MvcResult mvcResult = this.mockMvc.perform(get("/units/LENGTH"))
                .andReturn();
        Assert.assertEquals("METER", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void givenWrongUnitType_whenGetUnits_thenReturnException() throws Exception {
        try {
            when(unitConversionService.getUnits(any())).thenThrow(new UnitConversionException("Invalid Unit Type", UnitConversionException.ExceptionType.NO_SUCH_UNIT_TYPE));
            MvcResult mvcResult = this.mockMvc.perform(get("/units/asd"))
                    .andReturn();
        } catch (UnitConversionException e) {
            Assert.assertEquals(UnitConversionException.ExceptionType.NO_SUCH_UNIT_TYPE, e.type);
        }
    }
}
