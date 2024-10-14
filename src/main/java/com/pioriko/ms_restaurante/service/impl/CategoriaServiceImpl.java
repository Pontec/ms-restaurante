package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.dto.CategoriaDTO;
import com.pioriko.ms_restaurante.agregates.mapper.CategoriaMapper;
import com.pioriko.ms_restaurante.dao.CategoriaRepository;
import com.pioriko.ms_restaurante.entities.CategoriaEntity;
import com.pioriko.ms_restaurante.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {


    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        // Convertir el DTO a entidad
        CategoriaEntity categoria = categoriaMapper.mapToCategoriaEntity(categoriaDTO);
        CategoriaEntity categoriaEntity = categoriaRepository.save(categoria);
        return categoriaMapper.mapToCategoriaDTO(categoriaEntity);
    }

    @Override
    public List<CategoriaDTO> findAll() {
        List<CategoriaEntity> categorias = categoriaRepository.findAll();
        return  categorias.stream().map(categoriaMapper::mapToCategoriaDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<CategoriaDTO> findById(Integer id) {
        Optional<CategoriaEntity> categoriaEntity = categoriaRepository.findById(id);
        if (categoriaEntity.isPresent()) {
            return Optional.of(categoriaMapper.mapToCategoriaDTO(categoriaEntity.get()));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {
        if(categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
        }
    }

    @Override
    public CategoriaDTO update(Integer id, CategoriaDTO categoriaDTO) {
        CategoriaEntity categoriaEntity = categoriaRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Categoria no encontrada"));
        if (categoriaEntity != null) {
            categoriaEntity.setNombre(categoriaDTO.getNombre());
            categoriaRepository.save(categoriaEntity);
        }

        return categoriaMapper.mapToCategoriaDTO(categoriaEntity);
    }
}
