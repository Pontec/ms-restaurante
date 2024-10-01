package com.pioriko.ms_restaurante.agregates.mapper;

import com.pioriko.ms_restaurante.agregates.dto.MesasDTO;
import com.pioriko.ms_restaurante.entities.MesasEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MesasMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public MesasDTO mapToDto(MesasEntity entity){
        return modelMapper.map(entity, MesasDTO.class);
    }

    public MesasEntity mapToEntity(MesasDTO mesasDTO){
        return modelMapper.map(mesasDTO, MesasEntity.class);
    }
}
