package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.dto.MetodoPagoDTO;
import com.pioriko.ms_restaurante.agregates.response.ResponseBase;
import com.pioriko.ms_restaurante.service.MetodoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/metodo-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @PostMapping("/crear")
    public ResponseEntity<ResponseBase<MetodoPagoDTO>> crearMetodoPago(@RequestBody MetodoPagoDTO metodoPagoDTO) {
        MetodoPagoDTO metodoPago = metodoPagoService.save(metodoPagoDTO);
        ResponseBase response = new ResponseBase(201, "Metodo de pago creado Correctamente", Optional.of(metodoPago));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/todos-activo")
    public ResponseEntity listarMetodosPago() {
        List<MetodoPagoDTO> listaMetodosPago = metodoPagoService.findAllActive();
        ResponseBase response = new ResponseBase(200, "Lista de Metodos de Pago", Optional.of(listaMetodosPago));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
