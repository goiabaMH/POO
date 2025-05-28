package br.ifs.tdd.service;

import java.util.ArrayList;
import java.util.List;

import br.ifs.tdd.interfaces.IEstrategiaDesconto;
import br.ifs.tdd.model.Moeda;
import br.ifs.tdd.model.Produto;

public class Pedido {
	private List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
	private double total;

	public boolean adicionarItem(Produto produto, int quantidade) {
		return true;
	}

	public Moeda calcularTotal(IEstrategiaDesconto desconto) {
		return null;
	}

	private double aplicarDesconto(double total, IEstrategiaDesconto desconto) {
		return 0;
	}
	
	private Moeda converter(Moeda valor, IEstrategiaDesconto desconso) {
		return null;
	}

}
