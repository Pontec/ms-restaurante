package com.pioriko.ms_restaurante.entities;

import com.pioriko.ms_restaurante.entities.enu.EstadoCombo;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "combos")
public class CombosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_combo")
    private Integer idCombo;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private Double precio;
    private String descripcion;
    private String foto;
    @Enumerated(EnumType.STRING)
    private EstadoCombo estado;

}
