package com.tareasdomesticas.hogar_service.auth.infrastructure.adapter.out;

import com.tareasdomesticas.hogar_service.auth.application.port.out.ValidarSesionPort;
import com.tareasdomesticas.hogar_service.auth.domain.port.out.SesionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class ValidarSesionAdapter implements ValidarSesionPort {

    private final SesionRepository sesionRepository;

    @Override
    public boolean esSesionValida(String token) {
        return sesionRepository.esSesionValida(token);
    }

    @Override
    public Optional<Long> obtenerIdUsuario(String token) {
        return Optional.ofNullable(sesionRepository.obtenerIdUsuarioPorToken(token));
    }
}
