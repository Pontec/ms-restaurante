package com.pioriko.ms_restaurante.agregates.mapper;

import com.pioriko.ms_restaurante.agregates.dto.MesasDTO;
import com.pioriko.ms_restaurante.entities.MesasEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
public interface MesasMapper {

    @Mappings({
            @Mapping(source = "idMesa", target = "id"),
            @Mapping(source = "numeroMesa", target = "numeroMesa"),
            @Mapping(source = "capacidad", target = "capacidad"),
            @Mapping(source = "estado", target = "estado")
    })
    MesasDTO mapToMesasDTO (MesasEntity mesasEntity);

    @InheritInverseConfiguration
    MesasEntity mapToMesasEntity (MesasDTO mesasDTO);
}
