package com.pioriko.ms_restaurante.agregates.mapper;

import com.pioriko.ms_restaurante.agregates.dto.PedidoDTO;
import com.pioriko.ms_restaurante.entities.PedidoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {DetallePedidoMapper.class})
public interface PedidoMapper {

    @Mappings({
            @Mapping(source = "idPedido", target = "idPedido"),
            @Mapping(source = "fechaPedido", target = "fechaPedido"),
            @Mapping(source = "horaPedido", target = "horaPedido"),
            @Mapping(source = "estado", target = "estado"),
            @Mapping(source = "observaciones", target = "observaciones"),
            @Mapping(source = "cliente.idCliente", target = "idCliente"),
            @Mapping(source = "idEmpleado", target = "idEmpleado"),
            @Mapping(source = "idMesa", target = "idMesa"),
            @Mapping(source = "listDetallePedidos", target = "detallePedidos")
    })
    PedidoDTO mapToPedidoDto(PedidoEntity entity);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "cliente", ignore = true),
            @Mapping(target = "empleados", ignore = true),
            @Mapping(target = "mesa", ignore = true)
    })
    PedidoEntity mapToPedidoEntity(PedidoDTO dto);
}
