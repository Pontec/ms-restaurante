package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.agregates.dto.ProductoDTO;
import com.pioriko.ms_restaurante.entities.ProductoEntity;

import java.util.List;

public interface ProductoService {
    ProductoDTO save(ProductoDTO productoDTO);
    List<ProductoEntity> findAll();
    ProductoDTO findById(int id);
    void deleteById(int id);
    ProductoDTO update(int id, ProductoDTO productoDTO);
}
