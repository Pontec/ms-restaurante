package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.agregates.request.SignInRequest;
import com.pioriko.ms_restaurante.agregates.request.SignUpRequest;
import com.pioriko.ms_restaurante.agregates.response.AuthenticationResponse;
import com.pioriko.ms_restaurante.entities.Usuario;

import java.util.List;

public interface AuthenticationService {
    Usuario signUpUser(SignUpRequest signUpRequest);
    Usuario signUpAdmin(SignUpRequest signUpRequest);
    List<Usuario> todos();

    AuthenticationResponse signin(SignInRequest signInRequest);

}
