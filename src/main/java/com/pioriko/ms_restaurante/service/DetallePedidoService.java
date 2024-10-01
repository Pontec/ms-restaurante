package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.agregates.dto.DetallePedidoDTO;

import java.util.List;

public interface DetallePedidoService {
    DetallePedidoDTO saveDetallePedido(DetallePedidoDTO detallePedidoDTO);
    List<DetallePedidoDTO> getAllDetallePedidos();
    DetallePedidoDTO getDetallePedidoById(Integer id);
    void deleteDetallePedidoById(Integer id);
    DetallePedidoDTO updateDetallePedido(Integer id, DetallePedidoDTO detallePedidoDTO);
}
