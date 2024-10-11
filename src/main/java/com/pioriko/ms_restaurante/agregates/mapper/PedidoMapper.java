package com.pioriko.ms_restaurante.agregates.mapper;

import com.pioriko.ms_restaurante.agregates.dto.PedidoDTO;
import com.pioriko.ms_restaurante.entities.PedidoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PedidoMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public PedidoDTO mapToDto(PedidoEntity entity){
        return modelMapper.map(entity, PedidoDTO.class);
    }

    public PedidoEntity mapToEntity(PedidoDTO pedidoDTO){
        return modelMapper.map(pedidoDTO, PedidoEntity.class);
    }
}
