package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.dto.DetalleComboDTO;
import com.pioriko.ms_restaurante.agregates.mapper.DetalleComboMapper;
import com.pioriko.ms_restaurante.dao.DetalleComboRepository;
import com.pioriko.ms_restaurante.entities.DetalleComboEntity;
import com.pioriko.ms_restaurante.service.DetalleComboService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetalleComboPedidoImpl implements DetalleComboService {

    private final DetalleComboRepository detalleComboRepository;
    private final DetalleComboMapper detalleComboMapper;

    @Override
    public DetalleComboDTO save(DetalleComboDTO detalleComboDTO) {
        DetalleComboEntity detalleComboEntity = detalleComboMapper.maptoDetalleComboEntity(detalleComboDTO);
        detalleComboRepository.save(detalleComboEntity);
        return detalleComboMapper.maptoDetalleComboDTO(detalleComboEntity);
    }
}
