package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.dto.DetallePedidoDTO;
import com.pioriko.ms_restaurante.agregates.response.ResponseBase;
import com.pioriko.ms_restaurante.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/detalle-pedidos")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearDetallePedido(@RequestBody DetallePedidoDTO detallePedidoDTO) {
        DetallePedidoDTO detallePedido = detallePedidoService.saveDetallePedido(detallePedidoDTO);
        ResponseBase responseBase = new ResponseBase(201, "Detalle de pedido creado correctamente", Optional.of(detallePedido));
        return new ResponseEntity(responseBase, HttpStatus.ACCEPTED);
    }

    @GetMapping("/todos")
    public ResponseEntity<?> listarDetallePedidos() {
        return ResponseEntity.ok(detallePedidoService.getAllDetallePedidos());
    }

    @PostMapping("/buscar/{id}")
    public ResponseEntity<?> buscarDetallePedido(@PathVariable Integer id) {
        return ResponseEntity.ok(detallePedidoService.getDetallePedidoById(id));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarDetallePedido(@PathVariable Integer id) {
        detallePedidoService.deleteDetallePedidoById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarDetallePedido(@PathVariable Integer id, @RequestBody DetallePedidoDTO detallePedidoDTO) {
        return ResponseEntity.ok(detallePedidoService.updateDetallePedido(id, detallePedidoDTO));
    }
}
