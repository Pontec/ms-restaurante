package com.pioriko.ms_restaurante.agregates.mapper;

import com.pioriko.ms_restaurante.agregates.dto.MetodoPagoDTO;
import com.pioriko.ms_restaurante.entities.MetodoPagoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MetodoPagoMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nombre", target = "nombre"),
    })
    MetodoPagoDTO mapToMetodoPagoDTO (MetodoPagoEntity metodoPagoEntity);
    List<MetodoPagoDTO> mapToMetodoPagoDTOs (List<MetodoPagoEntity> metodoPagoEntities);

    @InheritInverseConfiguration
    MetodoPagoEntity mapToMetodoPagoEntity (MetodoPagoDTO metodoPagoDTO);
}
