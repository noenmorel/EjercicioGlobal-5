package com.bootcampW22.EjercicioGlobal.utils;

import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.entity.Vehicle;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public final class CustomFactory {
    private CustomFactory() {}
    private static final ObjectMapper objectMapper = new ObjectMapper();

    //EJERCICIO 0 - BUSCA POR ID
    public static List<Vehicle> get0Vehicle(Long id){
        List<Vehicle> lista = List.of(
                new Vehicle(id, "test", "test", "test", "test", 2000, "1", 1, "test", "test", 1.1, 1.1, 1.1),
                new Vehicle(id+1L, "test", "test", "test", "test", 2000, "1", 1, "test", "test", 1.1, 1.1, 1.1)
        );
        return lista;
    }
    public static List<VehicleDto> get0VehicleDto(Long id){
        return get0Vehicle(id)
                .stream()
                .map(x -> objectMapper.convertValue(x, VehicleDto.class))
                .toList();
    }

     //EJERCICIO 1 - BUSCA POR COLOR Y AÑO
    public static List<Vehicle> get1ListVehicleEqualYearAndColor(Long id,String color, Integer year){
        List<Vehicle> lista = List.of(
                new Vehicle(id, "test", "test", "test", color, year, "1", 1, "test", "test", 1.1, 1.1, 1.1),
                new Vehicle(id+1L, "test", "test", "test", color, year, "1", 1, "test", "test", 1.1, 1.1, 1.1)
        );
        return lista;
    }

    public static List<VehicleDto> get1VehicleDtoEqualYearAndColor(Long id,String color, Integer year){
        return get1ListVehicleEqualYearAndColor(id,color,year).stream().map(x -> objectMapper.convertValue(x,VehicleDto.class)).toList();
    }

    //EJERCICIO 2, 3 Y 4 - BUSCA POR MARCA
    public static List<Vehicle> get234ListVehicleEqualBrand(Long id,String brand){
        List<Vehicle> lista = List.of(
                new Vehicle(id, brand, "test", "test", "red", 2020, "1.5", 20, "test", "test", 1.1, 1.1, 1.1),
                new Vehicle(id+1L, brand, "test", "test", "green", 2020, "1.5", 10, "test", "test", 1.1, 1.1, 1.1)
        );
        return lista;
    }

    public static List<VehicleDto> get234ListVehicleDtoEqualBrand(Long id,String brand){
        return get234ListVehicleEqualBrand(id,brand).stream().map(x -> objectMapper.convertValue(x,VehicleDto.class)).toList();
    }

    //EJERCICIO 5 - BUSCAR POR PESO
    public static List<Vehicle> get5ListVehicleEqualWeight(Long id,Double weight){
        List<Vehicle> lista = List.of(
                new Vehicle(id, "Tesla", "test", "test", "red", 2020, "1", 1, "test", "test", 1.1, 1.1, weight),
                new Vehicle(id+1L, "Tesla", "test", "test", "green", 2020, "1", 1, "test", "test", 1.1, 1.1, weight)
        );
        return lista;
    }

    public static List<VehicleDto> get5ListVehicleDtoEqualWeight(Long id,Double weight){
        return get5ListVehicleEqualWeight(id,weight).stream().map(x -> objectMapper.convertValue(x,VehicleDto.class)).toList();
    }

}