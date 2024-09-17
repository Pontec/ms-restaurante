package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.dao.CategoriaRepository;
import com.pioriko.ms_restaurante.entities.Categoria;
import com.pioriko.ms_restaurante.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {


    private final CategoriaRepository categoriaRepository;

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Categoria no encontrada"));
    }

    @Override
    public void deleteById(Integer id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public Categoria update(Integer id, Categoria categoria) {

        Categoria existeCategoria = findById(id);
        existeCategoria.setDescripcion(categoria.getDescripcion());
        existeCategoria.setEstado(categoria.getEstado());
        existeCategoria.setProductos(categoria.getProductos());

        return categoriaRepository.save(existeCategoria);
    }
}
