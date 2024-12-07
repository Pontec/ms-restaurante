package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.dto.ComboDTO;
import com.pioriko.ms_restaurante.agregates.mapper.ComboMapper;
import com.pioriko.ms_restaurante.dao.ComboRepository;
import com.pioriko.ms_restaurante.entities.CombosEntity;
import com.pioriko.ms_restaurante.service.ComboService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComboServiceImpl implements ComboService {

    private final ComboRepository comboRepository;
    private final ComboMapper comboMapper;

    @Override
    public ComboDTO saveCombo(ComboDTO comboDTO) {
        CombosEntity combo = comboMapper.maptoComboEntity(comboDTO);
        CombosEntity saveCombo = comboRepository.save(combo);
        return comboMapper.maptoComboDto(saveCombo);
    }

    @Override
    public List<ComboDTO> findAllCombo() {
        List<CombosEntity> combos = comboRepository.findAll();
        return combos.stream().map(comboMapper::maptoComboDto).collect(Collectors.toList());
    }

    @Override
    public ComboDTO findComboById(Integer id) {
        CombosEntity combo = comboRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Combo no encontrado"));
        return comboMapper.maptoComboDto(combo);
    }

    @Override
    public void deleteComboById(Integer id) {

    }

    @Override
    public ComboDTO updateCombo(Integer id, ComboDTO comboDTO) {
        return null;
    }
}
