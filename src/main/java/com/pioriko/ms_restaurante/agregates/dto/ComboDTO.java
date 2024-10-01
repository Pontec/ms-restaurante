package com.pioriko.ms_restaurante.agregates.dto;

import lombok.Data;

@Data
public class ComboDTO {

    private Integer idCombo;
    private String nombre;
    private Double precio;
    private String descripcion;
    private String foto;
    private String estado;
}