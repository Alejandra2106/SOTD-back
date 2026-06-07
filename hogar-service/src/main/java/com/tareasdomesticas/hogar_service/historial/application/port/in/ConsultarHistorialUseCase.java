package com.tareasdomesticas.hogar_service.historial.application.port.in;

import com.tareasdomesticas.hogar_service.historial.application.dto.EntradaHistorialDTO;
import java.util.List;

public interface ConsultarHistorialUseCase {
    List<EntradaHistorialDTO> consultar(Long idHogar, Long idUsuario, boolean esAdministrador);
}
