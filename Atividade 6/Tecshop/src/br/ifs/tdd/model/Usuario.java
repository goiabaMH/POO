package br.ifs.tdd.model;

public class Usuario {
	private String nome;
	private String email;
	private String telefone;

	public Usuario(String nome, String email, String telefone) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

}
