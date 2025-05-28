package br.ifs.tdd.repository;

import br.ifs.tdd.interfaces.IRepositorio;
import br.ifs.tdd.service.Pedido;

public class Repositorio implements IRepositorio{

	public boolean salvarPedido(Pedido pedido) {
		return true;
	}

}
