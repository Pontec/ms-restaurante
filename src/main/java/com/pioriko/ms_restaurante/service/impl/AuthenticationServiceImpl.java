package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.request.SignInRequest;
import com.pioriko.ms_restaurante.agregates.request.SignUpRequest;
import com.pioriko.ms_restaurante.agregates.response.AuthenticationResponse;
import com.pioriko.ms_restaurante.dao.RolRepository;
import com.pioriko.ms_restaurante.dao.UsuarioRepository;
import com.pioriko.ms_restaurante.entities.Rol;
import com.pioriko.ms_restaurante.entities.Role;
import com.pioriko.ms_restaurante.entities.Usuario;
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
    public Usuario signUpUser(SignUpRequest signUpRequest) {
        Usuario usuario = new Usuario();
        usuario.setNombres(signUpRequest.getNombres());
        usuario.setApellidos(signUpRequest.getApellidos());
        usuario.setEmail(signUpRequest.getEmail());
        usuario.setTipoDoc(signUpRequest.getTipoDoc());
        usuario.setNumDoc(signUpRequest.getNumDoc());
        Set<Rol> assginedRoles = new HashSet<>();
        Rol userRol = rolRepository.findByNombreRol(Role.USER.name()).orElseThrow(() -> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        assginedRoles.add(userRol);
        usuario.setRoles(assginedRoles);
        //HASH AL PASSWORD PENDIENTE
        usuario.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Transactional
    @Override
    public Usuario signUpAdmin(SignUpRequest signUpRequest) {
        Usuario usuario = new Usuario();
        usuario.setNombres(signUpRequest.getNombres());
        usuario.setApellidos(signUpRequest.getApellidos());
        usuario.setEmail(signUpRequest.getEmail());
        usuario.setTipoDoc(signUpRequest.getTipoDoc());
        usuario.setNumDoc(signUpRequest.getNumDoc());
        Set<Rol> assginedRoles = new HashSet<>();
        Rol userRol = rolRepository.findByNombreRol(Role.ADMIN.name()).orElseThrow(() -> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        Rol userRol2 = rolRepository.findByNombreRol(Role.USER.name()).orElseThrow(() -> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        assginedRoles.add(userRol);
        assginedRoles.add(userRol2);
        usuario.setRoles(assginedRoles);
        //HASH AL PASSWORD PENDIENTE
        usuario.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> todos() {
        return usuarioRepository.findAll();
    }

    @Override
    public AuthenticationResponse signin(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(),signInRequest.getPassword()));
        var user = usuarioRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email no valido"));

        var token = jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(token);
        return authenticationResponse;
    }
}
