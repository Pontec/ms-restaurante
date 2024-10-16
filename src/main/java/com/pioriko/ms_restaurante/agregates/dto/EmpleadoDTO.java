package com.pioriko.ms_restaurante.agregates.dto;

import lombok.Data;

@Data
public class EmpleadoDTO {
    private Long idEmpleado;
    private String nombres;
    private String apellidos;
    private String correo;
    private String password;
    private String numDoc;
    private String direccion;
    private String telefono;
}
