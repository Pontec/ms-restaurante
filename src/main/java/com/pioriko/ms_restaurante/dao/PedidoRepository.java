package com.pioriko.ms_restaurante.dao;

import com.pioriko.ms_restaurante.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {

    @Query("SELECT DISTINCT p FROM PedidoEntity p " +
            "LEFT JOIN FETCH p.listDetallePedidos d " +
            "LEFT JOIN FETCH d.producto pr " +
            "LEFT JOIN FETCH d.combo c " +
            "LEFT JOIN FETCH p.cliente cl " +
            "LEFT JOIN FETCH p.mesa m")
    List<PedidoEntity> findAllPedidosConDetalles();

}
