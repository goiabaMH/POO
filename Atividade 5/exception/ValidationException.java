package br.ifs.tdd.exception;

public class ValidationException extends RuntimeException{
	public ValidationException (String mensagem) {
		super(mensagem);
	}
}
