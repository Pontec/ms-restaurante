package com.pioriko.ms_restaurante.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "producto")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer id;
    private String nombre;
    private Double precio;
    private String descripcion;
    @Column(name = "foto")
    private String imagen;
    private int estado;
    private int stock;
    private int porcion;
    private String litros;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    @JsonBackReference
    private CategoriaEntity categoria;
}
