package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.dto.MesasDTO;
import com.pioriko.ms_restaurante.agregates.response.ResponseBase;
import com.pioriko.ms_restaurante.service.MesasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/mesas")
public class MesasController {

    @Autowired
    private MesasService mesasService;

    @PostMapping("/crear")
    public ResponseEntity<ResponseBase<MesasDTO>> crearMesa(@RequestBody MesasDTO mesasDTO) {
        MesasDTO mesas = mesasService.save(mesasDTO);
        ResponseBase response = new ResponseBase(201, "Mesa creada Correctamente", Optional.of(mesas));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity listarMesas() {
        List<MesasDTO> listaMesas = mesasService.findAll();
        ResponseBase response = new ResponseBase(200, "Lista de Mesas", Optional.of(listaMesas));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ResponseBase<MesasDTO>> buscarMesa(@PathVariable Integer id) {
        MesasDTO mesas = mesasService.findById(id);
        ResponseBase response =  new ResponseBase(200, "Mesa Buscada", Optional.of(mesas));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarMesa(@PathVariable Integer id) {
        mesasService.delete(id);
        ResponseBase response = new ResponseBase(200, "Mesa Eliminada", Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseBase<MesasDTO>> actualizarMesa(@PathVariable Integer id, @RequestBody MesasDTO mesasDTO) {
        MesasDTO mesas = mesasService.update(id, mesasDTO);
        ResponseBase responseBase = new ResponseBase(200, "Mesa Actualizada", Optional.of(mesas));
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}/estado")
    public ResponseEntity<ResponseBase<MesasDTO>> actualizarEstadoMesa(@PathVariable Integer id, @RequestBody MesasDTO mesasDTO) {
        MesasDTO mesas = mesasService.updateEstadoMesa(id, mesasDTO);
        ResponseBase responseBase = new ResponseBase(200, "Estado de la mesa actualizado", Optional.of(mesas));
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }
}
