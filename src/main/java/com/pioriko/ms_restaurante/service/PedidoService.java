package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.agregates.dto.PedidoDTO;

import java.util.List;

public interface PedidoService {

    PedidoDTO savePedido(PedidoDTO pedidoDTO);
    List<PedidoDTO> findAllPedidos();
    PedidoDTO findPedidoById(Integer id);
    void deletePedidoById(Integer id);
    PedidoDTO updatePedido(Integer id, PedidoDTO pedidoDTO);
}
