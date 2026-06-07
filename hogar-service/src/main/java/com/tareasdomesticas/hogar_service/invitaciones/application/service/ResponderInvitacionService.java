package com.tareasdomesticas.hogar_service.invitaciones.application.service;

import com.tareasdomesticas.hogar_service.invitaciones.application.dto.InvitacionDTO;
import com.tareasdomesticas.hogar_service.invitaciones.application.port.in.ResponderInvitacionCommand;
import com.tareasdomesticas.hogar_service.invitaciones.application.port.in.ResponderInvitacionUseCase;
import com.tareasdomesticas.hogar_service.invitaciones.application.port.out.RegistrarMiembroPort;
import com.tareasdomesticas.hogar_service.invitaciones.domain.model.Invitacion;
import com.tareasdomesticas.hogar_service.invitaciones.domain.port.out.InvitacionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponderInvitacionService implements ResponderInvitacionUseCase {

    private static final Logger log = LoggerFactory.getLogger(ResponderInvitacionService.class);

    private final InvitacionRepository invitacionRepo;
    private final RegistrarMiembroPort registrarMiembroPort;

    public ResponderInvitacionService(InvitacionRepository invitacionRepo,
                                      RegistrarMiembroPort registrarMiembroPort) {
        this.invitacionRepo      = invitacionRepo;
        this.registrarMiembroPort = registrarMiembroPort;
    }

    @Override
    public InvitacionDTO responder(ResponderInvitacionCommand cmd) {

        Invitacion inv = invitacionRepo.buscarPorId(cmd.idInvitacion())
                .orElseThrow(() -> new IllegalArgumentException("La invitación no existe."));

        if (cmd.aceptar()) {
            registrarMiembroPort.registrarMiembro(
                    inv.getIdHogar(),
                    inv.getNombreInvitado(),
                    inv.getCorreoInvitado());

            inv.aceptar();
            log.info("Invitación aceptada: id={}, hogar={}", inv.getId(), inv.getIdHogar());
        } else {
            inv.rechazar();
            log.info("Invitación rechazada: id={}", inv.getId());
        }

        Invitacion actualizada = invitacionRepo.guardar(inv);
        return new InvitacionDTO(
                actualizada.getId(),
                actualizada.getIdHogar(),
                actualizada.getNombreInvitado(),
                actualizada.getCorreoInvitado(),
                actualizada.getEstado().name(),
                actualizada.getFechaEnvio());
    }
}
