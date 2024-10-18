package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.agregates.dto.ComboDTO;

import java.util.List;

public interface ComboService {
    ComboDTO saveCombo(ComboDTO comboDTO);
    List<ComboDTO> findAllCombo();
    ComboDTO findComboById(Integer id);
    void deleteComboById(Integer id);
    ComboDTO updateCombo(Integer id, ComboDTO comboDTO);
}
