package com.pioriko.ms_restaurante.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "combos")
public class CombosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCombo;
    private String nombre;
    private Double precio;
    private String descripcion;
    private String foto;
    @Enumerated(EnumType.STRING)
    private EstadoCombo estado;

    public enum EstadoCombo {
        DISPONIBLE,
        NO_DISPONIBLE
    }
}
