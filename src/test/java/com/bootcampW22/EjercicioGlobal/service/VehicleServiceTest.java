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
    
    //ejercicio 0
    @Test
    public void searchAllVehicles_okTest(){
        //arrange
        List<Vehicle> listaRepo = CustomFactory.get0Vehicle(1L);
        List<VehicleDto> listaEsperada = CustomFactory.get0VehicleDto(1L);

        //act
        when(vehicleRepositoryImpl.findAll()).thenReturn(listaRepo);
        List<VehicleDto> respDto = vehicleServiceImpl.searchAllVehicles();

        //assert
        assertEquals(listaEsperada, respDto);
        assertEquals(2, respDto.size());
        verify(vehicleRepositoryImpl).findAll();//Verifica que el método findAll() haya sido llamado en el mock del repositorio.
        verifyNoMoreInteractions(vehicleRepositoryImpl);//Asegura que no se haya llamado ningún otro método del repositorio aparte de findAll().
    }

    @Test
    public void searchAllVehicles_noOkTest(){
        //arrange
        //List<Vehicle> listaRepo = CustomFactory.get0Vehicle(1L);
        //List<VehicleDto> listaEsperada = CustomFactory.get0VehicleDto(1L);

        //act
        when(vehicleRepositoryImpl.findAll()).thenReturn(new ArrayList<>());
        NotFoundException respDto = assertThrows(NotFoundException.class, ()-> vehicleServiceImpl.searchAllVehicles());

        //assert
        assertEquals("No se encontró ningun auto en el sistema.", respDto.getMessage());
   }

    //ejercicio 1
   @Test
   public void searchVehiclesByYearAndColor_oktest(){
    //arrange
    List<VehicleDto> esperada = CustomFactory.get1VehicleDtoEqualYearAndColor(1L, "red", 2020);
    List<Vehicle> repo = CustomFactory.get1ListVehicleEqualYearAndColor(1L, "red", 2020);
    
    //act
    when(vehicleRepositoryImpl.findVehiclesByYearAndColor("red", 2020)).thenReturn(repo);
    List<VehicleDto> resp = vehicleServiceImpl.searchVehiclesByYearAndColor("red", 2020);

    //assert
    assertEquals(esperada, resp);
    verify(vehicleRepositoryImpl).findVehiclesByYearAndColor("red",2020);
    verifyNoMoreInteractions(vehicleRepositoryImpl);
   }

      @Test
   public void searchVehiclesByYearAndColor_noOktest(){
    //arrange
    //List<VehicleDto> esperada = CustomFactory.get1VehicleDtoEqualYearAndColor(1L, "red", 2020);
    //ist<Vehicle> repo = CustomFactory.get1ListVehicleEqualYearAndColor(1L, "red", 2020);
    
    //act
    when(vehicleRepositoryImpl.findVehiclesByYearAndColor("red", 2020)).thenReturn(new ArrayList<>());
   NotFoundException resp = assertThrows(NotFoundException.class, () -> vehicleServiceImpl.searchVehiclesByYearAndColor("red", 2020));

    //assert
    assertEquals("No se encontraron vehículos con esos criterios.", resp.getMessage());
   }
    //ejercicio 2
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