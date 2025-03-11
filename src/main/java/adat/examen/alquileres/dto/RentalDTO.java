package adat.examen.alquileres.dto;

import adat.examen.alquileres.entity.RentalStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RentalDTO {
    private Long id;
    private Long userId;
    private Long carId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private RentalStatus status;
    private String covachoRentalNotes;
} 