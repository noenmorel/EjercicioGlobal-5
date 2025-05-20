package com.bootcampW22.EjercicioGlobal.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bootcampW22.EjercicioGlobal.service.VehicleServiceImpl;

import static org.mockito.Answers.values;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.ObjectInputFilter.Status;


@SpringBootTest
@AutoConfigureMockMvc
public class VehicleControllerIntegrTest {
    @Autowired
    MockMvc mockMvc;
    //ejercicio 0
    @Test
    public void getVehicles_okTEst()throws Exception{
        mockMvc.perform(get("/vehicles"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].brand").value("Pontiac"));
    }

    @Test
    public void getVehicles_noOkTEst()throws Exception{
        mockMvc.perform(get("/vehicles"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        //.andExpect(status().isNotFound())
        //.andExpect(jsonPath("$[0].brand").value("Pontiac"))
        ;
    }

    //ejerccio 1
    @Test
    public void getVehiclesByColorAndYear_okTest( ) throws Exception{
        mockMvc.perform(get("/vehicles/color/{color}/year/{year}", "Mauv", 1986))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].color").value("Mauv"));
    }

    @Test
    public void getVehiclesByColorAndYear_noOkTest( ) throws Exception{
        mockMvc.perform(get("/vehicles/color/{color}/year/{year}", "Mauvvvvvv", 1986))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("No se encontraron vehículos con esos criterios."));
    }

    //ejercicio 2
    @Test
    public void getVehiclesByColorAndRangeOfYear_okTest() throws Exception {
        mockMvc.perform(get("/vehicles/brand/{brand}/between/{start_year}/{end_year}","Ford",1997,2020))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].brand").value("Ford"));
    }

    @Test
    public void getVehiclesByColorAndRangeOfYear_notOkTest() throws Exception {
        mockMvc.perform(get("/vehicles/brand/{brand}/between/{start_year}/{end_year}","Fordddddd",1997,2020))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("No se encontraron vehículos con esos criterios."));
    }
}
