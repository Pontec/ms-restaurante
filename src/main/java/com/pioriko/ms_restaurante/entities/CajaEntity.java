package com.pioriko.ms_restaurante.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "caja")
public class CajaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fecha_apertura", nullable = false)
    private LocalDateTime fechaApertura;
    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;
    @Column(name = "monto_inicial", nullable = false)
    private Double montoInicial;
    @Column(name = "monto_final")
    private Double montoFinal;
    @Column(name = "total_ventas")
    private Double totalVentas;
    @Column(name = "abierta", nullable = false)
    private Boolean abierta;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private EmpleadosEntity empleado;


}
