package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.dto.ClienteDTO;
import com.pioriko.ms_restaurante.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/crear")
    public ResponseEntity<?> saveCliente(@RequestBody ClienteDTO cliente) {
        ClienteDTO nuevoCliente = clienteService.crearCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<?> listarClientes() {
        return new ResponseEntity<>(clienteService.listarClientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCliente(@PathVariable Integer id) {
        return new ResponseEntity<>(clienteService.obtenerClientePorId(id), HttpStatus.OK);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Integer id) {
        clienteService.eliminarCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable Integer id, @RequestBody ClienteDTO clienteDto) {
        ClienteDTO clienteActualizado = clienteService.actualizarCliente(id, clienteDto);
        return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
    }



}

