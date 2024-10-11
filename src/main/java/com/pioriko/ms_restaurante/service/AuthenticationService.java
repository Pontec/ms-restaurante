package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.agregates.request.SignInRequest;
import com.pioriko.ms_restaurante.agregates.request.SignUpRequest;
import com.pioriko.ms_restaurante.agregates.response.AuthenticationResponse;
import com.pioriko.ms_restaurante.entities.Empleados;

import java.util.List;

public interface AuthenticationService {
    Empleados signUpUser(SignUpRequest signUpRequest);
    Empleados signUpAdmin(SignUpRequest signUpRequest);
    List<Empleados> todos();

    AuthenticationResponse signin(SignInRequest signInRequest);

}
