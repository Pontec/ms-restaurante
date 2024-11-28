package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.dto.EmpleadoDTO;
import com.pioriko.ms_restaurante.agregates.request.SignUpRequest;
import com.pioriko.ms_restaurante.entities.EmpleadosEntity;
import com.pioriko.ms_restaurante.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    public final EmpleadoService empleadoService;

    @GetMapping("/hola")
    public ResponseEntity<String> saludoAdmin(){
        return ResponseEntity.ok("Hola admin");
    }

    @PostMapping("/empleado/{dni}")
    public ResponseEntity<?> empleado(@PathVariable String dni){
        return ResponseEntity.ok(empleadoService.getEmpleadoReniec(dni));
    }

    @PutMapping("/empleado/actualizar/{id}")
    public ResponseEntity<EmpleadosEntity> updateEmpleado(@PathVariable Long id, @RequestBody SignUpRequest signUpRequest) {
        EmpleadosEntity updatedEmpleado = empleadoService.updateEmpleado(id, signUpRequest);
        return ResponseEntity.ok(updatedEmpleado);
    }
}
