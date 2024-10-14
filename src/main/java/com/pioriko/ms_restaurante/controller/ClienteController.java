package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.dto.ClienteDTO;
import com.pioriko.ms_restaurante.agregates.response.ResponseBase;
import com.pioriko.ms_restaurante.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/crear")
    public ResponseEntity<ResponseBase<ClienteDTO>> saveCliente(@RequestBody ClienteDTO cliente) {
        ClienteDTO nuevoCliente = clienteService.crearCliente(cliente);
        ResponseBase response = new ResponseBase(200, "Cliente creado correctamente", Optional.of(nuevoCliente));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<ResponseBase<List<ClienteDTO>>> listarClientes() {
        List<ClienteDTO> clientes = clienteService.listarClientes();
        ResponseBase response = new ResponseBase(200, "Lista de clientes", Optional.of(clientes));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<ClienteDTO>> obtenerCliente(@PathVariable Integer id) {
        ClienteDTO cliente = clienteService.obtenerClientePorId(id);
        ResponseBase response = new ResponseBase(200, "Cliente encontrado", Optional.of(cliente));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseBase<ClienteDTO>> actualizarCliente(@PathVariable Integer id, @RequestBody ClienteDTO clienteDto) {
        ClienteDTO clienteActualizado = clienteService.actualizarCliente(id, clienteDto);
        ResponseBase response = new ResponseBase(200,"Cliente actulizado correctamente", Optional.of(clienteActualizado));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}

