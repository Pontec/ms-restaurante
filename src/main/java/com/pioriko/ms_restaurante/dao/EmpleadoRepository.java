package com.pioriko.ms_restaurante.dao;

import com.pioriko.ms_restaurante.entities.EmpleadosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<EmpleadosEntity, Long> {
    Optional<EmpleadosEntity> findByCorreo(String email);
}
