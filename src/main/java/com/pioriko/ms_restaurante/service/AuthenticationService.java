package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.agregates.request.SignInRequest;
import com.pioriko.ms_restaurante.agregates.request.SignUpRequest;
import com.pioriko.ms_restaurante.agregates.response.AuthenticationResponse;
import com.pioriko.ms_restaurante.entities.EmpleadosEntity;

import java.util.List;

public interface AuthenticationService {
    EmpleadosEntity signUpMoso(SignUpRequest signUpRequest);
    EmpleadosEntity signUpAdmin(SignUpRequest signUpRequest);
    EmpleadosEntity signUpCajero(SignUpRequest signUpRequest);
    EmpleadosEntity signUpCocinero(SignUpRequest signUpRequest);
    List<EmpleadosEntity> todos();

    AuthenticationResponse signin(SignInRequest signInRequest);


}
