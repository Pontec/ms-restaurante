package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.dto.MetodoPagoDTO;
import com.pioriko.ms_restaurante.agregates.mapper.MetodoPagoMapper;
import com.pioriko.ms_restaurante.dao.MetodoPagoRepository;
import com.pioriko.ms_restaurante.service.MetodoPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetodoPagoServiceImpl implements MetodoPagoService {

    private final MetodoPagoRepository metodoPagoRepository;
    private final MetodoPagoMapper metodoPagoMapper;


    @Override
    public List<MetodoPagoDTO> findAllActive() {
        return metodoPagoMapper.mapToMetodoPagoDTOs(metodoPagoRepository.findByActivoTrue());
    }

    @Override
    public MetodoPagoDTO save(MetodoPagoDTO metodoPagoDTO) {
        return metodoPagoMapper.mapToMetodoPagoDTO(metodoPagoRepository
                .save(metodoPagoMapper.mapToMetodoPagoEntity(metodoPagoDTO)));
    }

}
