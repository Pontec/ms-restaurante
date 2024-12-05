package com.pioriko.ms_restaurante.dao;

import com.pioriko.ms_restaurante.entities.MetodoPagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetodoPagoRepository extends JpaRepository<MetodoPagoEntity, Integer> {
    List<MetodoPagoEntity> findByActivoTrue();
}
