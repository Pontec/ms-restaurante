package com.pioriko.ms_restaurante.agregates.dto;

import lombok.Data;

@Data
public class ClienteDTO {

    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String dni;
    private String correo;
    private String telefono;
    private String direccion;
}
