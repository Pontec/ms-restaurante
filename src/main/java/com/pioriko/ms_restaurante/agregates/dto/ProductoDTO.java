package com.pioriko.ms_restaurante.agregates.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDTO {
    private int id;
    private String nombre;
    private String descripcion;
    private int stock;
    private Double precio;
    private String imagen;
    private int estado;
    private Integer idCategoria;
}
