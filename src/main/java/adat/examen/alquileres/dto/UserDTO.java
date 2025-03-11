package adat.examen.alquileres.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String covachoFavoriteColor;
} 