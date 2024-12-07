package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.response.ResponseBase;
import com.pioriko.ms_restaurante.entities.CajaEntity;
import com.pioriko.ms_restaurante.service.CajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/caja")
public class CajaController {

    @Autowired
    private CajaService cajaService;

    @PostMapping("/abrir")
    public ResponseEntity<ResponseBase<CajaEntity>> abrirCaja(@RequestBody Map<String, Double> request) {
        Double montoInicial = request.get("montoInicial");
        Long idEmpleado = request.get("empleadoId").longValue();
        CajaEntity caja = cajaService.abrirCaja(montoInicial, idEmpleado);
        ResponseBase response = new ResponseBase(200,"Caja abierta correctamente", Optional.of(caja));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/cerrar")
    public ResponseEntity<ResponseBase<CajaEntity>> cerrarCaja(@RequestBody Map<String, Double> request) {
        Double montoFinal = request.get("montoFinal");
        CajaEntity caja = cajaService.cerrarCaja(montoFinal);
        ResponseBase response = new ResponseBase(200,"Caja cerrada correctamente", Optional.of(caja));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/ventas-desde-apertura")
    public ResponseEntity<ResponseBase<Double>> obtenerTotalVentasDesdeApertura() {
        Double totalVentas = cajaService.obtenerTotalVentasDesdeApertura();
        if (totalVentas == null) {
            totalVentas = 0.0;
        }
        ResponseBase response = new ResponseBase(200,"Caja actual obtenida correctamente", Optional.of(totalVentas));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/historial-cierres")
    public ResponseEntity<ResponseBase<CajaEntity>> obtenerHistorialCajas() {
        ResponseBase response = new ResponseBase(200,"Historial de cajas obtenido correctamente", Optional.of(cajaService.getHistorialCajas()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/actual")
    public ResponseEntity<ResponseBase<CajaEntity>> obtenerCajaActual() {
        CajaEntity caja = cajaService.getCajaAbierta();
        ResponseBase response = new ResponseBase(200,"Caja actual obtenida correctamente", Optional.of(caja));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
