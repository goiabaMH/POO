package br.ifs.tdd.service;

import br.ifs.tdd.model.Produto;

public class ItemPedido {
	private Produto produto;
	private int quantidade;
	

	public ItemPedido(Produto produto, int quantidade) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return quantidade;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public double calcularSubTotal() {
		return produto.getPreco()*quantidade;
	}
	
}
