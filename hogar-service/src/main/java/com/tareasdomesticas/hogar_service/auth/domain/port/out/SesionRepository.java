package com.tareasdomesticas.hogar_service.auth.domain.port.out;

public interface SesionRepository {

    void crearSesion(Long idUsuario, String token, String ipOrigen, String userAgent);

    void actualizarSesionMetadata(String token, String refreshToken,
            String ipOrigen, String userAgent);

    String obtenerRefreshTokenPorToken(String token);

    boolean invalidarSesion(String token);

    boolean esSesionValida(String token);

    Long obtenerIdUsuarioPorToken(String token);
}
