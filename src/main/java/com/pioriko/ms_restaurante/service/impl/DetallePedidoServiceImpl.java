package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.dto.DetallePedidoDTO;
import com.pioriko.ms_restaurante.agregates.mapper.DetallePedidoMapper;
import com.pioriko.ms_restaurante.dao.DetallePedidoRepository;
import com.pioriko.ms_restaurante.entities.DetallePedidoEntity;
import com.pioriko.ms_restaurante.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;
    @Autowired
    private DetallePedidoMapper detallePedidoMapper;



    @Override
    public DetallePedidoDTO saveDetallePedido(DetallePedidoDTO detallePedidoDTO) {
        // Convertir DetallePedidoDTO a DetallePedidoEntity
        DetallePedidoEntity detallePedidoEntity = detallePedidoMapper.toDetallePedidoEntity(detallePedidoDTO);
        // Guardar DetallePedidoEntity en la base de datos
        return detallePedidoMapper.toDetallePedidoDTO(detallePedidoRepository.save(detallePedidoEntity));
    }

    @Override
    public List<DetallePedidoDTO> getAllDetallePedidos() {
        return detallePedidoRepository.findAll().stream().map(detallePedidoMapper::toDetallePedidoDTO).collect(Collectors.toList());
    }

    @Override
    public DetallePedidoDTO getDetallePedidoById(Integer id) {
        return detallePedidoRepository.findById(id).map(detallePedidoMapper::toDetallePedidoDTO).orElse(null);
    }

    @Override
    public void deleteDetallePedidoById(Integer id) {
        detallePedidoRepository.deleteById(id);
    }

    @Override
    public DetallePedidoDTO updateDetallePedido(Integer id, DetallePedidoDTO detallePedidoDTO) {
        DetallePedidoEntity detallePedidoEntity = detallePedidoRepository.findById(id).orElse(null);
        if (detallePedidoEntity != null) {
            detallePedidoEntity.setCantidad(detallePedidoDTO.getCantidad());
            detallePedidoEntity.setPrecio(detallePedidoDTO.getPrecio());
            return detallePedidoMapper.toDetallePedidoDTO(detallePedidoRepository.save(detallePedidoEntity));
        }
        return null;
    }
}
