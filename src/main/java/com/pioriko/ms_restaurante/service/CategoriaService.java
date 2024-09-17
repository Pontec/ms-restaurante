package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.entities.Categoria;

import java.util.List;

public interface CategoriaService {
    Categoria save(Categoria categoria);
    List<Categoria> findAll();
    Categoria findById(Integer id);
    void deleteById(Integer id);
    Categoria update(Integer id, Categoria categoria);

}
