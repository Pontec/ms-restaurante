package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.dto.PedidoDTO;
import com.pioriko.ms_restaurante.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/crear")
    public ResponseEntity<?> savePedido(@RequestBody PedidoDTO pedidoDto) {
        PedidoDTO nuevoPedido = pedidoService.savePedido(pedidoDto);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<?> listarPedidos() {
        return new ResponseEntity<>(pedidoService.findAllPedidos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPedido(@PathVariable Integer id) {
        return new ResponseEntity<>(pedidoService.findPedidoById(id), HttpStatus.OK);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarPedido(@PathVariable Integer id) {
        pedidoService.deletePedidoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarPedido(@PathVariable Integer id, @RequestBody PedidoDTO pedidoDto) {
        PedidoDTO pedidoActualizado = pedidoService.updatePedido(id, pedidoDto);
        return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
    }

}
