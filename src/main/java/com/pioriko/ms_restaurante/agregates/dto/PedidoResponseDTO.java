package com.pioriko.ms_restaurante.agregates.dto;

import com.pioriko.ms_restaurante.entities.enu.EstadoPedido;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class PedidoResponseDTO {
    private Integer idPedido;
    private LocalDate fechaPedido;
    private LocalTime horaPedido;
    private EstadoPedido estado;
    private String observaciones;
    private ClienteDTO cliente;
    private EmpleadoDTO empleado;
    private MesasDTO mesa;
    private List<DetallePedidoDTO> detallePedidos;
}
