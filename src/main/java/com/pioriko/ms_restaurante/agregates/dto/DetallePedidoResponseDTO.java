package com.pioriko.ms_restaurante.agregates.dto;

import lombok.Data;

@Data
public class DetallePedidoResponseDTO {
    private Integer idDetallePedido;
    private Integer cantidad;
    private Double precio;
    private ProductoDTO producto;
    private ComboDTO combo;
}
