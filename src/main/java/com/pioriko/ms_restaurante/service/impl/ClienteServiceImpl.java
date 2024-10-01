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
        ClientesEntity cliente = clienteMapper.mapToEntity(clienteDTO);
        boolean existe = clienteRepository.existsByDni(cliente.getDni());
        if (existe){
            ClientesEntity existingCliente = clienteRepository.findByDni(cliente.getDni());
            return clienteMapper.mapToDto(existingCliente);
        }
        ClientesEntity clienteReniec = getClienteReniec(cliente);

        if (clienteReniec != null){
            return clienteMapper.mapToDto(clienteRepository.save(clienteReniec));
        }
        else {
            throw new RuntimeException("Error, la persona No existe en Reniec");
        }
    }
    private ClientesEntity getClienteReniec(ClientesEntity clienteEntity) {
        ResponseReniec responseReniec = getExecutionReniec(clienteEntity.getDni());
        ClientesEntity clienteReniec = new ClientesEntity();

        if(responseReniec != null){
            clienteReniec.setNombre(responseReniec.getNombres());
            clienteReniec.setApellido(responseReniec.getApellidoPaterno() + " " + responseReniec.getApellidoMaterno());
            clienteReniec.setCorreo(clienteEntity.getCorreo());
            clienteReniec.setTelefono(clienteEntity.getTelefono());
            clienteReniec.setDireccion(clienteEntity.getDireccion());
            clienteReniec.setDni(responseReniec.getNumeroDocumento());
            return clienteReniec;
        }
        return null;
    }

    @Override
    public ClienteDTO obtenerClientePorId(Integer idCliente) {
        ClientesEntity cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return clienteMapper.mapToDto(cliente);
    }

    @Override
    public List<ClienteDTO> listarClientes() {
        List<ClientesEntity> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(clienteMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO actualizarCliente(Integer idCliente, ClienteDTO clienteDTO) {
        ClientesEntity cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        clienteMapper.mapToEntity(clienteDTO);  // Mapeamos los cambios del DTO a la entidad

        ClientesEntity clienteActualizado = clienteRepository.save(cliente);
        return clienteMapper.mapToDto(clienteActualizado);
    }

    @Override
    public void eliminarCliente(Integer idCliente) {
        ClientesEntity cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        clienteRepository.delete(cliente);
    }

    //Metodos de apoyo siempre privado
    private ResponseReniec getExecutionReniec(String dni){
        String auth = "Bearer "+tokenApi;
        ResponseReniec reniec = clientReniec.getInfoReniec(dni,auth);
        return reniec;
    }


}
