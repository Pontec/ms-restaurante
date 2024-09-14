package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.entities.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioService {
    UserDetailsService userDetailService();
    List<Usuario> getUsuarios();
}
