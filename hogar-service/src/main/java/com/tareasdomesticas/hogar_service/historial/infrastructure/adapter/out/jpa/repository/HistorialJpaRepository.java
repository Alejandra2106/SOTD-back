package com.tareasdomesticas.hogar_service.historial.infrastructure.adapter.out.jpa.repository;

import com.tareasdomesticas.hogar_service.historial.infrastructure.adapter.out.jpa.entity.EntradaHistorialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialJpaRepository extends JpaRepository<EntradaHistorialEntity, Long> {

    List<EntradaHistorialEntity> findByIdHogarOrderByFechaHoraDesc(Long idHogar);

    List<EntradaHistorialEntity> findByIdHogarAndIdUsuarioActorOrderByFechaHoraDesc(
            Long idHogar, Long idUsuarioActor);
}
