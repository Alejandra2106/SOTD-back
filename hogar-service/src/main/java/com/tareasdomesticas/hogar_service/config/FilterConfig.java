package com.tareasdomesticas.hogar_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tareasdomesticas.hogar_service.auth.application.port.out.ValidarSesionPort;
import com.tareasdomesticas.hogar_service.infrastructure.filter.TokenAuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<TokenAuthFilter> tokenAuthFilter(
            ValidarSesionPort validarSesionPort,
            ObjectMapper objectMapper) {

        FilterRegistrationBean<TokenAuthFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new TokenAuthFilter(validarSesionPort, objectMapper));
        registration.addUrlPatterns("/api/*");
        registration.setOrder(1);
        registration.setName("tokenAuthFilter");
        return registration;
    }
}
