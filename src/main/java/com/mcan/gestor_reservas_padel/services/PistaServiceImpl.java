package com.mcan.gestor_reservas_padel.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mcan.gestor_reservas_padel.dtos.PistaDTO;
import com.mcan.gestor_reservas_padel.entities.Pista;
import com.mcan.gestor_reservas_padel.repositories.PistaRepository;

@Service
public class PistaServiceImpl implements PistaService {

    private final PistaRepository pistaRepository;

    public PistaServiceImpl(PistaRepository pistaRepository) {
        this.pistaRepository = pistaRepository;
    }

    @Override
    public PistaDTO crearPista(PistaDTO pistaDTO) {
        Pista pista = new Pista();
        pista.setNombre(pistaDTO.getNombre());
        pista.setUbicacion(pistaDTO.getUbicacion());
        pista.setDisponible(pistaDTO.isDisponible());

        Pista pistaGuardada = pistaRepository.save(pista);
        return convertirADTO(pistaGuardada);
    }

    @Override
    public List<PistaDTO> obtenerPistas() {
        return pistaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public PistaDTO obtenerPistaPorId(Long id) {
        Pista pista = pistaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pista no encontrada"));
        return convertirADTO(pista);
    }

    @Override
    public PistaDTO actualizarPista(Long id, PistaDTO pistaDTO) {
        Pista pista = pistaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pista no encontrada"));

        pista.setNombre(pistaDTO.getNombre());
        pista.setUbicacion(pistaDTO.getUbicacion());
        pista.setDisponible(pistaDTO.isDisponible());

        Pista pistaActualizada = pistaRepository.save(pista);
        return convertirADTO(pistaActualizada);
    }

    @Override
    public void eliminarPista(Long id) {
        if (!pistaRepository.existsById(id)) {
            throw new IllegalArgumentException("Pista no encontrada");
        }
        pistaRepository.deleteById(id);
    }

    private PistaDTO convertirADTO(Pista pista) {
        PistaDTO dto = new PistaDTO();
        dto.setId(pista.getId());
        dto.setNombre(pista.getNombre());
        dto.setUbicacion(pista.getUbicacion());
        dto.setDisponible(pista.isDisponible());
        return dto;
    }
}

