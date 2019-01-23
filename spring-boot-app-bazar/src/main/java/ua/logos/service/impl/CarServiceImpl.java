package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.logos.domain.CarDTO;
import ua.logos.domain.SellerDTO;
import ua.logos.entity.CarEntity;
import ua.logos.entity.SellerEntity;
import ua.logos.repository.CarRepository;
import ua.logos.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;


    @Override
    public CarDTO saveCar(CarDTO carDTO) {
        CarEntity carEntity = dtoToEntity(carDTO);
        carRepository.save(carEntity);
        carDTO.setId(carEntity.getId());

        return carDTO;
    }

    @Override
    public List<CarDTO> findAllCar() {
        List<CarEntity> carEntityList = carRepository.findAll();
        List<CarDTO> carDTOS = new ArrayList<>();
        for (CarEntity entity : carEntityList) {
            CarDTO carDTO = entityToDTOMapper(entity);
            carDTOS.add(carDTO);
        }
        return carDTOS;
    }

    @Override
    public CarDTO findCarById(Long id) {
        boolean exists = carRepository.existsById(id);
        if (!exists) {
            return null;
        }
        CarEntity carEntity = carRepository.findById(id).get();
        CarDTO carDTO = entityToDTOMapper(carEntity);
        return carDTO;
    }

    @Override
    public CarDTO updateCar(Long id, CarDTO carToUpdate) {
        boolean exists = carRepository.existsById(id);
        if (!exists) {
            return null;
        }
        CarEntity carFromDB = carRepository.findById(id).get();
        carFromDB.setModel(carToUpdate.getModel());
        carFromDB.setBrand(carToUpdate.getBrand());
        carFromDB.setColor(carToUpdate.getColor());
        carFromDB.setVolumeOfEngine(carToUpdate.getVolumeOfEngine());
        carFromDB.setPrice(carToUpdate.getPrice());

        SellerDTO sellerDTO = carToUpdate.getSeller();
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setId(sellerDTO.getId());
        sellerEntity.setFirstName(sellerDTO.getFirstName());
        sellerEntity.setLastName(sellerDTO.getLastName());
        sellerEntity.setEmail(sellerDTO.getEmail());
        sellerEntity.setPhoneNumber(sellerDTO.getPhoneNumber());
        carFromDB.setSeller(sellerEntity);
        CarDTO carDTO = entityToDTOMapper(carFromDB);
        carRepository.save(carFromDB);
        carDTO.setId(carFromDB.getId());
        return carDTO;
    }

    private CarDTO entityToDTOMapper(CarEntity carEntity) {
        CarDTO carDTO = new CarDTO();

        carDTO.setId(carEntity.getId());
        carDTO.setModel(carEntity.getModel());
        carDTO.setBrand(carEntity.getBrand());
        carDTO.setColor(carEntity.getColor());
        carDTO.setPrice(carEntity.getPrice());
        carDTO.setVolumeOfEngine(carEntity.getVolumeOfEngine());

        SellerEntity sellerEntity = carEntity.getSeller();
        SellerDTO sellerDTO = new SellerDTO();
        sellerDTO.setId(sellerEntity.getId());
        sellerDTO.setFirstName(sellerEntity.getFirstName());
        sellerDTO.setLastName(sellerEntity.getLastName());
        sellerDTO.setEmail(sellerEntity.getEmail());
        sellerDTO.setPhoneNumber(sellerEntity.getPhoneNumber());

        carDTO.setSeller(sellerDTO);
        return carDTO;

    }

    private CarEntity dtoToEntity(CarDTO carDTO) {
        CarEntity carEntity = new CarEntity();
        carEntity.setId(carDTO.getId());

        carEntity.setModel(carDTO.getModel());
        carEntity.setBrand(carDTO.getBrand());
        carEntity.setColor(carDTO.getColor());
        carEntity.setPrice(carDTO.getPrice());
        carEntity.setVolumeOfEngine(carDTO.getVolumeOfEngine());

        SellerDTO sellerDTO = carDTO.getSeller();
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setId(sellerDTO.getId());
        sellerEntity.setFirstName(sellerDTO.getFirstName());
        sellerEntity.setLastName(sellerDTO.getLastName());
        sellerEntity.setEmail(sellerDTO.getEmail());
        sellerEntity.setPhoneNumber(sellerDTO.getPhoneNumber());

        carEntity.setSeller(sellerEntity);

        return carEntity;

    }


}
