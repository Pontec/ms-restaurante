package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.dto.PedidoDTO;
import com.pioriko.ms_restaurante.agregates.mapper.PedidoMapper;
import com.pioriko.ms_restaurante.dao.ClienteRepository;
import com.pioriko.ms_restaurante.dao.PedidoRepository;
import com.pioriko.ms_restaurante.entities.PedidoEntity;
import com.pioriko.ms_restaurante.service.PedidoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoMapper pedidoMapper;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public PedidoDTO savePedido(PedidoDTO pedidoDTO) {

        // Verificar que el cliente existe
        if (!clienteRepository.existsById(pedidoDTO.getIdCliente())) {
            throw new EntityNotFoundException("Cliente no encontrado con id: " + pedidoDTO.getIdCliente());
        }

        // Convertir PedidoDTO a PedidoEntity
        PedidoEntity pedidoEntity = pedidoMapper.mapToEntity(pedidoDTO);

        // Guardar PedidoEntity en la base de datos
        PedidoEntity pedidoEntitySaved = pedidoRepository.save(pedidoEntity);

        // Convertir PedidoEntity a PedidoDTO
        PedidoDTO pedidoDTOSaved = pedidoMapper.mapToDto(pedidoEntitySaved);

        return pedidoDTOSaved;
    }

    @Override
    public List<PedidoDTO> findAllPedidos() {
        List<PedidoEntity> pedidoEntity = pedidoRepository.findAll();
        return pedidoEntity.stream().map(pedidoMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PedidoDTO findPedidoById(Integer id) {
        PedidoEntity pedidoEntity = pedidoRepository.findById(id).orElse(null);
        return pedidoEntity != null ? pedidoMapper.mapToDto(pedidoEntity) : null;
    }

    @Override
    public void deletePedidoById(Integer id) {
        pedidoRepository.deleteById(id);

    }

    @Override
    public PedidoDTO updatePedido(Integer id, PedidoDTO pedidoDTO) {
        PedidoEntity pedido = pedidoRepository.findById(id).orElse(null);

        if(pedido != null) {
            pedido.setFechaPedido(pedidoDTO.getFechaPedido());
            //pedido.setHoraPedido(pedidoDTO.getHoraPedido());
            pedido.setTotalPedido(pedidoDTO.getTotalPedido());
            pedido.setEstado(pedidoDTO.getEstado());
            pedido.setObservaciones(pedidoDTO.getObservaciones());
            PedidoEntity pedidoUpdated = pedidoRepository.save(pedido);
            return pedidoMapper.mapToDto(pedidoUpdated);
        }

        return null;
    }
}
