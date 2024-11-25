package com.pioriko.ms_restaurante.entities;

import com.pioriko.ms_restaurante.entities.enu.EstadoPedido;
import jakarta.persistence.*;
import lombok.*;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;
    @Column(name = "fecha_pedido", nullable = false)
    private LocalDate fechaPedido;
    @Column(name = "hora_pedido", nullable = false)
    private LocalTime horaPedido;
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;
    private String observaciones;

    @Column(name = "id_empleado")
    private Long idEmpleado;
    @Column(name = "id_mesa")
    private Integer idMesa;
    @Column(name = "id_cliente")
    private Integer idCliente;

    @ManyToOne
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
    private ClientesEntity cliente;

    @ManyToOne
    @JoinColumn(name = "id_empleado", insertable = false, updatable = false)
    private EmpleadosEntity empleados;

    @ManyToOne
    @JoinColumn(name = "id_mesa", insertable = false, updatable = false)
    private MesasEntity mesa;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<DetallePedidoEntity> listDetallePedidos;

}
