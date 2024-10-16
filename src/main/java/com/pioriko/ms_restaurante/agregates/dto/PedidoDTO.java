package com.pioriko.ms_restaurante.agregates.dto;

import com.pioriko.ms_restaurante.entities.enu.EstadoPedido;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class PedidoDTO {
    private Integer idPedido;
    private LocalDate fechaPedido;
    private LocalTime horaPedido;
    private EstadoPedido estado;
    private String observaciones;
    private Integer idCliente;
    private Long idEmpleado;
    private Integer idMesa;
    private List<DetallePedidoDTO> detallePedidos;

}