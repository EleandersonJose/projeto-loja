package com.personalizados.demo.request;

import java.util.List;

import com.personalizados.demo.model.Cliente;
import com.personalizados.demo.model.ItemPedido;

import lombok.Data;

@Data
public class PedidoRequest {

	private Cliente cliente;
	private List<ItemPedido> itens;
}