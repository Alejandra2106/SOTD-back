package com.tareasdomesticas.hogar_service.auth.application.dto;

public record LoginResultDTO(Long idUsuario, String nombre, String correo,
                              boolean tieneHogar, String rol, String token) {}
