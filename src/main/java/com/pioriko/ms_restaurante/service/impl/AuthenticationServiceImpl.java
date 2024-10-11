package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.request.SignInRequest;
import com.pioriko.ms_restaurante.agregates.request.SignUpRequest;
import com.pioriko.ms_restaurante.agregates.response.AuthenticationResponse;
import com.pioriko.ms_restaurante.dao.RolRepository;
import com.pioriko.ms_restaurante.dao.UsuarioRepository;
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

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Transactional
    @Override
    public Empleados signUpUser(SignUpRequest signUpRequest) {
        Empleados empleados = new Empleados();
        empleados.setNombres(signUpRequest.getNombres());
        empleados.setApellidos(signUpRequest.getApellidos());
        empleados.setCorreo(signUpRequest.getEmail());
        //empleados.setTipoDoc(signUpRequest.getTipoDoc());
        empleados.setNumDoc(signUpRequest.getNumDoc());
        Set<Rol> assginedRoles = new HashSet<>();
        Rol userRol = rolRepository.findByNombreRol(Role.MOZO.name()).orElseThrow(() -> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        assginedRoles.add(userRol);
        empleados.setRoles(assginedRoles);
        //HASH AL PASSWORD PENDIENTE
        empleados.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return usuarioRepository.save(empleados);
    }

    @Transactional
    @Override
    public Empleados signUpAdmin(SignUpRequest signUpRequest) {
        Empleados empleados = new Empleados();
        empleados.setNombres(signUpRequest.getNombres());
        empleados.setApellidos(signUpRequest.getApellidos());
        empleados.setCorreo(signUpRequest.getEmail());
        //empleados.setTipoDoc(signUpRequest.getTipoDoc());
        empleados.setNumDoc(signUpRequest.getNumDoc());
        Set<Rol> assginedRoles = new HashSet<>();
        Rol userRol = rolRepository.findByNombreRol(Role.ADMIN.name()).orElseThrow(() -> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        Rol userRol2 = rolRepository.findByNombreRol(Role.MOZO.name()).orElseThrow(() -> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        assginedRoles.add(userRol);
        assginedRoles.add(userRol2);
        empleados.setRoles(assginedRoles);
        //HASH AL PASSWORD PENDIENTE
        empleados.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return usuarioRepository.save(empleados);
    }

    @Override
    public List<Empleados> todos() {
        return usuarioRepository.findAll();
    }

    @Override
    public AuthenticationResponse signin(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(),signInRequest.getPassword()));
        var user = usuarioRepository.findByCorreo(signInRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email no valido"));

        var token = jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(token);
        return authenticationResponse;
    }
}
