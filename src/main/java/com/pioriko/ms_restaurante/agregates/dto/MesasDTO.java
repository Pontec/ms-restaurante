package com.pioriko.ms_restaurante.agregates.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MesasDTO {
    private Integer id;
    private String numeroMesa;
    private int capacidad;
    private String estado;
}
