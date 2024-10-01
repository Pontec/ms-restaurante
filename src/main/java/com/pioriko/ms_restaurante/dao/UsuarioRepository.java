package com.pioriko.ms_restaurante.dao;

import com.pioriko.ms_restaurante.entities.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Empleados, Long> {
    Optional<Empleados> findByCorreo(String email);
}
