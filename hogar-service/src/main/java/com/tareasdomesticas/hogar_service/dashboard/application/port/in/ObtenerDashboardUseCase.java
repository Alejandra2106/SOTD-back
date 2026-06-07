package com.tareasdomesticas.hogar_service.dashboard.application.port.in;

import com.tareasdomesticas.hogar_service.dashboard.application.dto.DashboardDTO;

public interface ObtenerDashboardUseCase {
    /**
     * @param idHogar         
     * @param idUsuario       
     * @param esAdministrador 
     */
    DashboardDTO obtener(Long idHogar, Long idUsuario, boolean esAdministrador);
}
