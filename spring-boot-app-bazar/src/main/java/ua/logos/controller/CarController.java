package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.logos.domain.CarDTO;
import ua.logos.service.CarService;

import java.util.List;

@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<?> createCar(@RequestBody CarDTO carDTO) {
        carService.saveCar(carDTO);
        return new ResponseEntity<>("Car created", HttpStatus.CREATED);
    }

    @GetMapping("{carId:[0-9]{1,5}}")
    public ResponseEntity<?> getById(@PathVariable("carId") Long id) {
        CarDTO carDTO = carService.findCarById(id);

        if (carDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(carDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllCars() {
        List<CarDTO> cars = carService.findAllCar();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PutMapping("{carId:[0-9]{1,5}}")
    public ResponseEntity<?> updateCar(@PathVariable("carId") Long id, @RequestBody CarDTO carToUpdate) {
        CarDTO car = carService.updateCar(id, carToUpdate);
        if (car == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Information about car updated", HttpStatus.OK);
    }

}
