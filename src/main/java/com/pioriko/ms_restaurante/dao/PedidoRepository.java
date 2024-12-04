package com.pioriko.ms_restaurante.dao;

import com.pioriko.ms_restaurante.entities.PedidoEntity;
import com.pioriko.ms_restaurante.entities.enu.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {


    @Query("SELECT p FROM PedidoEntity p WHERE p.estado = :estado AND p.fechaPedido BETWEEN :start AND :end")
    List<PedidoEntity> findByEstadoAndFechaBetween(@Param("estado") EstadoPedido estado,
                                                   @Param("start") LocalDate start,
                                                   @Param("end") LocalDate end);


    @Query("SELECT SUM(dp.cantidad * dp.precio) FROM DetallePedidoEntity dp WHERE dp.pedido.fechaPedido = :fechaAperturaDate AND dp.pedido.horaPedido >= :fechaAperturaTime OR dp.pedido.fechaPedido > :fechaAperturaDate")
    Double sumTotalVentasDesdeApertura(@Param("fechaAperturaDate") LocalDate fechaAperturaDate, @Param("fechaAperturaTime") LocalTime fechaAperturaTime);
}