package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.request.SignInRequest;
import com.pioriko.ms_restaurante.agregates.request.SignUpRequest;
import com.pioriko.ms_restaurante.agregates.response.AuthenticationResponse;
import com.pioriko.ms_restaurante.dao.RolRepository;
import com.pioriko.ms_restaurante.dao.EmpleadoRepository;
import com.pioriko.ms_restaurante.entities.Rol;
import com.pioriko.ms_restaurante.entities.Role;
import com.pioriko.ms_restaurante.entities.EmpleadosEntity;
import com.pioriko.ms_restaurante.service.AuthenticationService;
import com.pioriko.ms_restaurante.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final EmpleadoRepository empleadoRepository;
    private final RolRepository rolRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public EmpleadosEntity signUpMoso(SignUpRequest signUpRequest) {
        EmpleadosEntity empleadosEntity = new EmpleadosEntity();
        empleadosEntity.setNombres(signUpRequest.getNombres());
        empleadosEntity.setApellidos(signUpRequest.getApellidos());
        empleadosEntity.setCorreo(signUpRequest.getEmail());
        empleadosEntity.setNumDoc(signUpRequest.getNumDoc());
        empleadosEntity.setTelefono(signUpRequest.getTelefono());
        empleadosEntity.setDireccion(signUpRequest.getDireccion());
        empleadosEntity.setFechaContratacion(LocalDate.now());
        Set<Rol> assginedRoles = new HashSet<>();
        Rol userRol = rolRepository.findByNombreRol(Role.MOZO.name()).orElseThrow(() -> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        assginedRoles.add(userRol);
        empleadosEntity.setRoles(assginedRoles);
        empleadosEntity.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return empleadoRepository.save(empleadosEntity);
    }

    @Override
    public EmpleadosEntity signUpAdmin(SignUpRequest signUpRequest) {
        EmpleadosEntity empleadosEntity = new EmpleadosEntity();
        empleadosEntity.setNombres(signUpRequest.getNombres());
        empleadosEntity.setApellidos(signUpRequest.getApellidos());
        empleadosEntity.setCorreo(signUpRequest.getEmail());
        empleadosEntity.setNumDoc(signUpRequest.getNumDoc());
        empleadosEntity.setTelefono(signUpRequest.getTelefono());
        empleadosEntity.setDireccion(signUpRequest.getDireccion());
        Set<Rol> assginedRoles = new HashSet<>();
        Rol userRol = rolRepository.findByNombreRol(Role.ADMIN.name()).orElseThrow(() -> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        Rol userRol2 = rolRepository.findByNombreRol(Role.COCINERO.name()).orElseThrow(()-> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        Rol userRol3 = rolRepository.findByNombreRol(Role.CAJA.name()).orElseThrow(()-> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        Rol userRol4 = rolRepository.findByNombreRol(Role.MOZO.name()).orElseThrow(()-> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        assginedRoles.add(userRol);
        assginedRoles.add(userRol2);
        assginedRoles.add(userRol3);
        assginedRoles.add(userRol4);
        empleadosEntity.setRoles(assginedRoles);
        empleadosEntity.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return empleadoRepository.save(empleadosEntity);
    }

    @Override
    public EmpleadosEntity signUpCajero(SignUpRequest signUpRequest) {
        EmpleadosEntity empleadosEntity = new EmpleadosEntity();
        empleadosEntity.setNombres(signUpRequest.getNombres());
        empleadosEntity.setApellidos(signUpRequest.getApellidos());
        empleadosEntity.setCorreo(signUpRequest.getEmail());
        empleadosEntity.setNumDoc(signUpRequest.getNumDoc());
        empleadosEntity.setTelefono(signUpRequest.getTelefono());
        empleadosEntity.setDireccion(signUpRequest.getDireccion());
        empleadosEntity.setFechaContratacion(LocalDate.now());
        Set<Rol> assginedRoles = new HashSet<>();
        Rol userRol = rolRepository.findByNombreRol(Role.CAJA.name()).orElseThrow(() -> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        assginedRoles.add(userRol);
        empleadosEntity.setRoles(assginedRoles);
        empleadosEntity.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return empleadoRepository.save(empleadosEntity);
    }

    @Override
    public EmpleadosEntity signUpCocinero(SignUpRequest signUpRequest) {
        EmpleadosEntity empleadosEntity = new EmpleadosEntity();
        empleadosEntity.setNombres(signUpRequest.getNombres());
        empleadosEntity.setApellidos(signUpRequest.getApellidos());
        empleadosEntity.setCorreo(signUpRequest.getEmail());
        empleadosEntity.setNumDoc(signUpRequest.getNumDoc());
        empleadosEntity.setTelefono(signUpRequest.getTelefono());
        empleadosEntity.setDireccion(signUpRequest.getDireccion());
        empleadosEntity.setFechaContratacion(LocalDate.now());
        Set<Rol> assginedRoles = new HashSet<>();
        Rol userRol = rolRepository.findByNombreRol(Role.COCINERO.name()).orElseThrow(()-> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        assginedRoles.add(userRol);
        empleadosEntity.setRoles(assginedRoles);
        empleadosEntity.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return empleadoRepository.save(empleadosEntity);
    }

    @Override
    public List<EmpleadosEntity> todos() {
        return empleadoRepository.findAll();
    }

    @Override
    public AuthenticationResponse signin(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(),signInRequest.getPassword()));
        var user = empleadoRepository.findByCorreo(signInRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email no valido"));

        var token = jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(token);
        return authenticationResponse;
    }
}
