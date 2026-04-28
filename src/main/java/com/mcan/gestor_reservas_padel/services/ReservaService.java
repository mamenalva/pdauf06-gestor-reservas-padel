package com.mcan.gestor_reservas_padel.services;

import java.util.List;

import com.mcan.gestor_reservas_padel.dtos.ReservaDTO;

public interface ReservaService {

    ReservaDTO crearReserva(ReservaDTO reservaDTO);

    List<ReservaDTO> obtenerReservas();

    void eliminarReserva(Long id);
}

