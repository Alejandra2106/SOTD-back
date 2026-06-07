package com.tareasdomesticas.hogar_service.hogares.infrastructure.adapter.out.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hogares")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class HogarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hogar")
    private Long idHogar;

    @Column(name = "nombre_hogar", nullable = false, length = 50)
    private String nombreHogar;

    @Column(name = "descripcion_hogar", length = 255)
    private String descripcionHogar;

    @OneToMany(mappedBy = "hogar", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @Builder.Default
    private List<UsuarioEntity> usuarios = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    private java.time.LocalDateTime updatedAt;
}
