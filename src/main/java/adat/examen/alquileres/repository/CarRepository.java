package adat.examen.alquileres.repository;

import adat.examen.alquileres.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBrandContainingIgnoreCaseOrModelContainingIgnoreCase(String brand, String model);
    List<Car> findByAvailableTrue();
    boolean existsByPlateNumber(String plateNumber);
} 