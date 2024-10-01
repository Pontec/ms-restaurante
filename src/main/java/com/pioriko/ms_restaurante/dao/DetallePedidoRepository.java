package com.pioriko.ms_restaurante.dao;

import com.pioriko.ms_restaurante.entities.DetallePedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedidoEntity, Integer> {
}
