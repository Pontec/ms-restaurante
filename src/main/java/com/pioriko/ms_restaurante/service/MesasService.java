package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.agregates.dto.MesasDTO;

import java.util.List;

public interface MesasService {
    MesasDTO save(MesasDTO mesasDto);
    MesasDTO findById(Integer id);
    List<MesasDTO> findAll();
    MesasDTO update(Integer id, MesasDTO mesasDTO);
    void delete(Integer id);
}
