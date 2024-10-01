package com.pioriko.ms_restaurante.dao;

import com.pioriko.ms_restaurante.entities.CombosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboRepository extends JpaRepository<CombosEntity, Integer> {
}
