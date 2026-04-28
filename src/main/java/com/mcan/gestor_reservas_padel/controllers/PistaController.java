package com.mcan.gestor_reservas_padel.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcan.gestor_reservas_padel.dtos.PistaDTO;
import com.mcan.gestor_reservas_padel.services.PistaService;

@RestController
@RequestMapping("/api/pistas")
public class PistaController {

    private final PistaService pistaService;

    public PistaController(PistaService pistaService) {
        this.pistaService = pistaService;
    }

    @PostMapping
    public ResponseEntity<PistaDTO> crearPista(@RequestBody PistaDTO pistaDTO) {
        PistaDTO pistaCreada = pistaService.crearPista(pistaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pistaCreada);
    }

    @GetMapping
    public ResponseEntity<List<PistaDTO>> obtenerPistas() {
        List<PistaDTO> pistas = pistaService.obtenerPistas();
        return ResponseEntity.ok(pistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PistaDTO> obtenerPistaPorId(@PathVariable Long id) {
        PistaDTO pista = pistaService.obtenerPistaPorId(id);
        return ResponseEntity.ok(pista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PistaDTO> actualizarPista(@PathVariable Long id, @RequestBody PistaDTO pistaDTO) {
        PistaDTO pistaActualizada = pistaService.actualizarPista(id, pistaDTO);
        return ResponseEntity.ok(pistaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPista(@PathVariable Long id) {
        pistaService.eliminarPista(id);
        return ResponseEntity.noContent().build();
    }
}

