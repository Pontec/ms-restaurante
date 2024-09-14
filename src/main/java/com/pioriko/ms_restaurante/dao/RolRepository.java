package com.pioriko.ms_restaurante.dao;

import com.pioriko.ms_restaurante.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol,Long> {

    Optional<Rol> findByNombreRol(String rol);
}
