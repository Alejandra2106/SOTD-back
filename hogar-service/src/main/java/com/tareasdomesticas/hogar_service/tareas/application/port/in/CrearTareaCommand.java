package com.tareasdomesticas.hogar_service.tareas.application.port.in;

import java.time.LocalDateTime;

public record CrearTareaCommand(
    Long idHogar,
    String nombreTarea,
    String descripcionTarea,
    String fotoTarea,
    LocalDateTime fechaLimite,
    String dificultad,
    String prioridad
) {}