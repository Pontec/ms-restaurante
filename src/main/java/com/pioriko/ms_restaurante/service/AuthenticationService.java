package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.agregates.request.SignInRequest;
import com.pioriko.ms_restaurante.agregates.request.SignUpRequest;
import com.pioriko.ms_restaurante.agregates.response.AuthenticationResponse;
import com.pioriko.ms_restaurante.entities.Empleados;

import java.util.List;

public interface AuthenticationService {
    Empleados signUpMoso(SignUpRequest signUpRequest);
    Empleados signUpAdmin(SignUpRequest signUpRequest);
    Empleados signUpCajero(SignUpRequest signUpRequest);
    Empleados signUpCocinero(SignUpRequest signUpRequest);
    List<Empleados> todos();

    AuthenticationResponse signin(SignInRequest signInRequest);


}
