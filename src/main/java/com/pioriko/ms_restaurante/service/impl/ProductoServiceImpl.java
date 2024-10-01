package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.dto.ProductoDTO;
import com.pioriko.ms_restaurante.agregates.mapper.ProductoMapper;
import com.pioriko.ms_restaurante.dao.CategoriaRepository;
import com.pioriko.ms_restaurante.dao.ProductoRepository;
import com.pioriko.ms_restaurante.entities.CategoriaEntity;
import com.pioriko.ms_restaurante.entities.ProductoEntity;
import com.pioriko.ms_restaurante.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final CategoriaRepository categoriaRepository;

    @Override
    public ProductoDTO save(ProductoDTO productoDTO) {
        //Buscamos la categoria por Id
        CategoriaEntity categoria = categoriaRepository.findById(productoDTO.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + productoDTO.getIdCategoria()));

        // Convertir el DTO a entidad
        ProductoEntity producto = productoMapper.mapToEntity(productoDTO);
        producto.setCategoria(categoria);

        // Guardar el producto
        ProductoEntity nuevoProducto = productoRepository.save(producto);

        // Convertir de vuelta a DTO
        return productoMapper.mapToDto(nuevoProducto);
    }

    @Override
    public List<ProductoEntity> findAll() {
        List<ProductoEntity> producto = productoRepository.findAll();
        //return productos.stream().map(this::mapToDTO).collect(Collectors.toList());
        return producto;
    }

    @Override
    public ProductoDTO findById(int id) {
        ProductoEntity producto = productoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Producto no encontrada"));

        return productoMapper.mapToDto(producto);
    }

    @Override
    public void deleteById(int id) {
        ProductoEntity producto = productoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Producto no encontrada"));
        productoRepository.delete(producto);
    }

    @Override
    public ProductoDTO update(int id, ProductoDTO productoDto) {
        // Verificar si el producto existe
        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        // Actualizar el producto con los nuevos datos
        //CategoriaEntity categoria = categoriaRepository.findById(productoDto.getIdCategoria())
        //        .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + productoDto.getIdCategoria()));

        producto.setNombre(productoDto.getNombre());
        producto.setDescripcion(productoDto.getDescripcion());
        producto.setStock(productoDto.getStock());
        producto.setPrecio(productoDto.getPrecio());
        producto.setEstado(productoDto.getEstado());
        producto.setImagen(productoDto.getImagen());
        //producto.setCategoria(categoria);


        // Guardar el producto actualizado
        ProductoEntity productoActualizado = productoRepository.save(producto);

        return productoMapper.mapToDto(productoActualizado);
    }
}
