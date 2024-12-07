package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.dto.ComboDTO;
import com.pioriko.ms_restaurante.agregates.response.ResponseBase;
import com.pioriko.ms_restaurante.service.ComboService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/vi/admin/combo")
public class ComboController {

    private final ComboService comboService;

    @PostMapping("/crear")
    public ResponseEntity<ResponseBase<ComboDTO>> crearCombo (@RequestBody ComboDTO comboDTO){
        ComboDTO combo = comboService.saveCombo(comboDTO);
        ResponseBase responseBase = new ResponseBase(201, "Combo creado correctamente", Optional.of(combo));
        return new ResponseEntity(responseBase, HttpStatus.ACCEPTED);
    }

    @GetMapping("/todos")
    public ResponseEntity<ResponseBase<List<ComboDTO>>> getAllCombos(){
        List<ComboDTO> listaCombos = comboService.findAllCombo();
        ResponseBase responseBase = new ResponseBase(200, "Lista de combos", Optional.of(listaCombos));
        return new ResponseEntity(responseBase, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ResponseBase<ComboDTO>> getComboById(@PathVariable int id){
        ComboDTO comboDTO = comboService.findComboById(id);
       ResponseBase responseBase = new ResponseBase(200, "Combo encontrado", Optional.of(comboDTO));
       return new ResponseEntity(responseBase, HttpStatus.OK);
    }
}
