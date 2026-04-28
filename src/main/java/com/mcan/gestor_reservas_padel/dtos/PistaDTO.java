package com.mcan.gestor_reservas_padel.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PistaDTO {

    private Long id;
    private String nombre;
    private String ubicacion;
    private boolean disponible;
}

