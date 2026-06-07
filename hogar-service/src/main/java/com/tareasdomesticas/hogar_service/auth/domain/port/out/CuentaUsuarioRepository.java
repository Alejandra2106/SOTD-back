package com.tareasdomesticas.hogar_service.auth.domain.port.out;

import com.tareasdomesticas.hogar_service.auth.domain.model.CuentaUsuario;
import java.util.Optional;
public interface CuentaUsuarioRepository {
    CuentaUsuario guardar(CuentaUsuario cuenta);
    Optional<CuentaUsuario> buscarPorCorreo(String correo);
    boolean existePorCorreo(String correo);
}
