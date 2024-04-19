package com.aula.demo.dtos;

import java.time.LocalDate;

import com.aula.demo.Pedido;
import com.aula.demo.enums.StatusPedido;

public record PedidoDto(long id, LocalDate datapedido , String endereco, StatusPedido status ) {
	public PedidoDto(Pedido pedido) {
		this(pedido.getId(), pedido.getDataPedido(), pedido.getEndereco(), pedido.getStatus());
	}

}
