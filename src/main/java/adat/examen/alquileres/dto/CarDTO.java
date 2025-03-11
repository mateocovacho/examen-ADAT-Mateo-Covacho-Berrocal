package adat.examen.alquileres.dto;

import lombok.Data;

@Data
public class CarDTO {
    private Long id;
    private String plateNumber;
    private String brand;
    private String model;
    private Integer year;
    private Boolean available;
    private String covachoColor;
} 