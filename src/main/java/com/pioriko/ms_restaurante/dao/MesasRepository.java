package com.pioriko.ms_restaurante.dao;

import com.pioriko.ms_restaurante.entities.MesasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesasRepository extends JpaRepository<MesasEntity, Integer> {
    MesasEntity findByNumeroMesa(String numeroMesa);
}
