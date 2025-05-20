package br.ifs.tdd.model;

import java.util.Objects;

import br.ifs.tdd.exception.ValidationException;

public class Produto {

	private String identificador;
	private String nome;
	private String desc;
	private double preco;
	
	public Produto(String identificador, String nome, String desc, double preco) {
		setIdentificador(identificador);
		setNome(nome);
		setDescricao(desc);
		setPreco(preco);
	}

	public String getIdentificador() {
		return identificador;
	}
	
	private void setIdentificador(String identificador) {
		if (identificador == null || identificador.equals("")) {
			throw new ValidationException("Identificador obrigatório");
		}
		setTamanhoIdentificador(identificador);
		setApenasNumeros(identificador);
	}
	
	private void setTamanhoIdentificador(String identificador) {
		if (identificador.length() != 13) {
			throw new ValidationException("Tamanho do identificador inválido (13 caracteres)");
		}
	}
	
	private void setApenasNumeros(String identificador) {
		if(!(identificador.chars().allMatch(Character::isDigit))) {
			throw new ValidationException("Formato do identificador inválido (apenas números)");
		}
		this.identificador = identificador;
	}

	public String getNome() {
		return nome;
	}
	
	private void setNome(String nome) {
		if (nome == null || nome.equals("")) {
			throw new ValidationException("Nome obrigatório");
		}
		setTamanhoNome(nome);
	}
	
	private void setTamanhoNome(String nome) {
		if (nome.length() > 100) {
			throw new ValidationException("Nome excede 100 caracteres");
		}
		this.nome = nome;
	}

	public String getDesc() {
		return desc;
	}
	
	private void setDescricao(String desc) {
		if (desc == null || desc.equals("")) {
			throw new ValidationException("Descrição obrigatória");
		}
		setTamanhoDescricao(desc);
	}
	
	private void setTamanhoDescricao(String desc) {
		if (desc.length() > 500) {
			throw new ValidationException("Descrição excede 500 caracteres");
		}
		this.desc = desc;
	}

	public double getPreco() {
		return preco;
	}
	
	private void setPreco(double preco) {
		if (preco < 0) {
			throw new ValidationException("Preço inválido (deve ser positivo)");
		}
		this.preco = preco;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(identificador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(identificador, other.identificador);
	}

	@Override
	public String toString() {
		return "Produto [identificador=" + identificador + ", nome=" + nome + ", desc=" + desc + ", preco=" + preco
				+ "]";
	}
	
	
	
}
