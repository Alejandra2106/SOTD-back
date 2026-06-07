package com.tareasdomesticas.hogar_service.dashboard.application.port.out;

import java.util.List;
import java.util.Optional;

public interface ObtenerInfoHogarPort {

    Optional<InfoHogar> obtenerInfoHogar(Long idHogar);

    record InfoHogar(Long idHogar, List<MiembroInfo> miembros) {}

    record MiembroInfo(Long idUsuario, String nombreUsuario) {}
}
