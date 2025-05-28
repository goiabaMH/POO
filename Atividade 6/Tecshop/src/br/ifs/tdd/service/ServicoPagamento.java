package br.ifs.tdd.service;

import br.ifs.tdd.interfaces.IPagamento;

public class ServicoPagamento implements IPagamento{
	public boolean processarPagamento(double total) {
		return true;
	}
}
