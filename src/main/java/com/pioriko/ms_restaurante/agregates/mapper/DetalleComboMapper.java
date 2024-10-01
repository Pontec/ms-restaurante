package com.pioriko.ms_restaurante.agregates.mapper;

import com.pioriko.ms_restaurante.agregates.dto.DetalleComboDTO;
import com.pioriko.ms_restaurante.entities.DetalleComboEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DetalleComboMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public DetalleComboDTO mapToDto(DetalleComboEntity entity){
        return modelMapper.map(entity, DetalleComboDTO.class);
    }

    public DetalleComboEntity mapToEntity(DetalleComboDTO detalleComboDTO){
        return modelMapper.map(detalleComboDTO, DetalleComboEntity.class);
    }
}
