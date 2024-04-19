package com.aula.demo.dtos;

import com.aula.demo.Produto;

public record ProdutoDto(long id, String nome, int quantidade, String descricao, float preco ) {
	public ProdutoDto(Produto produto) {
		this(produto.getId(), produto.getNome(), produto.getQuantidade(), produto.getDescricao(), produto.getPreco());
	}

}
