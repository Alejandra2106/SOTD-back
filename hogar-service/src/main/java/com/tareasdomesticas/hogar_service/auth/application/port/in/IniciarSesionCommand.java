package com.tareasdomesticas.hogar_service.auth.application.port.in;

public record IniciarSesionCommand(
    String correo,
    String contrasena,
    String ipOrigen,     
    String userAgent    
) {}
