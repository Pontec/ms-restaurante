package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.agregates.dto.MetodoPagoDTO;
import com.pioriko.ms_restaurante.entities.MetodoPagoEntity;

import java.util.List;

public interface MetodoPagoService {

    List<MetodoPagoDTO> findAllActive();
    MetodoPagoDTO save (MetodoPagoDTO metodoPagoDTO);
}
