package com.pioriko.ms_restaurante.agregates.mapper;

import com.pioriko.ms_restaurante.agregates.dto.ComboDTO;
import com.pioriko.ms_restaurante.entities.CombosEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
public interface ComboMapper {

    @Mappings({
            @Mapping(source = "idCombo", target = "idCombo"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "precio", target = "precio"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "foto", target = "foto"),
            @Mapping(source = "estado", target = "estado")
    })
    ComboDTO maptoComboDto(CombosEntity combo);

    @InheritInverseConfiguration
    CombosEntity maptoComboEntity(ComboDTO combo);



}
