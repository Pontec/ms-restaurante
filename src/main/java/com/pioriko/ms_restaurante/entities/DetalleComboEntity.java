package com.pioriko.ms_restaurante.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_combo")
public class DetalleComboEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_combo")
    private Integer idDetalleCombo;

    @ManyToOne
    @JoinColumn(name = "id_combo", nullable = false)
    private CombosEntity combo;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoEntity producto;

    @Column(nullable = false)
    private Integer cantidad;
}
