package com.pioriko.ms_restaurante.agregates.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetallePedidoDTO {

    private Integer idDetallePedido;
    private Integer cantidad;
    private Double precio;
    private Integer idPedido;
    private Integer idProducto;
    private Integer idCombo;
}