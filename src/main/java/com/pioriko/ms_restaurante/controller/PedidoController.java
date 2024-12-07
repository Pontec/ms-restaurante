package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.dto.PedidoDTO;
import com.pioriko.ms_restaurante.agregates.dto.PedidoResponseDTO;
import com.pioriko.ms_restaurante.agregates.response.ResponseBase;
import com.pioriko.ms_restaurante.entities.PedidoEntity;
import com.pioriko.ms_restaurante.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/crear")
    public ResponseEntity<?> savePedido(@RequestBody PedidoDTO pedidoDto) {
        PedidoDTO nuevoPedido = pedidoService.savePedido(pedidoDto);
        ResponseBase responseBase = new ResponseBase(201, "Pedido Creado Correctamente", Optional.of(nuevoPedido));
        return new ResponseEntity<>(responseBase, HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<ResponseBase<List<PedidoResponseDTO>>> listarPedidos() {
       List<PedidoResponseDTO> lista = pedidoService.findAllPedidos();
        ResponseBase responseBase = new ResponseBase(200, "Pedidos Listado Correctamente", Optional.of(lista));
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<PedidoResponseDTO>> obtenerPedido(@PathVariable Integer id) {
        ResponseBase responseBase = new ResponseBase
                (200, "Pedido Encontrado", Optional.of(pedidoService.findPedidoById(id)));
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
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

    @PutMapping("/actualizar/estado/{id}")
    public ResponseEntity<?> actualizarEstadoPedido(@PathVariable Integer id, @RequestBody PedidoDTO pedidoDto) {
        PedidoResponseDTO pedidoActualizado = pedidoService.updateEstadoPedido(id, pedidoDto);
        return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
    }

    @GetMapping("/total-pagado-hoy")
    public ResponseEntity<ResponseBase<Double>> getTotalPagadoHoy(){
        Double totalPagado = pedidoService.getTotalPagadoHoy();
        if(totalPagado == null) {
            ResponseBase responseBase = new ResponseBase(204, "No hay ventas hoy", Optional.empty());
            return new ResponseEntity<>(responseBase, HttpStatus.NO_CONTENT);
        }
        ResponseBase responseBase = new ResponseBase(200, "Total Pagado Hoy", Optional.of(totalPagado));
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }



}
