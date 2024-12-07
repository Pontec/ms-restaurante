package com.pioriko.ms_restaurante.agregates.mapper;

import com.pioriko.ms_restaurante.agregates.dto.DetallePedidoDTO;
import com.pioriko.ms_restaurante.agregates.dto.DetallePedidoResponseDTO;
import com.pioriko.ms_restaurante.entities.DetallePedidoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetallePedidoMapper {


    @Mapping(source = "idDetallePedido", target = "idDetallePedido")
    @Mapping(source = "cantidad", target = "cantidad")
    @Mapping(source = "precio", target = "precio")
    @Mapping(source = "pedido.idPedido", target = "idPedido")
    @Mapping(source = "producto.id", target = "idProducto")
    @Mapping(source = "producto", target = "producto")    
    @Mapping(target = "producto.idCategoria", ignore = true)
    @Mapping(source = "combo.idCombo", target = "idCombo")
    DetallePedidoDTO toDetallePedidoDTO(DetallePedidoEntity detallePedidoEntity);
    //sirve para mapear de una entidad a un dto para poder mostrar en la respuesta

    @InheritInverseConfiguration
    @Mapping(target = "pedido.idPedido", source = "idPedido")
    @Mapping(target = "producto", source = "producto")
    @Mapping(target = "producto.categoria.idCategoria", ignore = true)
    @Mapping(target = "combo.idCombo", source = "idCombo")
    DetallePedidoEntity toDetallePedidoEntity(DetallePedidoDTO detallePedidoDTO);
    //sirve para mapear de un dto a una entidad para poder guardar en la base de datos


    @Mapping(source = "idDetallePedido", target = "idDetallePedido")
    @Mapping(source = "cantidad", target = "cantidad")
    @Mapping(source = "precio", target = "precio")
    @Mapping(source = "producto", target = "producto")
    @Mapping(source = "combo", target = "combo")
    DetallePedidoResponseDTO toDetallePedidoResponseDTO(DetallePedidoEntity detallePedidoEntity);

}
