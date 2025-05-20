package br.ifs.tdd.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.ifs.tdd.exception.ValidationException;
import br.ifs.tdd.model.Produto;

class ProdutoTest {

	@Test
	void CT01produtoValido() {
		Produto produto = new Produto("1234567890123", "Caneta", "Esferográfica", 2.50);
		assertEquals("1234567890123", produto.getIdentificador());
		assertEquals("Caneta", produto.getNome());
		assertEquals("Esferográfica", produto.getDesc());
		assertEquals(2.50, produto.getPreco());
	}
	
	@Test
	void CT02identicadorVazioNulo() {
		ValidationException validacao = assertThrows(ValidationException.class, ()-> new Produto("", "Caneta", "Esferográfica", 2.50));
		assertEquals("Identificador obrigatório", validacao.getMessage());
	}
	
	@Test
	void CT03identificadorDeComprimento() {
		ValidationException validacao = assertThrows(ValidationException.class, ()-> new Produto("123456789012", "Caneta", "Esferográfica", 2.50));
		assertEquals("Tamanho do identificador inválido (13 caracteres)", validacao.getMessage());
	}
	
	@Test
	void CT04identificadorNaoNumerico() {
		ValidationException validacao = assertThrows(ValidationException.class, ()-> new Produto("12345ABC90123", "Caneta", "Esferográfica", 2.50));
		assertEquals("Formato do identificador inválido (apenas números)", validacao.getMessage());
	}
	
	@Test
	void CT05nomeVazioNulo() {
		ValidationException validacao = assertThrows(ValidationException.class, ()-> new Produto("1234567890123", "", "Esferográfica", 2.50));
		assertEquals("Nome obrigatório", validacao.getMessage());
	}
	
	@Test
	void CT06nomeLongo() {
		ValidationException validacao = assertThrows(ValidationException.class, ()-> new Produto("1234567890123", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Esferográfica", 2.50));
		assertEquals("Nome excede 100 caracteres", validacao.getMessage());
	}
	
	@Test
	void CT07descricaoVaziaNula() {
		ValidationException validacao = assertThrows(ValidationException.class, ()-> new Produto("1234567890123", "Caneta", "", 2.50));
		assertEquals("Descrição obrigatória", validacao.getMessage());
	}
	
	@Test
	void CT08descricaoLonga() {
		ValidationException validacao = assertThrows(ValidationException.class, ()-> new Produto("1234567890123", "Caneta", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" 
	+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 2.50));
		assertEquals("Descrição excede 500 caracteres", validacao.getMessage());
	}
	
	@Test
	void CT09precoNegativo() {
		ValidationException validacao = assertThrows(ValidationException.class, ()-> new Produto("1234567890123", "Caneta", "Esferográfica", -2.50));
		assertEquals("Preço inválido (deve ser positivo)", validacao.getMessage());
	}

}
