package com.mcan.gestor_reservas_padel.services;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mcan.gestor_reservas_padel.dtos.ReservaDTO;
import com.mcan.gestor_reservas_padel.entities.Pista;
import com.mcan.gestor_reservas_padel.entities.Reserva;
import com.mcan.gestor_reservas_padel.entities.Usuario;
import com.mcan.gestor_reservas_padel.repositories.PistaRepository;
import com.mcan.gestor_reservas_padel.repositories.ReservaRepository;
import com.mcan.gestor_reservas_padel.repositories.UsuarioRepository;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PistaRepository pistaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository, UsuarioRepository usuarioRepository,
            PistaRepository pistaRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.pistaRepository = pistaRepository;
    }

    @Override
    public ReservaDTO crearReserva(ReservaDTO reservaDTO) {
        validarReserva(reservaDTO);

        Usuario usuario = usuarioRepository.findById(reservaDTO.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Pista pista = pistaRepository.findById(reservaDTO.getPistaId())
                .orElseThrow(() -> new IllegalArgumentException("Pista no encontrada"));

        Reserva reserva = new Reserva();
        reserva.setFecha(reservaDTO.getFecha());
        reserva.setHoraInicio(reservaDTO.getHoraInicio());
        reserva.setHoraFin(reservaDTO.getHoraFin());
        reserva.setUsuario(usuario);
        reserva.setPista(pista);

        Reserva reservaGuardada = reservaRepository.save(reserva);

        return convertirADTO(reservaGuardada);
    }

    @Override
    public List<ReservaDTO> obtenerReservas() {
        return reservaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarReserva(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new IllegalArgumentException("Reserva no encontrada");
        }
        reservaRepository.deleteById(id);
    }

    private void validarReserva(ReservaDTO reservaDTO) {
        // Validar que el horario sea uno de los permitidos
        boolean horarioValido = esHorarioValido(reservaDTO.getHoraInicio(), reservaDTO.getHoraFin());
        if (!horarioValido) {
            throw new IllegalArgumentException(
                    "Horario no permitido. Los horarios válidos son: 17:30-19:00, 19:00-20:30, 20:30-22:00, 22:00-23:30");
        }

        // Validar que no exista una reserva duplicada
        List<Reserva> reservasExistentes = reservaRepository.findAll();
        boolean reservaDuplicada = reservasExistentes.stream()
                .anyMatch(r -> r.getPista().getId().equals(reservaDTO.getPistaId())
                        && r.getFecha().equals(reservaDTO.getFecha())
                        && r.getHoraInicio().equals(reservaDTO.getHoraInicio())
                        && r.getHoraFin().equals(reservaDTO.getHoraFin()));

        if (reservaDuplicada) {
            throw new IllegalArgumentException(
                    "Ya existe una reserva para esta pista en la fecha y horario especificados");
        }
    }

    private boolean esHorarioValido(LocalTime horaInicio, LocalTime horaFin) {
        return (horaInicio.equals(LocalTime.of(17, 30)) && horaFin.equals(LocalTime.of(19, 0))) ||
               (horaInicio.equals(LocalTime.of(19, 0)) && horaFin.equals(LocalTime.of(20, 30))) ||
               (horaInicio.equals(LocalTime.of(20, 30)) && horaFin.equals(LocalTime.of(22, 0))) ||
               (horaInicio.equals(LocalTime.of(22, 0)) && horaFin.equals(LocalTime.of(23, 30)));
    }

    private ReservaDTO convertirADTO(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setFecha(reserva.getFecha());
        dto.setHoraInicio(reserva.getHoraInicio());
        dto.setHoraFin(reserva.getHoraFin());
        dto.setUsuarioId(reserva.getUsuario().getId());
        dto.setPistaId(reserva.getPista().getId());
        return dto;
    }
}

