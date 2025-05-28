package br.ifs.tdd.interfaces;

import br.ifs.tdd.service.Pedido;

public interface IRepositorio {
	public boolean salvarPedido(Pedido pedido);
}
