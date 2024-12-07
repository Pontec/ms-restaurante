package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.request.SignInRequest;
import com.pioriko.ms_restaurante.agregates.request.SignUpRequest;
import com.pioriko.ms_restaurante.agregates.response.AuthenticationResponse;
import com.pioriko.ms_restaurante.entities.EmpleadosEntity;
import com.pioriko.ms_restaurante.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/autenticacion")
@RequiredArgsConstructor
public class AutenticacionController {
    private final AuthenticationService authenticationService;

    //Creacion de perfil usuario
    @PostMapping("/signupmoso")
    public ResponseEntity<EmpleadosEntity> signUpMoso(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUpMoso(signUpRequest));
    }

    //Creacion de perfil administrador
    @PostMapping("/signupadmin")
    public ResponseEntity<EmpleadosEntity> signUpAdmin(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUpAdmin(signUpRequest));
    }
    //Creacion de perfil cajero
    @PostMapping("/signupcajero")
    public ResponseEntity<EmpleadosEntity> signUpCajero(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUpCajero(signUpRequest));
    }

    @PostMapping("/signupcocinero")
    public ResponseEntity<EmpleadosEntity> signUpCocino(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUpCocinero(signUpRequest));
    }

    //Iniciar secion
    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }

    @GetMapping("/todos")
    public ResponseEntity<?> todos(){
        return ResponseEntity.ok(authenticationService.todos());
    }
}
