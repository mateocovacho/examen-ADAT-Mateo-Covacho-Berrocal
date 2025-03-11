package adat.examen.alquileres.service;

import adat.examen.alquileres.dto.CarDTO;
import adat.examen.alquileres.entity.Car;
import adat.examen.alquileres.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarDTO createCar(CarDTO carDTO) {
        if (carRepository.existsByPlateNumber(carDTO.getPlateNumber())) {
            throw new IllegalArgumentException("Plate number already exists");
        }
        Car car = new Car();
        BeanUtils.copyProperties(carDTO, car);
        car = carRepository.save(car);
        BeanUtils.copyProperties(car, carDTO);
        return carDTO;
    }

    public CarDTO updateCar(Long id, CarDTO carDTO) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));
        BeanUtils.copyProperties(carDTO, car, "id");
        car = carRepository.save(car);
        BeanUtils.copyProperties(car, carDTO);
        return carDTO;
    }

    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new EntityNotFoundException("Car not found");
        }
        carRepository.deleteById(id);
    }

    public CarDTO getCar(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));
        CarDTO carDTO = new CarDTO();
        BeanUtils.copyProperties(car, carDTO);
        return carDTO;
    }

    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream()
                .map(car -> {
                    CarDTO carDTO = new CarDTO();
                    BeanUtils.copyProperties(car, carDTO);
                    return carDTO;
                })
                .collect(Collectors.toList());
    }

    public List<CarDTO> searchCars(String brand, String model) {
        return carRepository.findByBrandContainingIgnoreCaseOrModelContainingIgnoreCase(brand, model)
                .stream()
                .map(car -> {
                    CarDTO carDTO = new CarDTO();
                    BeanUtils.copyProperties(car, carDTO);
                    return carDTO;
                })
                .collect(Collectors.toList());
    }

    public List<CarDTO> getAvailableCars() {
        return carRepository.findByAvailableTrue()
                .stream()
                .map(car -> {
                    CarDTO carDTO = new CarDTO();
                    BeanUtils.copyProperties(car, carDTO);
                    return carDTO;
                })
                .collect(Collectors.toList());
    }
} 