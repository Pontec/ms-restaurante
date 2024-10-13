package com.pioriko.ms_restaurante.agregates.mapper;

import com.pioriko.ms_restaurante.agregates.dto.CategoriaDTO;
import com.pioriko.ms_restaurante.entities.CategoriaEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

   CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

   @Mappings({
           @Mapping(source = "idCategoria", target = "id"),
           @Mapping(source = "nombre", target = "nombre")
   })
   CategoriaDTO mapToCategoriaDTO(CategoriaEntity categoriaEntity);

   @InheritInverseConfiguration
    CategoriaEntity mapToCategoriaEntity(CategoriaDTO categoriaDTO);


    /*
    private static final ModelMapper modelMapper = new ModelMapper();

    public CategoriaDTO mapToDto(CategoriaEntity entity){
        return modelMapper.map(entity, CategoriaDTO.class);
    }

    public CategoriaEntity mapToEntity(CategoriaDTO categoriaDTO){
        return modelMapper.map(categoriaDTO, CategoriaEntity.class);
    }
    */
}
