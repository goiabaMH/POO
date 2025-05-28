package br.ifs.tdd.interfaces;

import br.ifs.tdd.model.Moeda;

public interface IConversorMoeda {
	public Moeda converter(Moeda valorOrigem, String codigoMoedaDesconto);
}
