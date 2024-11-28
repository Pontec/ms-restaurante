package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.clients.ClientReniec;
import com.pioriko.ms_restaurante.agregates.dto.EmpleadoDTO;
import com.pioriko.ms_restaurante.agregates.request.SignUpRequest;
import com.pioriko.ms_restaurante.agregates.response.ResponseReniec;
import com.pioriko.ms_restaurante.dao.EmpleadoRepository;
import com.pioriko.ms_restaurante.entities.ClientesEntity;
import com.pioriko.ms_restaurante.entities.EmpleadosEntity;
import com.pioriko.ms_restaurante.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    private final ClientReniec clientReniec;
    private final EmpleadoRepository empleadoRepository;

    @Override
    public UserDetailsService userDetailService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return empleadoRepository.findByCorreo(username).orElseThrow(
                        () -> new UsernameNotFoundException("Usuario no encontrado"));
            }
        };
    }

    @Override
    public List<EmpleadosEntity> getUsuarios() {
        return empleadoRepository.findAll();
    }

    //Se recuera el token que estan esta en properties
    @Value("${token.api}")
    private String tokenApi;

    @Override
    public EmpleadoDTO getEmpleadoReniec(String dni) {

        ResponseReniec responseReniec = getExecutionReniec(dni);
        EmpleadoDTO empleado = new EmpleadoDTO();
        if(responseReniec != null){
            empleado.setNombres(responseReniec.getNombres());
            empleado.setApellidos(responseReniec.getApellidoPaterno() + " " + responseReniec.getApellidoMaterno());
            empleado.setNumDoc(responseReniec.getNumeroDocumento());
            return empleado;
        }
        return null;
    }

    @Override
    public EmpleadosEntity updateEmpleado(Long id, SignUpRequest signUpRequest) {
        EmpleadosEntity empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Empleado no encontrado"));

        empleado.setNombres(signUpRequest.getNombres());
        empleado.setApellidos(signUpRequest.getApellidos());
        empleado.setCorreo(signUpRequest.getEmail());
        empleado.setNumDoc(signUpRequest.getNumDoc());
        empleado.setDireccion(signUpRequest.getDireccion());
        empleado.setTelefono(signUpRequest.getTelefono());
        empleado.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));

        return empleadoRepository.save(empleado);
    }


    //Metodos de apoyo siempre privado
    private ResponseReniec getExecutionReniec(String dni){
        String auth = "Bearer "+tokenApi;
        ResponseReniec reniec = clientReniec.getInfoReniec(dni,auth);
        return reniec;
    }
}
