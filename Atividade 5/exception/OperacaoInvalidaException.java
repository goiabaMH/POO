package br.ifs.tdd.exception;

public class OperacaoInvalidaException extends RuntimeException{
	public OperacaoInvalidaException (String mensagem) {
		super(mensagem);
	}
}
