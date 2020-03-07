package com.bridgelabz.quantity.controller;

import com.bridgelabz.quantity.DTO.ValueAndUnitDTO;
import com.bridgelabz.quantity.services.ConvertValue;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class QuantityMeasurmentControllerTest {

    ValueAndUnitDTO[] obj = new ValueAndUnitDTO[2];
    QuantityMeasurmentController quantityMeasurement;
    Gson gson = new Gson();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ConvertValue convertValue;

    @BeforeEach
    public void setUp(){
        obj[0] = new ValueAndUnitDTO();
        obj[1] = new ValueAndUnitDTO();
        obj[0].unit = QuantityConversion.MeasurementUnit.INCH;
        obj[1].unit = QuantityConversion.MeasurementUnit.YARD;
        obj[0].value = 0;
        obj[1].value = 1;
    }

    @Test
    void givenValueAndUnitDtoJson_whenGetConverted_thenReturnStatus200() throws Exception {
        when(convertValue.convertValue(obj[0],obj[1])).thenReturn(null);
        String jsonDTO =gson.toJson(obj);
        MvcResult mvcResult = this.mockMvc.perform(post("/unitcomparison").content(jsonDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200,status);
    }

    @Test
    void givenValueAndUnitDtoJson_whenContentTypeAtomXml_thenReturnStatus415() throws Exception {
        when(convertValue.convertValue(obj[0],obj[1])).thenReturn(null);
        String jsonDTO =gson.toJson(obj);
        MvcResult mvcResult = this.mockMvc.perform(post("/unitcomparison").content(jsonDTO)
                .contentType(MediaType.APPLICATION_ATOM_XML)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(415,status);
    }

    @Test
    void givenValueAndUnitDotJson_whenGetConverted_thenReturnConvertedValue() throws Exception {
        when(convertValue.convertValue(any(),any())).thenReturn(obj[1].value);
        String jsonDTO =gson.toJson(obj);
        MvcResult mvcResult = this.mockMvc.perform(post("/unitcomparison").content(jsonDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals("{\"value\":2.0,\"unit\":\"CENTIMETER\"}",mvcResult.getResponse().getContentAsString());
    }
}
