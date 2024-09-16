package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.request.SignInRequest;
import com.pioriko.ms_restaurante.agregates.request.SignUpRequest;
import com.pioriko.ms_restaurante.agregates.response.AuthenticationResponse;
import com.pioriko.ms_restaurante.entities.Empleados;
import com.pioriko.ms_restaurante.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/autenticacion")
@RequiredArgsConstructor
public class AutenticacionController {
    private final AuthenticationService authenticationService;

    //Creacion de perfil usuario
    @PostMapping("/signupuser")
    public ResponseEntity<Empleados> signUpUser(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUpUser(signUpRequest));
    }

    //Creacion de perfil administrador
    @PostMapping("/signupadmin")
    public ResponseEntity<Empleados> signUpAdmin(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUpAdmin(signUpRequest));
    }

    //Iniciar secion
    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }
}
