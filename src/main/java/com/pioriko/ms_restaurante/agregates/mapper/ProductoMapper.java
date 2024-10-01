package com.pioriko.ms_restaurante.agregates.mapper;

import com.pioriko.ms_restaurante.agregates.dto.ProductoDTO;
import com.pioriko.ms_restaurante.entities.ProductoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductoMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public ProductoDTO mapToDto(ProductoEntity entity){
        return modelMapper.map(entity, ProductoDTO.class);
    }

    public ProductoEntity mapToEntity(ProductoDTO personaDTO){
        return modelMapper.map(personaDTO, ProductoEntity.class);
    }
}
