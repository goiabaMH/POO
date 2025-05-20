package br.ifs.tdd.exception;

public class ProdutoNaoEncontradoException extends RuntimeException{
	public ProdutoNaoEncontradoException (String mensagem) {
		super(mensagem);
	}
}
