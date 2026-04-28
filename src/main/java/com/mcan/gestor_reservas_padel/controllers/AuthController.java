package com.mcan.gestor_reservas_padel.controllers;

import com.mcan.gestor_reservas_padel.dtos.AuthResponseDTO;
import com.mcan.gestor_reservas_padel.dtos.LoginRequestDTO;
import com.mcan.gestor_reservas_padel.dtos.UsuarioDTO;
import com.mcan.gestor_reservas_padel.entities.Usuario;
import com.mcan.gestor_reservas_padel.repositories.UsuarioRepository;
import com.mcan.gestor_reservas_padel.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setRol("USER");
        usuarioRepository.save(usuario);

        String token = jwtUtil.generateToken(usuario.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponseDTO(token, usuario.getEmail(), usuario.getNombre(), usuario.getRol()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));

        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow();

        String token = jwtUtil.generateToken(usuario.getEmail());
        return ResponseEntity.ok(
                new AuthResponseDTO(token, usuario.getEmail(), usuario.getNombre(), usuario.getRol()));
    }
}
