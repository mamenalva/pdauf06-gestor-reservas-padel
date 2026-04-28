package com.mcan.gestor_reservas_padel.services;

import java.util.List;

import com.mcan.gestor_reservas_padel.dtos.UsuarioDTO;

public interface UsuarioService {

    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> obtenerUsuarios();

    UsuarioDTO obtenerUsuarioPorId(Long id);

    UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO);

    void eliminarUsuario(Long id);
}

