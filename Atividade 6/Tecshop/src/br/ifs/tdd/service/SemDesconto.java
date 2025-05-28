package br.ifs.tdd.service;

import br.ifs.tdd.interfaces.IEstrategiaDesconto;

public class SemDesconto implements IEstrategiaDesconto{

	public double aplicar(double total, Pedido pedido) {
		return 0;
	}

}
