package com.pioriko.ms_restaurante.agregates.dto;

import lombok.Data;

@Data
public class DetalleComboDTO {

    private Integer idDetCombo;
    private Integer idCombo;
    private Integer idProducto;
    private Integer cantidad;
}

