package com.pioriko.ms_restaurante.agregates.mapper;

import com.pioriko.ms_restaurante.agregates.dto.DetalleComboDTO;
import com.pioriko.ms_restaurante.entities.DetalleComboEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
public interface DetalleComboMapper {

    @Mappings({
            @Mapping(source = "idDetalleCombo", target = "idDetCombo"),
            @Mapping(source = "combo.idCombo", target = "idCombo"),
            @Mapping(source = "producto.id", target = "idProducto"),
            @Mapping(source = "cantidad", target = "cantidad")
    })
    DetalleComboDTO maptoDetalleComboDTO(DetalleComboEntity entity);

    @InheritInverseConfiguration
    DetalleComboEntity maptoDetalleComboEntity(DetalleComboDTO dto);
}
