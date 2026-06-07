package com.tareasdomesticas.hogar_service.hogares.infrastructure.adapter.out;

import com.tareasdomesticas.hogar_service.hogares.application.port.in.AgregarMiembroCommand;
import com.tareasdomesticas.hogar_service.hogares.application.port.in.AgregarMiembroUseCase;
import com.tareasdomesticas.hogar_service.hogares.domain.model.Hogar;
import com.tareasdomesticas.hogar_service.hogares.domain.port.out.HogarRepository;
import com.tareasdomesticas.hogar_service.invitaciones.application.port.out.RegistrarMiembroPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegistrarMiembroAdapter implements RegistrarMiembroPort {

    private final HogarRepository hogarRepository;
    private final AgregarMiembroUseCase agregarMiembroUseCase;

    @Override
    public void registrarMiembro(Long idHogar, String nombreInvitado, String correoInvitado) {
        Hogar hogar = hogarRepository.buscarPorId(idHogar)
                .orElseThrow(() -> new IllegalArgumentException("El hogar no existe."));

        boolean yaExiste = hogar.getUsuarios().stream()
                .anyMatch(u -> u.getCorreoUsuario().equalsIgnoreCase(correoInvitado));

        if (!yaExiste) {
            agregarMiembroUseCase.agregarMiembro(new AgregarMiembroCommand(
                    idHogar,
                    hogar.getAdministrador().getIdUsuario(),
                    nombreInvitado,
                    correoInvitado));
        }
    }
}
