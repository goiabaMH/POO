package br.ifs.tdd.controller;

import br.ifs.tdd.interfaces.IEstrategiaDesconto;
import br.ifs.tdd.model.Produto;

public class ControladorPedido {

	public boolean iniciarPedido() {
		return true;
	}

	public boolean adicionarItem(Produto produto, int quantidade) {
		return true;
	}

	public double calcularTotal(IEstrategiaDesconto desconto) {
		return 0;
	}

	public boolean finalizarPedido(IEstrategiaDesconto desconto) {
		return true;
	}
}
