package com.pioriko.ms_restaurante.dao;

import com.pioriko.ms_restaurante.entities.CategoriaEntity;
import com.pioriko.ms_restaurante.entities.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {
    List<ProductoEntity> findByCategoria(CategoriaEntity categoria);

}
