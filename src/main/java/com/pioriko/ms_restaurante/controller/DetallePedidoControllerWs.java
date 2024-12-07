package com.pioriko.ms_restaurante.controller;


import com.pioriko.ms_restaurante.agregates.dto.DetallePedidoDTO;
import com.pioriko.ms_restaurante.agregates.dto.PedidoResponseDTO;
import com.pioriko.ms_restaurante.agregates.dto.ProductoDTO;
import com.pioriko.ms_restaurante.agregates.request.PedidoDetalleRequest;
import com.pioriko.ms_restaurante.agregates.response.ResponseBase;
import com.pioriko.ms_restaurante.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class DetallePedidoControllerWs {
    @Autowired
    private DetallePedidoService detallePedidoService;


    @MessageMapping("/mozo/cocina")
    @SendTo("/topic/pedidos/cocina")
    public ResponseEntity<?> enviarPedidoACocina(@RequestBody PedidoResponseDTO pedidoResponseDTO) {
        //DetallePedidoDTO detallePedido = detallePedidoService.saveDetallePedido(detallePedidoDTO);
        //ResponseBase responseBase = new ResponseBase(201, "Detalle de pedido creado correctamente", Optional.of(detallePedido));
        return new ResponseEntity(pedidoResponseDTO, HttpStatus.ACCEPTED);
    }

    @MessageMapping("/cocina/mozo")
    @SendTo("/topic/pedidos/mozo")
    public ResponseEntity<?> actualizarPedidoDesdeCocina(@RequestBody PedidoResponseDTO pedidoResponseDTO) {
        //DetallePedidoDTO detallePedido = detallePedidoService.saveDetallePedido(detallePedidoDTO);
        //ResponseBase responseBase = new ResponseBase(201, "Detalle de pedido creado correctamente", Optional.of(detallePedido));
        return new ResponseEntity(pedidoResponseDTO, HttpStatus.ACCEPTED);
    }


    @MessageMapping("/mozo/caja")
    @SendTo("/topic/pedidos/caja")
    public ResponseEntity<?> enviarPedidoACaja(@RequestBody PedidoResponseDTO pedidoResponseDTO) {
        //DetallePedidoDTO detallePedido = detallePedidoService.saveDetallePedido(detallePedidoDTO);
        //ResponseBase responseBase = new ResponseBase(201, "Detalle de pedido creado correctamente", Optional.of(detallePedido));
        return new ResponseEntity(pedidoResponseDTO, HttpStatus.ACCEPTED);
    }

    @MessageMapping("/update")
    @SendTo("/topic/pedidos")
    public ResponseEntity<?> updatePedido(@RequestBody PedidoResponseDTO pedidoResponseDTO) {
        //DetallePedidoDTO detallePedido = detallePedidoService.saveDetallePedido(detallePedidoDTO);
        //ResponseBase responseBase = new ResponseBase(201, "Detalle de pedido creado correctamente", Optional.of(detallePedido));
        return new ResponseEntity(pedidoResponseDTO, HttpStatus.ACCEPTED);
    }




}
