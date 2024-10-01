package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.dto.MesasDTO;
import com.pioriko.ms_restaurante.service.MesasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/mesas")
public class MesasController {

    @Autowired
    private MesasService mesasService;

    @PostMapping("/crear")
    public ResponseEntity<MesasDTO> crearMesa(@RequestBody MesasDTO mesasDTO) {
        MesasDTO mesas = mesasService.save(mesasDTO);
        return ResponseEntity.ok(mesas);
    }

    @GetMapping("/todos")
    public ResponseEntity listarMesas() {
        return ResponseEntity.ok(mesasService.findAll());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<MesasDTO> buscarMesa(@PathVariable Integer id) {
        MesasDTO mesas = mesasService.findById(id);
        return ResponseEntity.ok(mesas);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarMesa(@PathVariable Integer id) {
        mesasService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<MesasDTO> actualizarMesa(@PathVariable Integer id, @RequestBody MesasDTO mesasDTO) {
        MesasDTO mesas = mesasService.update(id, mesasDTO);
        return ResponseEntity.ok(mesas);
    }
}
