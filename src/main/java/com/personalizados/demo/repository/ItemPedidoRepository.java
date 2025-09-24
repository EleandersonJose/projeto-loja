package com.personalizados.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personalizados.demo.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
	
}