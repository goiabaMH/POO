package br.ifs.tdd.exception;

public class EstoqueInsuficienteException extends RuntimeException{
	public EstoqueInsuficienteException (String mensagem) {
		super(mensagem);
	}
}
