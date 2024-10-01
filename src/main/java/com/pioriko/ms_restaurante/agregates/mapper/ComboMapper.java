package com.pioriko.ms_restaurante.agregates.mapper;

import com.pioriko.ms_restaurante.agregates.dto.ComboDTO;
import com.pioriko.ms_restaurante.entities.CombosEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ComboMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public ComboDTO mapToDto(CombosEntity entity){
        return modelMapper.map(entity, ComboDTO.class);
    }

    public CombosEntity mapToEntity(ComboDTO comboDTO){
        return modelMapper.map(comboDTO, CombosEntity.class);
    }
}
