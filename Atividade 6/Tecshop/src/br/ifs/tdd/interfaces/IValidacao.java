package br.ifs.tdd.interfaces;

import br.ifs.tdd.model.Produto;

public interface IValidacao {
	public void validarProduto(Produto produto);
	
	public void validarEstoque(Produto produto);
}
