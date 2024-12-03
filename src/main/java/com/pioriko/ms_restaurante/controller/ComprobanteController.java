package com.pioriko.ms_restaurante.controller;

import com.pioriko.ms_restaurante.agregates.dto.PedidoResponseDTO;
import com.pioriko.ms_restaurante.agregates.mapper.PedidoMapper;
import com.pioriko.ms_restaurante.dao.PedidoRepository;
import com.pioriko.ms_restaurante.entities.PedidoEntity;
import com.pioriko.ms_restaurante.service.ComprobanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.JMRuntimeException;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api/v1/admin/comprobantes")
public class ComprobanteController {
    @Autowired
    private ComprobanteService comprobanteService;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoMapper pedidoMapper;

    @GetMapping("/{pedidoId}")
    public ResponseEntity<byte[]> generarComprobante(@PathVariable Integer pedidoId) {
        try {
            PedidoEntity pedido = pedidoRepository.findById(pedidoId)
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

            PedidoResponseDTO pedidoDTO = pedidoMapper.mapToPedidoResponseDto(pedido);

            byte[] reportContent = comprobanteService.generarComprobantePDF(pedidoDTO);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "inline; filename=comprobante.pdf");

            return new ResponseEntity<>(reportContent, headers, HttpStatus.OK);

        } catch (JMRuntimeException e) {
            return ResponseEntity.internalServerError().build();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Error inesperado: " + e.getMessage()).getBytes());
        }
    }
}