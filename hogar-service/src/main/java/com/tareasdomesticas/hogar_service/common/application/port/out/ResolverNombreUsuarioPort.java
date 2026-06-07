package com.tareasdomesticas.hogar_service.common.application.port.out;


public interface ResolverNombreUsuarioPort {
    /** @return nombre del usuario, o "Usuario desconocido" si no existe. */
    String resolverNombre(Long idUsuario);
}
