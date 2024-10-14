package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.dto.ProductoDTO;
import com.pioriko.ms_restaurante.agregates.response.ResponseBase;
import com.pioriko.ms_restaurante.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping("/crear")
    public ResponseEntity<ResponseBase<ProductoDTO>> saveProductos(@RequestBody ProductoDTO productoDto) {
        ProductoDTO nuevoProducto = productoService.save(productoDto);
        ResponseBase response = new ResponseBase(201,"Producto creacdo Correctamente", Optional.of(nuevoProducto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/todos")
    public ResponseEntity<ResponseBase<List<ProductoDTO>>> listarCategorias() {
        List<ProductoDTO> productoDTOList = productoService.findAll();
        ResponseBase response = new ResponseBase(200,"Todos los productos", Optional.of(productoDTOList));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<ProductoDTO>> obtenerProducto (@PathVariable Integer id) {
        ProductoDTO producto = productoService.findById(id);
        ResponseBase response = new ResponseBase(200,"Producto encontrado", Optional.of(producto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<ResponseBase<ProductoDTO>> actualizarProducto(@PathVariable Integer id, @RequestBody ProductoDTO productoDto) {
        ProductoDTO productoActulizado = productoService.update(id, productoDto);
        ResponseBase responseBase = new ResponseBase<>(200, "Producto actualizado correctamente", Optional.of(productoActulizado));
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }
}
