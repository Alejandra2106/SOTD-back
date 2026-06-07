package com.tareasdomesticas.hogar_service.hogares.infrastructure.adapter.out;

import com.tareasdomesticas.hogar_service.dashboard.application.port.out.ObtenerInfoHogarPort;
import com.tareasdomesticas.hogar_service.hogares.domain.port.out.HogarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ObtenerInfoHogarAdapter implements ObtenerInfoHogarPort {

    private final HogarRepository hogarRepository;

    @Override
    public Optional<InfoHogar> obtenerInfoHogar(Long idHogar) {
        return hogarRepository.buscarPorId(idHogar)
                .map(hogar -> {
                    List<MiembroInfo> miembros = hogar.getUsuarios().stream()
                            .filter(u -> u.getIdUsuario() != null)
                            .map(u -> new MiembroInfo(u.getIdUsuario(), u.getNombreUsuario()))
                            .toList();
                    return new InfoHogar(hogar.getIdHogar(), miembros);
                });
    }
}
