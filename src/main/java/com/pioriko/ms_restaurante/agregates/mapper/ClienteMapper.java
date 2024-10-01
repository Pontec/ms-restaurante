package com.pioriko.ms_restaurante.agregates.mapper;

import com.pioriko.ms_restaurante.agregates.dto.ClienteDTO;
import com.pioriko.ms_restaurante.entities.ClientesEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public ClienteDTO mapToDto(ClientesEntity entity){
        return modelMapper.map(entity, ClienteDTO.class);
    }

    public ClientesEntity mapToEntity(ClienteDTO clienteDTO){
        return modelMapper.map(clienteDTO, ClientesEntity.class);
    }
}
