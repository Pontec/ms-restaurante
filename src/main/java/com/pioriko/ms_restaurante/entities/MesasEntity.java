package com.pioriko.ms_restaurante.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mesas")
@Entity
public class MesasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mesa")
    private Integer id;
    private String numeroMesa;
    private int capacidad;
    @Enumerated(EnumType.STRING)
    private EstadoMesa estado;

    public enum EstadoMesa {
        DISPONIBLE,
        OCUPADA,
        RESERVADA
    }
}
