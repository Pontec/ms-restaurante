package com.pioriko.ms_restaurante.dao;

import com.pioriko.ms_restaurante.entities.ClientesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClientesEntity, Integer> {
    Boolean existsByDni(String dni);
    ClientesEntity findByDni(String dni);
}
