package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.agregates.dto.CategoriaDTO;
import com.pioriko.ms_restaurante.entities.CategoriaEntity;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    CategoriaDTO save(CategoriaDTO categoriaDTO);
    List<CategoriaDTO> findAll();
    Optional<CategoriaDTO> findById(Integer id);
    void deleteById(Integer id);
    CategoriaDTO update(Integer id, CategoriaDTO categoriaDTO);

}
