package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.dto.DetallePedidoDTO;
import com.pioriko.ms_restaurante.agregates.dto.DetallePedidoResponseDTO;
import com.pioriko.ms_restaurante.agregates.mapper.DetallePedidoMapper;
import com.pioriko.ms_restaurante.dao.ComboRepository;
import com.pioriko.ms_restaurante.dao.DetallePedidoRepository;
import com.pioriko.ms_restaurante.dao.PedidoRepository;
import com.pioriko.ms_restaurante.dao.ProductoRepository;
import com.pioriko.ms_restaurante.entities.CombosEntity;
import com.pioriko.ms_restaurante.entities.DetallePedidoEntity;
import com.pioriko.ms_restaurante.entities.PedidoEntity;
import com.pioriko.ms_restaurante.entities.ProductoEntity;
import com.pioriko.ms_restaurante.service.DetallePedidoService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final DetallePedidoMapper detallePedidoMapper;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final ComboRepository comboRepository;


    @Override
    public DetallePedidoDTO saveDetallePedido(DetallePedidoDTO detallePedidoDTO) {
        // Convertir DetallePedidoDTO a DetallePedidoEntity
        DetallePedidoEntity detallePedidoEntity = detallePedidoMapper.toDetallePedidoEntity(detallePedidoDTO);

        PedidoEntity pedido = pedidoRepository.findById(detallePedidoDTO.getIdPedido()).orElseThrow(()-> new NoSuchElementException("Pedido no encontrado"));
        ProductoEntity producto = productoRepository.findById(detallePedidoDTO.getIdProducto()).orElseThrow(()-> new NoSuchElementException("Producto no encontrado"));
        CombosEntity combos = comboRepository.findById(detallePedidoDTO.getIdCombo()).orElseThrow(()-> new NoSuchElementException("Combo no encontrado"));
        detallePedidoEntity.setPedido(pedido);
        detallePedidoEntity.setProducto(producto);
        detallePedidoEntity.setCombo(combos);
        // Guardar DetallePedidoEntity en la base de datos
        return detallePedidoMapper.toDetallePedidoDTO(detallePedidoRepository.save(detallePedidoEntity));
    }

    @Override
    public List<DetallePedidoResponseDTO> getAllDetallePedidos() {
        return detallePedidoRepository.findAll().stream().map(detallePedidoMapper::toDetallePedidoResponseDTO).collect(Collectors.toList());
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

    @Override
    public List<DetallePedidoDTO> saveAllDetallePedidos(List<DetallePedidoDTO> detallePedidoDTOs) {
        List<DetallePedidoEntity> entities = detallePedidoDTOs.stream()
                .map(detallePedidoMapper::toDetallePedidoEntity)
                .collect(Collectors.toList());
        List<DetallePedidoEntity> savedEntities = detallePedidoRepository.saveAll(entities);
        return savedEntities.stream()
                .map(detallePedidoMapper::toDetallePedidoDTO)
                .collect(Collectors.toList());
    }
}
