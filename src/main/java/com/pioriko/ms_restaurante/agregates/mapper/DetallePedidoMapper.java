package com.pioriko.ms_restaurante.agregates.mapper;

import com.pioriko.ms_restaurante.agregates.dto.DetallePedidoDTO;
import com.pioriko.ms_restaurante.entities.DetallePedidoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DetallePedidoMapper {

    //Aqui se mapean los atributos de la entidad a los atributos del DTO
    DetallePedidoMapper INSTANCE = Mappers.getMapper(DetallePedidoMapper.class);

    @Mappings({
            @Mapping(source = "idDetallePedido", target = "idDetallePedido"),
            @Mapping(source = "cantidad", target = "cantidad"),
            @Mapping(source = "precio", target = "precio"),
            @Mapping(source = "pedido.idPedido", target = "idPedido"),
            @Mapping(source = "producto.id", target = "idProducto"),
            @Mapping(source = "combo.idCombo", target = "idCombo")
    })
    DetallePedidoDTO toDetallePedidoDTO(DetallePedidoEntity detallePedidoEntity);

    @InheritInverseConfiguration
    @Mappings({
            //@Mapping(target = "pedido", ignore = true),
            //@Mapping(target = "producto", ignore = true)
    })
    DetallePedidoEntity toDetallePedidoEntity(DetallePedidoDTO detallePedidoDTO);
}
