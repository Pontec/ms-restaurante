package com.pioriko.ms_restaurante.dao;

import com.pioriko.ms_restaurante.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {


}
