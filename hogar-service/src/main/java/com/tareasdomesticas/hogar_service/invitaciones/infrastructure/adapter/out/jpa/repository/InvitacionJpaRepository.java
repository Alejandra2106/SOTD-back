package com.tareasdomesticas.hogar_service.invitaciones.infrastructure.adapter.out.jpa.repository;

import com.tareasdomesticas.hogar_service.invitaciones.domain.model.EstadoInvitacion;
import com.tareasdomesticas.hogar_service.invitaciones.infrastructure.adapter.out.jpa.entity.InvitacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InvitacionJpaRepository extends JpaRepository<InvitacionEntity, Long> {

    Optional<InvitacionEntity> findByCorreoInvitadoAndIdHogarAndEstado(
            String correoInvitado, Long idHogar, EstadoInvitacion estado);

    List<InvitacionEntity> findByCorreoInvitadoAndEstado(
            String correoInvitado, EstadoInvitacion estado);
    @Query("""
            SELECT COUNT(u) > 0
            FROM com.tareasdomesticas.hogar_service.hogares.infrastructure.adapter.out.jpa.entity.UsuarioEntity u
            WHERE u.correoUsuario = :correo
              AND u.hogar IS NOT NULL
              AND u.hogar.idHogar = :idHogar
            """)
    boolean existsMiembroConCorreo(@Param("correo") String correo,
                                   @Param("idHogar") Long idHogar);
}
