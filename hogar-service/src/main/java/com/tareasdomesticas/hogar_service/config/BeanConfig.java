package com.tareasdomesticas.hogar_service.config;

import com.tareasdomesticas.hogar_service.auth.application.port.in.IniciarSesionUseCase;
import com.tareasdomesticas.hogar_service.auth.application.port.in.RegistrarUsuarioUseCase;
import com.tareasdomesticas.hogar_service.auth.application.service.IniciarSesionService;
import com.tareasdomesticas.hogar_service.auth.application.service.RegistrarUsuarioService;
import com.tareasdomesticas.hogar_service.auth.domain.port.out.CuentaUsuarioRepository;
import com.tareasdomesticas.hogar_service.auth.domain.port.out.PasswordEncoderPort;
import com.tareasdomesticas.hogar_service.auth.domain.port.out.SesionRepository;
import com.tareasdomesticas.hogar_service.common.application.port.out.ResolverNombreUsuarioPort;
import com.tareasdomesticas.hogar_service.dashboard.application.port.in.ObtenerDashboardUseCase;
import com.tareasdomesticas.hogar_service.dashboard.application.port.out.ObtenerInfoHogarPort;
import com.tareasdomesticas.hogar_service.dashboard.application.service.DashboardService;

import com.tareasdomesticas.hogar_service.historial.application.port.in.ConsultarHistorialUseCase;
import com.tareasdomesticas.hogar_service.historial.application.port.in.RegistrarAccionHistorialUseCase;
import com.tareasdomesticas.hogar_service.historial.application.service.HistorialService;
import com.tareasdomesticas.hogar_service.historial.domain.port.out.HistorialRepository;

import com.tareasdomesticas.hogar_service.hogares.application.port.in.*;
import com.tareasdomesticas.hogar_service.hogares.application.service.*;
import com.tareasdomesticas.hogar_service.hogares.domain.port.out.HogarRepository;
import com.tareasdomesticas.hogar_service.hogares.infrastructure.adapter.out.ObtenerMiembrosHogarAdapter;

import com.tareasdomesticas.hogar_service.invitaciones.application.port.in.*;
import com.tareasdomesticas.hogar_service.invitaciones.application.port.out.RegistrarMiembroPort;
import com.tareasdomesticas.hogar_service.invitaciones.application.service.*;
import com.tareasdomesticas.hogar_service.invitaciones.domain.port.out.InvitacionRepository;
import com.tareasdomesticas.hogar_service.hogares.infrastructure.adapter.out.RegistrarMiembroAdapter;

import com.tareasdomesticas.hogar_service.tareas.application.port.in.*;
import com.tareasdomesticas.hogar_service.tareas.application.port.out.*;
import com.tareasdomesticas.hogar_service.tareas.application.service.*;
import com.tareasdomesticas.hogar_service.tareas.domain.port.out.*;
import com.tareasdomesticas.hogar_service.tareas.infrastructure.adapter.out.LiberarTareasAdapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BeanConfig {


    @Bean
    public ObtenerMiembrosHogarPort obtenerMiembrosHogarPort(HogarRepository hogarRepo) {
        return new ObtenerMiembrosHogarAdapter(hogarRepo);
    }

    @Bean
    public LiberarTareasPort liberarTareasPort(AsignacionSemanalRepository asignacionRepo) {
        return new LiberarTareasAdapter(asignacionRepo);
    }
    @Bean
    public RegistrarUsuarioUseCase registrarUsuarioUseCase(CuentaUsuarioRepository r,
                                                            PasswordEncoderPort enc) {
        return new RegistrarUsuarioService(r, enc);
    }
    @Bean
    public IniciarSesionUseCase iniciarSesionUseCase(CuentaUsuarioRepository r,
                                                      PasswordEncoderPort enc,
                                                      SesionRepository sesionRepo) {
        return new IniciarSesionService(r, enc, sesionRepo);
    }


    @Bean
    public HistorialService historialService(HistorialRepository historialRepo) {
        return new HistorialService(historialRepo);
    }

    @Bean
    public ConsultarHistorialUseCase consultarHistorialUseCase(HistorialService s) { return s; }

    @Bean
    @Primary
    public RegistrarAccionHistorialUseCase registrarAccionHistorialUseCase(HistorialService s) { return s; }
    @Bean
    public ObtenerDashboardUseCase obtenerDashboardUseCase(TareaRepository t,
                                                            AsignacionSemanalRepository a,
                                                            ObtenerInfoHogarPort p) {
        return new DashboardService(t, a, p);
    }

    @Bean
    public CrearHogarUseCase crearHogarUseCase(HogarRepository r) {
        return new CrearHogarService(r);
    }

    @Bean
    public AgregarMiembroUseCase agregarMiembroUseCase(HogarRepository r, CuentaUsuarioRepository c, RegistrarAccionHistorialUseCase historial, ResolverNombreUsuarioPort rn) {
        return new AgregarMiembroService(r, c, historial, rn);
    }

    @Bean
    public EliminarMiembroUseCase eliminarMiembroUseCase(HogarRepository h, LiberarTareasPort l, RegistrarAccionHistorialUseCase historial, ResolverNombreUsuarioPort rn) {
        return new EliminarMiembroService(h, l, historial, rn);
    }


    @Bean
    public EnviarInvitacionUseCase enviarInvitacionUseCase(InvitacionRepository r,
                                                            HogarRepository h) {
        return new EnviarInvitacionService(r, h);
    }

    @Bean
    public RegistrarMiembroPort registrarMiembroPort(HogarRepository h,
                                                      AgregarMiembroUseCase a) {
        return new RegistrarMiembroAdapter(h, a);
    }

    @Bean
    public ResponderInvitacionUseCase responderInvitacionUseCase(InvitacionRepository r,
                                                                  RegistrarMiembroPort p) {
        return new ResponderInvitacionService(r, p);
    }

    @Bean
    public ListarInvitacionesPendientesUseCase listarInvitacionesPendientesUseCase(
            InvitacionRepository r) {
        return new ListarInvitacionesPendientesService(r);
    }

    @Bean
    public CrearTareaUseCase crearTareaUseCase(TareaRepository r, RegistrarAccionHistorialUseCase h, ResolverNombreUsuarioPort rn) {
        return new CrearTareaService(r, h, rn);
    }

    @Bean
    public EditarTareaUseCase editarTareaUseCase(TareaRepository r, AsignacionSemanalRepository a, RegistrarAccionHistorialUseCase h, ResolverNombreUsuarioPort rn) {
        return new EditarTareaService(r, a, h, rn);
    }

    @Bean
    public EliminarTareaUseCase eliminarTareaUseCase(TareaRepository t, AsignacionSemanalRepository a, RegistrarAccionHistorialUseCase h, ResolverNombreUsuarioPort rn) {
        return new EliminarTareaService(t, a, h, rn);
    }

    @Bean
    public CambiarEstadoTareaUseCase cambiarEstadoTareaUseCase(TareaRepository t,
                                                                AsignacionSemanalRepository a,
                                                                RegistrarAccionHistorialUseCase h,
                                                                ResolverNombreUsuarioPort rn) {
        return new CambiarEstadoTareaService(t, a, h, rn);
    }

    @Bean
    public AsignarTareaUseCase asignarTareaUseCase(TareaRepository t, AsignacionSemanalRepository a,
                                                    ObtenerMiembrosHogarPort p,
                                                    RegistrarAccionHistorialUseCase h,
                                                    ResolverNombreUsuarioPort rn) {
        return new AsignarTareaService(t, a, p, h, rn);
    }

    @Bean
    public ListarTareasUseCase listarTareasUseCase(TareaRepository t, AsignacionSemanalRepository a) {
        return new ListarTareasService(t, a);
    }

    @Bean
    public FiltrarTareasUseCase filtrarTareasUseCase(TareaRepository t, AsignacionSemanalRepository a) {
        return new FiltrarTareasService(t, a);
    }
}
