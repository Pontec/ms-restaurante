package com.pioriko.ms_restaurante.agregates.dto;

import com.pioriko.ms_restaurante.entities.enu.EstadoMesa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MesasDTO {
    private Integer id;
    private String numeroMesa;
    private int capacidad;
    private EstadoMesa estado;
}
