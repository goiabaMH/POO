package br.ifs.tdd.exception;

public class ProdutoDuplicadoException extends RuntimeException{
	public ProdutoDuplicadoException (String mensagem) {
		super(mensagem);
	}
}
