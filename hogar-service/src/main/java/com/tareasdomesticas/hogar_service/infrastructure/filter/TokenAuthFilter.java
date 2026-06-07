package com.tareasdomesticas.hogar_service.infrastructure.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tareasdomesticas.hogar_service.auth.application.port.out.ValidarSesionPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TokenAuthFilter extends OncePerRequestFilter {

    private static final List<String> RUTAS_PUBLICAS = List.of(
            "/api/auth/registro",
            "/api/auth/login");

    private final ValidarSesionPort validarSesionPort;
    private final ObjectMapper      objectMapper;
    private static final Logger log = LoggerFactory.getLogger(TokenAuthFilter.class);

    public TokenAuthFilter(ValidarSesionPort validarSesionPort, ObjectMapper objectMapper) {
        this.validarSesionPort = validarSesionPort;
        this.objectMapper      = objectMapper;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return RUTAS_PUBLICAS.stream().anyMatch(path::equals);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path       = request.getRequestURI();
        String method     = request.getMethod();
        String authHeader = request.getHeader("Authorization");
        log.debug("TokenAuthFilter invoked: {} {} AuthorizationPresent={}", path, method, authHeader != null);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("Authorization header ausente o mal formado: {} for {} {}", authHeader, method, path);
            escribirError(response, HttpStatus.UNAUTHORIZED,
                    "No autenticado. Debe iniciar sesión para acceder a este recurso.");
            return;
        }

        String token = authHeader.substring(7).trim();

        boolean esValida;
        try {
            esValida = validarSesionPort.esSesionValida(token);
        } catch (Exception e) {
            log.error("Error al verificar sesión para token {}", token, e);
            escribirError(response, HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error interno al verificar la sesión.");
            return;
        }

        if (!esValida) {
            log.warn("Sesión inválida para token: {}", token);
            escribirError(response, HttpStatus.UNAUTHORIZED,
                    "Sesión inválida o expirada. Inicie sesión nuevamente.");
            return;
        }

        try {
            validarSesionPort.obtenerIdUsuario(token)
                    .ifPresent(idUsuario -> request.setAttribute("idUsuario", idUsuario));
        } catch (Exception e) {
            log.error("Error al obtener idUsuario para token {}", token, e);
            escribirError(response, HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error interno al verificar la sesión.");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void escribirError(HttpServletResponse response,
                               HttpStatus status, String mensaje) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), Map.of("mensaje", mensaje));
    }
}
