package com.mcan.gestor_reservas_padel.services;

import java.util.List;

import com.mcan.gestor_reservas_padel.dtos.PistaDTO;

public interface PistaService {

    PistaDTO crearPista(PistaDTO pistaDTO);

    List<PistaDTO> obtenerPistas();

    PistaDTO obtenerPistaPorId(Long id);

    PistaDTO actualizarPista(Long id, PistaDTO pistaDTO);

    void eliminarPista(Long id);
}

