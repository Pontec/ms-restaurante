package com.pioriko.ms_restaurante.agregates.dto;

import com.pioriko.ms_restaurante.entities.enu.EstadoProducto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDTO {
    private int id;
    private String nombre;
    private Double precio;
    private String descripcion;
    private String imagen;
    private EstadoProducto estado;
    private String porcion;
    private int stock;
    private String litros;
    private Integer idCategoria;
}
