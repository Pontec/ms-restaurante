package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.dao.CajaRepository;
import com.pioriko.ms_restaurante.dao.EmpleadoRepository;
import com.pioriko.ms_restaurante.dao.PedidoRepository;
import com.pioriko.ms_restaurante.entities.CajaEntity;
import com.pioriko.ms_restaurante.entities.EmpleadosEntity;
import com.pioriko.ms_restaurante.service.CajaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CajaServiceImpl implements CajaService {

    private final CajaRepository cajaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final PedidoRepository pedidoRepository;

    @Override
    public CajaEntity abrirCaja(double montoInicial, Long idEmpleado) {
        EmpleadosEntity empleado = empleadoRepository.findById(idEmpleado).
                orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        CajaEntity caja = new CajaEntity();
        caja.setMontoInicial(montoInicial);
        caja.setFechaApertura(LocalDateTime.now());
        caja.setTotalVentas(0.0);
        caja.setAbierta(true);
        caja.setEmpleado(empleado);
        return cajaRepository.save(caja);
    }

    @Override
    public CajaEntity cerrarCaja(double montoFinal) {
        CajaEntity caja = cajaRepository.findFirstByOrderByFechaAperturaDesc();
        if(caja == null || !caja.getAbierta()) {
            throw new RuntimeException("No hay una caja abierta");
        }
        caja.setFechaCierre(LocalDateTime.now());
        caja.setMontoFinal(montoFinal);
        caja.setAbierta(false);

        Double totalVentas = pedidoRepository.sumTotalVentasDesdeApertura(caja.getFechaApertura().toLocalDate(), caja.getFechaApertura().toLocalTime());
        caja.setTotalVentas(totalVentas);
        return cajaRepository.save(caja);
    }

    @Override
    public CajaEntity getCajaAbierta() {
        return cajaRepository.findFirstByOrderByFechaAperturaDesc();
    }

    @Override
    public List<CajaEntity> getHistorialCajas() {
        return cajaRepository.findAllByOrderByFechaAperturaDesc();
    }

    public Double obtenerTotalVentasDesdeApertura() {
        CajaEntity caja = cajaRepository.findFirstByAbiertaTrue()
                .orElseThrow(() -> new RuntimeException("No hay caja abierta"));
        return pedidoRepository.sumTotalVentasDesdeApertura(caja.getFechaApertura().toLocalDate(), caja.getFechaApertura().toLocalTime());

    }
}
