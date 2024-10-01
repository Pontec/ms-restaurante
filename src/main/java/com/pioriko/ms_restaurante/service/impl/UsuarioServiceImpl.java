package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.dao.UsuarioRepository;
import com.pioriko.ms_restaurante.entities.Empleados;
import com.pioriko.ms_restaurante.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    @Override
    public UserDetailsService userDetailService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return usuarioRepository.findByCorreo(username).orElseThrow(
                        () -> new UsernameNotFoundException("Usuario no encontrado"));
            }
        };
    }

    @Override
    public List<Empleados> getUsuarios() {
        return usuarioRepository.findAll();
    }
}
