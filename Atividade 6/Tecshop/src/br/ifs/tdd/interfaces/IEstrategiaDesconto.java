package br.ifs.tdd.interfaces;

import br.ifs.tdd.service.Pedido;

public interface IEstrategiaDesconto {
	public double aplicar(double total, Pedido pedido);
}
