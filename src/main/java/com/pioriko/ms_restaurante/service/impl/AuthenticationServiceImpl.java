package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.request.SignInRequest;
import com.pioriko.ms_restaurante.agregates.request.SignUpRequest;
import com.pioriko.ms_restaurante.agregates.response.AuthenticationResponse;
import com.pioriko.ms_restaurante.dao.RolRepository;
import com.pioriko.ms_restaurante.dao.EmpleadoRepository;
import com.pioriko.ms_restaurante.entities.Rol;
import com.pioriko.ms_restaurante.entities.Role;
import com.pioriko.ms_restaurante.entities.Empleados;
import com.pioriko.ms_restaurante.service.AuthenticationService;
import com.pioriko.ms_restaurante.service.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public Empleados signUpMoso(SignUpRequest signUpRequest) {
        Empleados empleados = new Empleados();
        empleados.setNombres(signUpRequest.getNombres());
        empleados.setApellidos(signUpRequest.getApellidos());
        empleados.setCorreo(signUpRequest.getEmail());
        empleados.setNumDoc(signUpRequest.getNumDoc());
        empleados.setTelefono(signUpRequest.getTelefono());
        empleados.setDireccion(signUpRequest.getDireccion());
        Set<Rol> assginedRoles = new HashSet<>();
        Rol userRol = rolRepository.findByNombreRol(Role.MOZO.name()).orElseThrow(() -> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        assginedRoles.add(userRol);
        empleados.setRoles(assginedRoles);
        empleados.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return empleadoRepository.save(empleados);
    }

    @Override
    public Empleados signUpAdmin(SignUpRequest signUpRequest) {
        Empleados empleados = new Empleados();
        empleados.setNombres(signUpRequest.getNombres());
        empleados.setApellidos(signUpRequest.getApellidos());
        empleados.setCorreo(signUpRequest.getEmail());
        empleados.setNumDoc(signUpRequest.getNumDoc());
        empleados.setTelefono(signUpRequest.getTelefono());
        empleados.setDireccion(signUpRequest.getDireccion());
        Set<Rol> assginedRoles = new HashSet<>();
        Rol userRol = rolRepository.findByNombreRol(Role.ADMIN.name()).orElseThrow(() -> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        Rol userRol2 = rolRepository.findByNombreRol(Role.COCINERO.name()).orElseThrow(()-> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        Rol userRol3 = rolRepository.findByNombreRol(Role.CAJA.name()).orElseThrow(()-> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        Rol userRol4 = rolRepository.findByNombreRol(Role.MOZO.name()).orElseThrow(()-> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        assginedRoles.add(userRol);
        assginedRoles.add(userRol2);
        assginedRoles.add(userRol3);
        assginedRoles.add(userRol4);
        empleados.setRoles(assginedRoles);
        empleados.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return empleadoRepository.save(empleados);
    }

    @Override
    public Empleados signUpCajero(SignUpRequest signUpRequest) {
        Empleados empleados = new Empleados();
        empleados.setNombres(signUpRequest.getNombres());
        empleados.setApellidos(signUpRequest.getApellidos());
        empleados.setCorreo(signUpRequest.getEmail());
        empleados.setNumDoc(signUpRequest.getNumDoc());
        empleados.setTelefono(signUpRequest.getTelefono());
        empleados.setDireccion(signUpRequest.getDireccion());
        Set<Rol> assginedRoles = new HashSet<>();
        Rol userRol = rolRepository.findByNombreRol(Role.CAJA.name()).orElseThrow(() -> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        assginedRoles.add(userRol);
        empleados.setRoles(assginedRoles);
        empleados.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return empleadoRepository.save(empleados);
    }

    @Override
    public Empleados signUpCocinero(SignUpRequest signUpRequest) {
        Empleados empleados = new Empleados();
        empleados.setNombres(signUpRequest.getNombres());
        empleados.setApellidos(signUpRequest.getApellidos());
        empleados.setCorreo(signUpRequest.getEmail());
        empleados.setNumDoc(signUpRequest.getNumDoc());
        empleados.setTelefono(signUpRequest.getTelefono());
        empleados.setDireccion(signUpRequest.getDireccion());
        Set<Rol> assginedRoles = new HashSet<>();
        Rol userRol = rolRepository.findByNombreRol(Role.COCINERO.name()).orElseThrow(()-> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        assginedRoles.add(userRol);
        empleados.setRoles(assginedRoles);
        empleados.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return empleadoRepository.save(empleados);
    }

    @Override
    public List<Empleados> todos() {
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
