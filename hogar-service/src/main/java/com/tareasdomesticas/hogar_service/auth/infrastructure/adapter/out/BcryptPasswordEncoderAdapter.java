package com.tareasdomesticas.hogar_service.auth.infrastructure.adapter.out;

import com.tareasdomesticas.hogar_service.auth.domain.port.out.PasswordEncoderPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptPasswordEncoderAdapter implements PasswordEncoderPort {

    private final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

    @Override
    public String encode(String rawPassword) {
        return bCrypt.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return bCrypt.matches(rawPassword, encodedPassword);
    }
}
