package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.dto.MesasDTO;
import com.pioriko.ms_restaurante.agregates.mapper.MesasMapper;
import com.pioriko.ms_restaurante.dao.MesasRepository;
import com.pioriko.ms_restaurante.entities.MesasEntity;
import com.pioriko.ms_restaurante.service.MesasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesasServiceImpl implements MesasService {

    @Autowired
    private MesasRepository mesasRepository;
    @Autowired
    private MesasMapper mesasMapper;


    @Override
    public MesasDTO save(MesasDTO mesasDto) {
        MesasEntity mesas = mesasMapper.mapToEntity(mesasDto);
        MesasEntity nuevaMesa = mesasRepository.save(mesas);
        return mesasMapper.mapToDto(nuevaMesa);
    }

    @Override
    public MesasDTO findById(Integer id) {
        MesasEntity mesas = mesasRepository.findById(id).orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
        return mesasMapper.mapToDto(mesas);
    }

    @Override
    public List<MesasDTO> findAll() {
        List<MesasEntity> mesas = mesasRepository.findAll();
        return mesas.stream().map(mesasMapper::mapToDto).toList();
    }

    @Override
    public MesasDTO update(Integer id, MesasDTO mesasDTO) {
        MesasEntity mesas = mesasRepository.findById(id).orElseThrow(()-> new RuntimeException("Mesa no encontrada"));
        mesas.setNumeroMesa(mesasDTO.getNumeroMesa());
        mesas.setCapacidad(mesasDTO.getCapacidad());
        mesas.setEstado(MesasEntity.EstadoMesa.valueOf(mesasDTO.getEstado()));
        return mesasMapper.mapToDto(mesasRepository.save(mesas));
    }

    @Override
    public void delete(Integer id) {
        mesasRepository.deleteById(id);
    }
}
