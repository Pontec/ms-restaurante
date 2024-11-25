package com.pioriko.ms_restaurante.agregates.mapper;

import com.pioriko.ms_restaurante.agregates.dto.DetallePedidoDTO;
import com.pioriko.ms_restaurante.agregates.dto.DetallePedidoResponseDTO;
import com.pioriko.ms_restaurante.entities.DetallePedidoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetallePedidoMapper {


    @Mappings({
            @Mapping(source = "idDetallePedido", target = "idDetallePedido"),
            @Mapping(source = "cantidad", target = "cantidad"),
            @Mapping(source = "precio", target = "precio"),
            @Mapping(source = "pedido.idPedido", target = "idPedido"),
            @Mapping(source = "producto", target = "producto"),
            @Mapping(source = "combo.idCombo", target = "idCombo")
    })
    DetallePedidoDTO toDetallePedidoDTO(DetallePedidoEntity detallePedidoEntity);
    //sirve para mapear de una entidad a un dto para poder mostrar en la respuesta

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "pedido.idPedido", source = "idPedido"),
            @Mapping(target = "producto", source = "producto"),
            @Mapping(target = "combo.idCombo", source = "idCombo")
    })
    DetallePedidoEntity toDetallePedidoEntity(DetallePedidoDTO detallePedidoDTO);
    //sirve para mapear de un dto a una entidad para poder guardar en la base de datos


    @Mappings({
            @Mapping(source = "idDetallePedido", target = "idDetallePedido"),
            @Mapping(source = "cantidad", target = "cantidad"),
            @Mapping(source = "precio", target = "precio"),
            @Mapping(source = "producto", target = "producto"),
            @Mapping(source = "combo", target = "combo")
    })
    DetallePedidoResponseDTO toDetallePedidoResponseDTO(DetallePedidoEntity detallePedidoEntity);

}
