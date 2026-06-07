package com.tareasdomesticas.hogar_service.auth.application.port.out;

import java.util.Optional;
public interface ValidarSesionPort {
    boolean esSesionValida(String token);
    Optional<Long> obtenerIdUsuario(String token);
}
