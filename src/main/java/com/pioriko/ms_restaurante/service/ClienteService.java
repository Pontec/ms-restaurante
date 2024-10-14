package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.agregates.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {

    ClienteDTO crearCliente( ClienteDTO clienteDTO);
    ClienteDTO obtenerClientePorId(Integer idCliente);
    List<ClienteDTO> listarClientes();
    ClienteDTO actualizarCliente(Integer idCliente, ClienteDTO clienteDTO);
}
