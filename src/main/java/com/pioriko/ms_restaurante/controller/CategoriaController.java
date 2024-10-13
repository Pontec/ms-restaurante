package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.dto.CategoriaDTO;
import com.pioriko.ms_restaurante.agregates.response.ResponseBase;
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
import java.util.Optional;

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
    public ResponseEntity<ResponseBase> crearCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO nuevaCategoria = categoriaService.save(categoriaDTO);
        ResponseBase response = new ResponseBase(201,"Categoria creada correctamente", Optional.of(nuevaCategoria));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<ResponseBase<List<CategoriaDTO>>> listarCategorias() {
        List<CategoriaDTO> categoriaEntityList = categoriaService.findAll();
        ResponseBase response = new ResponseBase(200,"Lista de categorias", Optional.of(categoriaEntityList));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<CategoriaDTO>> obtenerCategoria(@PathVariable Integer id) {
        Optional<CategoriaDTO> categoria = categoriaService.findById(id);
        ResponseBase responseBase = new ResponseBase(200,"Categoria encontrada correctamente", categoria);
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarCategoria(@PathVariable Integer id) {
        categoriaService.deleteById(id);
        ResponseBase responseBase = new ResponseBase<>(200, "Categoria eliminada correctamente", Optional.empty());
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseBase<CategoriaDTO>> actualizarCategoria(@PathVariable Integer id, @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoriaActualizada = categoriaService.update(id, categoriaDTO);
        ResponseBase responseBase = new ResponseBase<>(200,"Categoria actulizada correctamente", Optional.of(categoriaActualizada));
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }
}
