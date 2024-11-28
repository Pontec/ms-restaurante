package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.agregates.dto.PedidoDTO;
import com.pioriko.ms_restaurante.agregates.dto.PedidoResponseDTO;

import java.util.List;

public interface PedidoService {

    PedidoDTO savePedido(PedidoDTO pedidoDTO);
    List<PedidoResponseDTO> findAllPedidos();
    PedidoResponseDTO findPedidoById(Integer id);
    void deletePedidoById(Integer id);
    PedidoDTO updatePedido(Integer id, PedidoDTO pedidoDTO);
    PedidoDTO updateEstadoPedido(Integer id, PedidoDTO pedidoDTO);
}
