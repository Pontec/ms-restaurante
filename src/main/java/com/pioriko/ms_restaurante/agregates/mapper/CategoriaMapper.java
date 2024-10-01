package com.pioriko.ms_restaurante.agregates.mapper;

import com.pioriko.ms_restaurante.agregates.dto.CategoriaDTO;
import com.pioriko.ms_restaurante.entities.CategoriaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoriaMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public CategoriaDTO mapToDto(CategoriaEntity entity){
        return modelMapper.map(entity, CategoriaDTO.class);
    }

    public CategoriaEntity mapToEntity(CategoriaDTO categoriaDTO){
        return modelMapper.map(categoriaDTO, CategoriaEntity.class);
    }

}
