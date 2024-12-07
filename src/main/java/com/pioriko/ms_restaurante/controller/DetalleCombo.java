package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.dto.DetalleComboDTO;
import com.pioriko.ms_restaurante.agregates.response.ResponseBase;
import com.pioriko.ms_restaurante.service.DetalleComboService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/detalle-combo")
@RequiredArgsConstructor
public class DetalleCombo {

    private final DetalleComboService detalleComboService;

    @PostMapping("/guardar")
    public ResponseEntity<ResponseBase<DetalleCombo>> guardar(@RequestBody DetalleComboDTO detalleComboDto) {
        DetalleComboDTO detalle = detalleComboService.save(detalleComboDto);
        ResponseBase response = new ResponseBase(201, "Detalle de combo creado", Optional.of(detalle));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
