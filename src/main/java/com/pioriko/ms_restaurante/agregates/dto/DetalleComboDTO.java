package com.pioriko.ms_restaurante.agregates.dto;

import lombok.Data;

@Data
public class DetalleComboDTO {

    private Integer idDetalleCombo;
    private Integer idCombo;     // Referencia al combo
    private Integer idProducto;  // Referencia al producto
    private Integer cantidad;
}

