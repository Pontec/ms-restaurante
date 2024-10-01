package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.dto.CategoriaDTO;
import com.pioriko.ms_restaurante.agregates.mapper.CategoriaMapper;
import com.pioriko.ms_restaurante.dao.CategoriaRepository;
import com.pioriko.ms_restaurante.entities.CategoriaEntity;
import com.pioriko.ms_restaurante.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {


    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public CategoriaEntity save(CategoriaDTO categoriaDTO) {
        // Convertir el DTO a entidad

        CategoriaEntity categoria = categoriaMapper.mapToEntity(categoriaDTO);

        // Guardar la categoria
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<CategoriaEntity> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public CategoriaEntity findById(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Categoria no encontrada"));
    }

    @Override
    public void deleteById(Integer id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public CategoriaEntity update(Integer id, CategoriaDTO categoriaDTO) {

        CategoriaEntity existeCategoria = findById(id);
        existeCategoria.setNombre(categoriaDTO.getNombre());

        return categoriaRepository.save(existeCategoria);
    }
}
