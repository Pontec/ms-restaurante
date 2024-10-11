package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.entities.Empleados;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmpleadoService {
    UserDetailsService userDetailService();
    List<Empleados> getUsuarios();
}
