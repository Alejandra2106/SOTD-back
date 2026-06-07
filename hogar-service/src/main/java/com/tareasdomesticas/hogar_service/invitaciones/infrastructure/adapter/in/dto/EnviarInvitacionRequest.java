package com.tareasdomesticas.hogar_service.invitaciones.infrastructure.adapter.in.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnviarInvitacionRequest {
    private Long idAdministrador;

    @NotBlank(message = "El nombre es obligatorio (mín. 2 caracteres).")
    private String nombre;
    @NotBlank(message = "El correo es obligatorio.")
    private String correo;
}
