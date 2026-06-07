package com.tareasdomesticas.hogar_service.hogares.application.port.in;

public record EliminarMiembroCommand(
                Long hogarId,
                Long idAdministrador,
                Long idMiembro,
                String nombreMiembro
) {
        public EliminarMiembroCommand(Long hogarId, Long idAdministrador, Long idMiembro) {
                this(hogarId, idAdministrador, idMiembro, null);
        }
}
