package com.pioriko.ms_restaurante.dao;

import com.pioriko.ms_restaurante.entities.CajaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CajaRepository extends JpaRepository<CajaEntity, Integer> {

    //este metodo nos permite obtener la ultima caja abierta
    CajaEntity findFirstByOrderByFechaAperturaDesc();

    List<CajaEntity> findAllByOrderByFechaAperturaDesc();

    //este metodo nos permite obtener la caja abierta
    Optional<CajaEntity> findFirstByAbiertaTrue();
}
