package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.dto.PedidoDTO;
import com.pioriko.ms_restaurante.agregates.mapper.PedidoMapper;
import com.pioriko.ms_restaurante.dao.ClienteRepository;
import com.pioriko.ms_restaurante.dao.EmpleadoRepository;
import com.pioriko.ms_restaurante.dao.MesasRepository;
import com.pioriko.ms_restaurante.dao.PedidoRepository;
import com.pioriko.ms_restaurante.entities.ClientesEntity;
import com.pioriko.ms_restaurante.entities.EmpleadosEntity;
import com.pioriko.ms_restaurante.entities.MesasEntity;
import com.pioriko.ms_restaurante.entities.PedidoEntity;
import com.pioriko.ms_restaurante.service.PedidoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final MesasRepository mesasRepository;

    @Override
    public PedidoDTO savePedido(PedidoDTO pedidoDTO) {

        PedidoEntity pedido = pedidoMapper.mapToPedidoEntity(pedidoDTO);
        // Buscar las entidades completas usando los IDs
        ClientesEntity cliente = clienteRepository.findById(pedidoDTO.getIdCliente())
                .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado"));
        EmpleadosEntity empleado = empleadoRepository.findById(pedidoDTO.getIdEmpleado())
                .orElseThrow(() -> new NoSuchElementException("Empleado no encontrado"));
        MesasEntity mesa = mesasRepository.findById(pedidoDTO.getIdMesa())
                .orElseThrow(() -> new NoSuchElementException("Mesa no encontrada"));

        // Asignar las entidades completas a la reserva
        pedido.setCliente(cliente);
        pedido.setEmpleados(empleado);
        pedido.setMesa(mesa);

        PedidoEntity pedidoEntity = pedidoRepository.save(pedido);
        // Guardar el pedido
        return pedidoMapper.mapToPedidoDto(pedidoEntity);
    }

    @Override
    public List<PedidoDTO> findAllPedidos() {
        List<PedidoEntity> pedidoEntity = pedidoRepository.findAll();
        //System.out.println("lista de pedidos: " + pedidoEntity);
        return pedidoEntity.stream().map(pedidoMapper::mapToPedidoDto).collect(Collectors.toList());
    }

    @Override
    public PedidoDTO findPedidoById(Integer id) {
        PedidoEntity pedidoEntity = pedidoRepository.findById(id).orElse(null);
        return pedidoEntity != null ? pedidoMapper.mapToPedidoDto(pedidoEntity) : null;
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
            //pedido.setEstado(pedidoDTO.getEstado());
            pedido.setObservaciones(pedidoDTO.getObservaciones());
            PedidoEntity pedidoUpdated = pedidoRepository.save(pedido);
            return pedidoMapper.mapToPedidoDto(pedidoUpdated);
        }

        return null;
    }
}
