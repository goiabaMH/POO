package br.ifs.tdd.model;

public class Produto {
	private String nome;
	private double preco;
	private int quantidade;
	private String descricao;

	public Produto(String nome, double preco, int quantidade, String descricao) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	public String getDescricao() {
		return descricao;
	}

}
