package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.agregates.dto.CategoriaDTO;
import com.pioriko.ms_restaurante.entities.CategoriaEntity;

import java.util.List;

public interface CategoriaService {
    CategoriaEntity save(CategoriaDTO categoriaDTO);
    List<CategoriaEntity> findAll();
    CategoriaEntity findById(Integer id);
    void deleteById(Integer id);
    CategoriaEntity update(Integer id, CategoriaDTO categoriaDTO);

}
