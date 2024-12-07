package com.pioriko.ms_restaurante.agregates.dto;

import lombok.Data;

@Data
public class ProductoReporteDTO {
    private String producto;
    private Integer cantidad;
    private Double monto_unitario;
    private Double monto_total;

    // Constructor
    public ProductoReporteDTO(String producto, Integer cantidad, 
                            Double monto_unitario, Double monto_total) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.monto_unitario = monto_unitario;
        this.monto_total = monto_total;
    }

}
