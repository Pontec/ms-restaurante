package com.pioriko.ms_restaurante.agregates.mapper;

import com.pioriko.ms_restaurante.agregates.dto.ClienteDTO;
import com.pioriko.ms_restaurante.entities.ClientesEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mappings({
            @Mapping(source = "idCliente", target = "idCliente"),
            @Mapping(source = "dni", target = "dni"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "correo", target = "correo"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "direccion", target = "direccion")
    })
    ClienteDTO mapToClienteDTO (ClientesEntity cliente);
    
    @InheritInverseConfiguration
    ClientesEntity mapToClientesEntity (ClienteDTO clienteDTO);

}
