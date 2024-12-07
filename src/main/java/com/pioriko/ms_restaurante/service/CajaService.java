package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.entities.CajaEntity;

import java.util.List;

public interface CajaService {
    CajaEntity abrirCaja(double montoInicial, Long idEmpleado);
    CajaEntity cerrarCaja(double montoFinal);
    CajaEntity getCajaAbierta();
    List<CajaEntity> getHistorialCajas();
    Double obtenerTotalVentasDesdeApertura();


}
