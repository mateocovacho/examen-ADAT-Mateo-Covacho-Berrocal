package adat.examen.alquileres.controller;

import adat.examen.alquileres.dto.RentalDTO;
import adat.examen.alquileres.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    public ResponseEntity<RentalDTO> createRental(@RequestBody RentalDTO rentalDTO) {
        return ResponseEntity.ok(rentalService.createRental(rentalDTO));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<RentalDTO> returnRental(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.returnRental(id));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<RentalDTO>> getUserRentals(@PathVariable Long userId) {
        return ResponseEntity.ok(rentalService.getUserRentals(userId));
    }
} 