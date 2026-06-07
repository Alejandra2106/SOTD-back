package com.tareasdomesticas.hogar_service.historial.application.port.in;

import com.tareasdomesticas.hogar_service.historial.domain.model.TipoAccion;

public interface RegistrarAccionHistorialUseCase {
    void registrar(Long idHogar, Long idTarea, String nombreTarea,
            TipoAccion tipoAccion, Long idUsuarioActor, String nombreUsuarioActor,
            String detalle);
}
