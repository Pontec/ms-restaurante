package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.dto.ProductoDTO;
import com.pioriko.ms_restaurante.entities.ProductoEntity;
import com.pioriko.ms_restaurante.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping("/crear")
    public ResponseEntity<ProductoDTO> saveProductos(@RequestBody ProductoDTO productoDto) {
        ProductoDTO nuevoProducto = productoService.save(productoDto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }


    @GetMapping("/todos")
    public List<ProductoEntity> listarCategorias() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProducto (@PathVariable Integer id) {
        ProductoDTO producto = productoService.findById(id);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
        productoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Integer id, @RequestBody ProductoDTO productoDto) {
        ProductoDTO productoActulizado = productoService.update(id, productoDto);
        return new ResponseEntity<>(productoActulizado, HttpStatus.OK);
    }
}
