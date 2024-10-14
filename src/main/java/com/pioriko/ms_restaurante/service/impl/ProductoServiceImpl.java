package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.dto.CategoriaDTO;
import com.pioriko.ms_restaurante.agregates.dto.ProductoDTO;
import com.pioriko.ms_restaurante.agregates.mapper.ProductoMapper;
import com.pioriko.ms_restaurante.dao.CategoriaRepository;
import com.pioriko.ms_restaurante.dao.ProductoRepository;
import com.pioriko.ms_restaurante.entities.CategoriaEntity;
import com.pioriko.ms_restaurante.entities.ProductoEntity;
import com.pioriko.ms_restaurante.entities.enu.EstadoProducto;
import com.pioriko.ms_restaurante.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final CategoriaRepository categoriaRepository;

    @Override
    public ProductoDTO save(ProductoDTO productoDTO) {
        // Verificar si la categoría existe
        CategoriaEntity categoriaExiste = categoriaRepository.findById(productoDTO.getIdCategoria()).orElseThrow(()-> new NoSuchElementException("Categoria no encontrada"));
        // Convertir el DTO a la entidad del producto
        ProductoEntity productoEntity = productoMapper.mapToProductoEntity(productoDTO);
        productoEntity.setCategoria(categoriaExiste);
        // Guardar la entidad del producto en la base de datos
        ProductoEntity productoGuardado = productoRepository.save(productoEntity);
        // Convertir la entidad guardada a DTO y devolverla
        return productoMapper.mapToProductoDTO(productoGuardado);
    }

    @Override
    public List<ProductoDTO> findAll() {
        List<ProductoEntity> producto = productoRepository.findAll();
        return producto.stream().map(productoMapper::mapToProductoDTO).collect(Collectors.toList());
    }

    @Override
    public ProductoDTO findById(int id) {
        ProductoEntity producto = productoRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Producto no encontrado"));
        return productoMapper.mapToProductoDTO(producto);

    }

    @Override
    public void deleteById(int id) {
        ProductoEntity producto = productoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Producto no encontrada"));
        productoRepository.delete(producto);
    }

    @Override
    public ProductoDTO update(int id, ProductoDTO productoDto) {

        Optional<ProductoEntity> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            ProductoEntity producto = productoOptional.get();
            producto.setNombre(productoDto.getNombre());
            producto.setDescripcion(productoDto.getDescripcion());
            producto.setPrecio(productoDto.getPrecio());
            producto.setEstado(productoDto.getEstado());
            producto.setPorcion(productoDto.getPorcion());
            producto.setStock(productoDto.getStock());
            producto.setLitros(productoDto.getLitros());
            productoRepository.save(producto);
            return productoMapper.mapToProductoDTO(producto);
        }
        return null;
    }
}
