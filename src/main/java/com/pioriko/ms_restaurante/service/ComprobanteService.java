package com.pioriko.ms_restaurante.service;

import com.pioriko.ms_restaurante.agregates.dto.PedidoResponseDTO;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface ComprobanteService {

    byte[] generarComprobantePDF(PedidoResponseDTO pedido) throws JRException, FileNotFoundException;
}
