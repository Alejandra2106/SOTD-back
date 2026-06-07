package com.tareasdomesticas.hogar_service.historial.domain.model;

import java.time.LocalDateTime;

public class EntradaHistorial {

    private final Long id;
    private final Long idHogar;
    private final Long idTarea; 
    private final String nombreTarea; 
    private final TipoAccion tipoAccion;
    private final Long idUsuarioActor;
    private final String nombreUsuarioActor;
    private final LocalDateTime fechaHora;
    private final String detalle;

    public EntradaHistorial(Long id, Long idHogar, Long idTarea, String nombreTarea,
            TipoAccion tipoAccion, Long idUsuarioActor,
            String nombreUsuarioActor, LocalDateTime fechaHora,
            String detalle) {
        if (idHogar == null)
            throw new IllegalArgumentException("idHogar es obligatorio.");
        if (tipoAccion == null)
            throw new IllegalArgumentException("tipoAccion es obligatorio.");
        if (idUsuarioActor == null)
            throw new IllegalArgumentException("idUsuarioActor es obligatorio.");
        if (fechaHora == null)
            throw new IllegalArgumentException("fechaHora es obligatoria.");
        this.id = id;
        this.idHogar = idHogar;
        this.idTarea = idTarea;
        this.nombreTarea = nombreTarea;
        this.tipoAccion = tipoAccion;
        this.idUsuarioActor = idUsuarioActor;
        this.nombreUsuarioActor = nombreUsuarioActor;
        this.fechaHora = fechaHora;
        this.detalle = detalle;
    }

    public Long getId() {
        return id;
    }

    public Long getIdHogar() {
        return idHogar;
    }

    public Long getIdTarea() {
        return idTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public TipoAccion getTipoAccion() {
        return tipoAccion;
    }

    public Long getIdUsuarioActor() {
        return idUsuarioActor;
    }

    public String getNombreUsuarioActor() {
        return nombreUsuarioActor;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getDetalle() {
        return detalle;
    }
}
