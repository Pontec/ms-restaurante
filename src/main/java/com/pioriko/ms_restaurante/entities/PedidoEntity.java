package com.pioriko.ms_restaurante.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "pedidos")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;
    @Column(name = "fecha_pedido")
    private LocalDate fechaPedido;
    @Column(name = "hora_pedido")
    private LocalTime horaPedido;
    @Column(name = "total_pedido")
    private Double totalPedido;
    private EstadoPedido estado;
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClientesEntity cliente;

    @ManyToOne
    @JoinColumn(name = "id_mesa")
    private MesasEntity mesa;

    public enum EstadoPedido {
        PENDIENTE,
        EN_PREPARACION,
        ENTREGADO,
        CANCELADO
    }
}
