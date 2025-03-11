package adat.examen.alquileres.service;

import adat.examen.alquileres.dto.RentalDTO;
import adat.examen.alquileres.entity.Car;
import adat.examen.alquileres.entity.Rental;
import adat.examen.alquileres.entity.RentalStatus;
import adat.examen.alquileres.entity.User;
import adat.examen.alquileres.repository.CarRepository;
import adat.examen.alquileres.repository.RentalRepository;
import adat.examen.alquileres.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public RentalService(RentalRepository rentalRepository, UserRepository userRepository, CarRepository carRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    @Transactional
    public RentalDTO createRental(RentalDTO rentalDTO) {
        User user = userRepository.findById(rentalDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        
        Car car = carRepository.findById(rentalDTO.getCarId())
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));

        if (!car.getAvailable()) {
            throw new IllegalStateException("Car is not available for rental");
        }

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setCar(car);
        rental.setStartDate(LocalDateTime.now());
        rental.setStatus(RentalStatus.ACTIVE);
        rental.setCovachoRentalNotes(rentalDTO.getCovachoRentalNotes());

        car.setAvailable(false);
        carRepository.save(car);

        rental = rentalRepository.save(rental);
        
        BeanUtils.copyProperties(rental, rentalDTO);
        return rentalDTO;
    }

    @Transactional
    public RentalDTO returnRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new EntityNotFoundException("Rental not found"));

        if (rental.getStatus() != RentalStatus.ACTIVE) {
            throw new IllegalStateException("Rental is not active");
        }

        rental.setEndDate(LocalDateTime.now());
        rental.setStatus(RentalStatus.COMPLETED);

        Car car = rental.getCar();
        car.setAvailable(true);
        carRepository.save(car);

        rental = rentalRepository.save(rental);

        RentalDTO rentalDTO = new RentalDTO();
        BeanUtils.copyProperties(rental, rentalDTO);
        return rentalDTO;
    }

    public List<RentalDTO> getUserRentals(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found");
        }

        return rentalRepository.findByUserId(userId).stream()
                .map(rental -> {
                    RentalDTO rentalDTO = new RentalDTO();
                    BeanUtils.copyProperties(rental, rentalDTO);
                    return rentalDTO;
                })
                .collect(Collectors.toList());
    }
} 