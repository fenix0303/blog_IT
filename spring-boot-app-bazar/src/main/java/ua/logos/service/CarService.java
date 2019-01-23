package ua.logos.service;

import ua.logos.domain.CarDTO;

import java.util.List;

public interface CarService {
    CarDTO saveCar(CarDTO carDTO);

    List<CarDTO> findAllCar();

    CarDTO findCarById(Long id);

    CarDTO updateCar (Long id, CarDTO carToUpdate);

}
