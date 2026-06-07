package com.tareasdomesticas.hogar_service.invitaciones.application.port.out;

public interface RegistrarMiembroPort {

    /**
     * Agrega el miembro al hogar si aún no existe.
     *
     * @param idHogar         hogar destino
     * @param nombreInvitado  nombre del nuevo miembro
     * @param correoInvitado  correo del nuevo miembro (usado como identificador)
     */
    void registrarMiembro(Long idHogar, String nombreInvitado, String correoInvitado);
}
