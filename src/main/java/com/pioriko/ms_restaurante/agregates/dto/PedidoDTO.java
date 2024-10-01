package com.pioriko.ms_restaurante.agregates.dto;

import com.pioriko.ms_restaurante.entities.PedidoEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PedidoDTO {

    private Integer idPedido;
    private LocalDate fechaPedido;
    //private LocalTime horaPedido;
    private Double totalPedido;
    private String observaciones;
    private PedidoEntity.EstadoPedido estado;
    private Integer idCliente;  // Referencia al cliente que hizo el pedido
    private Integer idMesa;     // Referencia a la mesa asociada al pedido
}