package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.dto.MesasDTO;
import com.pioriko.ms_restaurante.agregates.mapper.MesasMapper;
import com.pioriko.ms_restaurante.dao.MesasRepository;
import com.pioriko.ms_restaurante.entities.MesasEntity;
import com.pioriko.ms_restaurante.service.MesasService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MesasServiceImpl implements MesasService {

    private final MesasRepository mesasRepository;
    private final MesasMapper mesasMapper;


    @Override
    public MesasDTO save(MesasDTO mesasDto) {
        MesasEntity existeMesas = mesasRepository.findByNumeroMesa(mesasDto.getNumeroMesa());
        if (existeMesas != null) {
            throw new IllegalArgumentException("Ya existe una mesa con el número: " + mesasDto.getNumeroMesa());
        }
        MesasEntity mesas = mesasMapper.mapToMesasEntity(mesasDto);
        MesasEntity nuevaMesa = mesasRepository.save(mesas);
        return mesasMapper.mapToMesasDTO(nuevaMesa);
    }

    @Override
    public MesasDTO findById(Integer id) {
        MesasEntity mesas = mesasRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Mesa no encontrada"));
        return mesasMapper.mapToMesasDTO(mesas);
    }

    @Override
    public List<MesasDTO> findAll() {
        List<MesasEntity> mesas = mesasRepository.findAll();
        return mesas.stream().map(mesasMapper::mapToMesasDTO).toList();
    }

    @Override
    public MesasDTO update(Integer id, MesasDTO mesasDTO) {
        MesasEntity mesas = mesasRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Mesa no encontrada"));
        mesas.setNumeroMesa(mesasDTO.getNumeroMesa());
        mesas.setCapacidad(mesasDTO.getCapacidad());
        mesas.setEstado(mesasDTO.getEstado());
        return mesasMapper.mapToMesasDTO(mesasRepository.save(mesas));
    }

    @Override
    public void delete(Integer id) {
        MesasEntity existe =  mesasRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Mesa no encontrada"));
        if(existe!= null){
            mesasRepository.delete(existe);
        }
    }

    @Override
    public MesasDTO updateEstadoMesa(Integer id, MesasDTO mesasDTO) {
        MesasEntity mesas = mesasRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Mesa no encontrada"));
        mesas.setEstado(mesasDTO.getEstado());
        return mesasMapper.mapToMesasDTO(mesasRepository.save(mesas));
    }
}
