package com.pioriko.ms_restaurante.controller;


import com.pioriko.ms_restaurante.agregates.dto.DetallePedidoDTO;
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


    @MessageMapping("/save")
    @SendTo("/topic/pedidos")
    public ResponseEntity<?> crearDetallePedido(@RequestBody DetallePedidoDTO detallePedidoDTO) {
        DetallePedidoDTO detallePedido = detallePedidoService.saveDetallePedido(detallePedidoDTO);
        ResponseBase responseBase = new ResponseBase(201, "Detalle de pedido creado correctamente", Optional.of(detallePedido));
        return new ResponseEntity(responseBase, HttpStatus.ACCEPTED);
    }
}
