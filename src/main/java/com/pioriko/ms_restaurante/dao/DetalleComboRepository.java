package com.pioriko.ms_restaurante.dao;

import com.pioriko.ms_restaurante.entities.DetalleComboEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleComboRepository extends JpaRepository<DetalleComboEntity, Integer> {
}
