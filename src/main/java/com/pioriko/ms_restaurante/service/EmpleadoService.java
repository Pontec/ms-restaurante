package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.agregates.dto.EmpleadoDTO;
import com.pioriko.ms_restaurante.entities.EmpleadosEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmpleadoService {
    UserDetailsService userDetailService();
    List<EmpleadosEntity> getUsuarios();
    EmpleadoDTO getEmpleadoReniec(String dni);
}
