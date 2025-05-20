package com.bootcampW22.EjercicioGlobal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.dto.VehicleAvgCapacityByBrandDto;
import com.bootcampW22.EjercicioGlobal.entity.Vehicle;
import com.bootcampW22.EjercicioGlobal.exception.NotFoundException;
import com.bootcampW22.EjercicioGlobal.repository.VehicleRepositoryImpl;
import com.bootcampW22.EjercicioGlobal.utils.CustomFactory;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {
    @Mock
    VehicleRepositoryImpl vehicleRepositoryImpl;

    @InjectMocks
    VehicleServiceImpl vehicleServiceImpl;

    @Test
    public void searchVehiclesByBrandAndRangeOfYear_okTest(){
        //Arrange
        List<Vehicle> list = CustomFactory.get234ListVehicleEqualBrand(1L, "fiat");
        List<VehicleDto> esperado = CustomFactory.get234ListVehicleDtoEqualBrand(1L, "fiat");

        //Act
        when(vehicleRepositoryImpl.findVehiclesByBrandAndRangeOfYear("fiat", 2020, 2021)).thenReturn(list);
        List<VehicleDto> resp = vehicleServiceImpl.searchVehiclesByBrandAndRangeOfYear("fiat", 2020, 2021);

        //Assert
        assertEquals(esperado, resp);
        assertEquals(2, resp.size());
        verify(vehicleRepositoryImpl).findVehiclesByBrandAndRangeOfYear("fiat", 2020, 2021);
    }

    @Test
    public void searchVehiclesByBrandAndRangeOfYear_notOkTest(){
        //Arrange

        //Act
        when(vehicleRepositoryImpl.findVehiclesByBrandAndRangeOfYear("fiat", 2020, 2021)).thenReturn(new ArrayList<>());
        NotFoundException resp = assertThrows(NotFoundException.class, ()-> vehicleServiceImpl.searchVehiclesByBrandAndRangeOfYear("fiat", 2020, 2021));

        //Assert
        assertEquals("No se encontraron vehículos con esos criterios.", resp.getMessage());
    }
}