package com.pioriko.ms_restaurante.service.impl;

import com.pioriko.ms_restaurante.agregates.dto.PedidoResponseDTO;
import com.pioriko.ms_restaurante.agregates.dto.ProductoReporteDTO;
import com.pioriko.ms_restaurante.service.ComprobanteService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.management.JMRuntimeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ComprobanteServiceImpl implements ComprobanteService {

    public byte[] generarComprobantePDF(PedidoResponseDTO pedido) throws JMRuntimeException, FileNotFoundException {
        // Cargar el template Jasper
        String filePath = "src" + File.separator + "main" + File.separator +
                "resources" + File.separator + "templates" + File.separator + "report" + File.separator +
                "Report.jrxml";
        File reportFile = ResourceUtils.getFile(filePath);

        //final File imgLogo = ResourceUtils.getFile("classpath:/images/logo.jpg");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Preparar parámetros
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nombre_mozo", pedido.getEmpleado().getNombres());
        parametros.put("numero_mesa", pedido.getMesa().getNumeroMesa());
        parametros.put("nombre_cliente", pedido.getCliente().getNombre());
        parametros.put("fecha", formatter.format(now));

        System.out.println("Cantidad de productos: " + pedido.getDetallePedidos().size());
    pedido.getDetallePedidos().forEach(detalle -> {
        System.out.println("Producto original: " + detalle.getProducto().getNombre());
    });

        List<ProductoReporteDTO> productosDetalles = pedido.getDetallePedidos().stream()
        .map(detalle -> {
            double montoTotal = detalle.getCantidad() * detalle.getProducto().getPrecio();
            ProductoReporteDTO dto = new ProductoReporteDTO(
                detalle.getProducto().getNombre(),
                detalle.getCantidad(),
                detalle.getProducto().getPrecio(),
                montoTotal
            );
            // Debug para verificar mapping
            System.out.println("Producto mapeado: " + dto.getProducto());
            return dto;
        })
                .collect(Collectors.toList());
        
                System.out.println("Productos en lista final: " + productosDetalles.size());
    productosDetalles.forEach(p -> {
        System.out.println("Producto en lista: " + p.getProducto() + ", Cantidad: " + p.getCantidad());
    });

        JRBeanCollectionDataSource productosDataSource = new JRBeanCollectionDataSource(productosDetalles, false);

        parametros.put("productos", productosDataSource);
        parametros.put("medio_pago", "Efectivo");
        parametros.put("sub_total", calcularSubtotal(pedido));
        parametros.put("total", calcularTotal(pedido));
        parametros.put("imageDir", "classpath:/images/");

        // Generar reporte
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportFile.getAbsolutePath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, productosDataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al llenar el reporte: " + e.getMessage());
        }
    }

    private String calcularSubtotal(PedidoResponseDTO pedido) {
        double subtotal = pedido.getDetallePedidos().stream()
            .mapToDouble(detalle -> detalle.getCantidad() * detalle.getProducto().getPrecio())
            .sum();
        return String.format("%.2f", subtotal);
    }

    private String calcularTotal(PedidoResponseDTO pedido) {
        double subtotal = pedido.getDetallePedidos().stream()
            .mapToDouble(detalle -> detalle.getCantidad() * detalle.getProducto().getPrecio())
            .sum();
        double tax = subtotal * 0.18; // Assuming a tax rate of 18%
        double total = subtotal + tax;
        return String.format("%.2f", total);
    }
}
