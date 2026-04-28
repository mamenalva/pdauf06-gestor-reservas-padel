package com.mcan.gestor_reservas_padel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcan.gestor_reservas_padel.entities.Pista;

@Repository
public interface PistaRepository extends JpaRepository<Pista, Long> {
}

