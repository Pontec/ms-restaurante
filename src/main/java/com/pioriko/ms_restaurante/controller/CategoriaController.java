package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.dto.CategoriaDTO;
import com.pioriko.ms_restaurante.entities.CategoriaEntity;
import com.pioriko.ms_restaurante.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/categorias")
@RequiredArgsConstructor
@Tag(
        name = "API GESTION DE CATEGORIAS",
        description = "API que contiene los endpoints para la gestión de categorias"
)
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping("/crear")
    @Operation(summary = "Crear una nueva categoria")
    @ApiResponse(responseCode = "201", description = "Categoria creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Error en los datos de la categoria")
    public ResponseEntity<CategoriaEntity> crearCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaEntity nuevaCategoria = categoriaService.save(categoriaDTO);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public List<CategoriaEntity> listarCategorias() {
        return categoriaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaEntity> obtenerCategoria(@PathVariable Integer id) {
        CategoriaEntity categoria = categoriaService.findById(id);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Integer id) {
        categoriaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/actulizar/{id}")
    public ResponseEntity<CategoriaEntity> actualizarCategoria(@PathVariable Integer id, @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaEntity categoriaActualizada = categoriaService.update(id, categoriaDTO);
        return new ResponseEntity<>(categoriaActualizada, HttpStatus.OK);
    }
}
