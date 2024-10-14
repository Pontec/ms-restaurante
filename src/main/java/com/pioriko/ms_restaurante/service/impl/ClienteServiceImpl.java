package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.clients.ClientReniec;
import com.pioriko.ms_restaurante.agregates.dto.ClienteDTO;
import com.pioriko.ms_restaurante.agregates.mapper.ClienteMapper;
import com.pioriko.ms_restaurante.agregates.response.ResponseReniec;
import com.pioriko.ms_restaurante.dao.ClienteRepository;
import com.pioriko.ms_restaurante.entities.ClientesEntity;
import com.pioriko.ms_restaurante.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final ClientReniec clientReniec;

    //Se recuera el token que estan esta en properties
    @Value("${token.api}")
    private String tokenApi;

    @Override
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        ClientesEntity cliente = clienteMapper.mapToClientesEntity(clienteDTO);
        ClientesEntity exist = clienteRepository.findByDni(cliente.getDni());
        if (exist != null){
            return clienteMapper.mapToClienteDTO(exist);
        }
        ClientesEntity clienteReniec = getClienteReniec(cliente);
        return clienteMapper.mapToClienteDTO(clienteRepository.save(clienteReniec));
    }

    private ClientesEntity getClienteReniec(ClientesEntity clienteEntity) {
        ResponseReniec responseReniec = getExecutionReniec(clienteEntity.getDni());
        ClientesEntity clienteReniec = new ClientesEntity();
        if(responseReniec != null){
            clienteReniec.setNombre(responseReniec.getNombres());
            clienteReniec.setApellido(responseReniec.getApellidoPaterno() + " " + responseReniec.getApellidoMaterno());
            clienteReniec.setDni(responseReniec.getNumeroDocumento());
            return clienteReniec;
        }
        return null;
    }

    @Override
    public ClienteDTO obtenerClientePorId(Integer idCliente) {
        ClientesEntity cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return clienteMapper.mapToClienteDTO(cliente);
    }

    @Override
    public List<ClienteDTO> listarClientes() {
        List<ClientesEntity> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(clienteMapper::mapToClienteDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO actualizarCliente(Integer idCliente, ClienteDTO clienteDTO) {
        ClientesEntity cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        if( cliente != null){
            cliente.setNombre(clienteDTO.getNombre());
            cliente.setApellido(clienteDTO.getApellido());
            cliente.setDni(clienteDTO.getDni());
            cliente.setCorreo(clienteDTO.getCorreo());
            cliente.setDireccion(clienteDTO.getDireccion());
            cliente.setTelefono(clienteDTO.getTelefono());
            return clienteMapper.mapToClienteDTO(clienteRepository.save(cliente));
        }
        return null;
    }

    //Metodos de apoyo siempre privado
    private ResponseReniec getExecutionReniec(String dni){
        String auth = "Bearer "+tokenApi;
        ResponseReniec reniec = clientReniec.getInfoReniec(dni,auth);
        return reniec;
    }


}
