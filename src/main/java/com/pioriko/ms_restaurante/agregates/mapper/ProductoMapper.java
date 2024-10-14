package com.pioriko.ms_restaurante.agregates.mapper;

import com.pioriko.ms_restaurante.agregates.dto.ProductoDTO;
import com.pioriko.ms_restaurante.entities.ProductoEntity;
import com.pioriko.ms_restaurante.entities.enu.EstadoProducto;
import org.mapstruct.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mappings({
            @Mapping(source="id", target = "id"),
            @Mapping(source="nombre", target="nombre" ),
            @Mapping(source="precio", target="precio"),
            @Mapping(source="descripcion", target="descripcion"),
            @Mapping(source="imagen", target="imagen"),
            @Mapping(source="estado", target="estado"),
            @Mapping(source="porcion", target="porcion"),
            @Mapping(source="stock", target="stock"),
            @Mapping(source = "litros", target = "litros"),
            @Mapping(source="categoria.idCategoria", target = "idCategoria")
    })
    ProductoDTO mapToProductoDTO(ProductoEntity productoEntity);

    @InheritInverseConfiguration
    @Mapping(target = "categoria", ignore = true) // Ignorar categoría al mapear de vuelta
    ProductoEntity mapToProductoEntity(ProductoDTO productoDTO);

}
