package br.ifs.tdd.model;

import java.time.LocalDate;

import br.ifs.tdd.exception.ValidationException;

public class Lote {
	private Produto produto;
	private int quantidade;
	private LocalDate dataValidade;

	public Lote(Produto produto, int quantidade, LocalDate dataValidade) {
		super();
		setProduto(produto);
		setQuantidade(quantidade);
		setValidade(dataValidade);
	}

	public Produto getProduto() {
		return produto;
	}
	
	private void setProduto(Produto produto) {
		if (produto == null) {
			throw new ValidationException("Produto inválido");
		}
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}
	
	private void setQuantidade(int quantidade) {
		if (quantidade < 0) {
			throw new ValidationException("Quantidade inválida (deve ser positiva)");
		}
		this.quantidade = quantidade;
	}
	
	public void diminuirQuantidade(int quantidade) {
		this.quantidade -= quantidade;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}
	
	private void setValidade(LocalDate dataValidade) {
		if (dataValidade == null) {
			throw new ValidationException("Data de validade obrigatória");
		}
		validadeVencida(dataValidade);
		this.dataValidade = dataValidade;
	}
	
	private void validadeVencida(LocalDate dataValidade) {
		if(dataValidade.isBefore(LocalDate.now())) {
			throw new ValidationException("Data de validade vencida");
		}
	}

	@Override
	public String toString() {
		return "Lote [Quantidade=" + quantidade + ", Validade=" + dataValidade + ", "+ produto +"]";
	}
	
	

}
