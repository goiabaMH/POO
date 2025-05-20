package br.ifs.tdd.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.ifs.tdd.exception.ValidationException;
import br.ifs.tdd.model.Lote;
import br.ifs.tdd.model.Produto;

class LoteTest {

	@Test
	void CT10loteValido() {
		Produto produto = new Produto("1234567890123", "Caneta", "Esferográfica", 2.50);
		Lote lote = new Lote(produto, 20, LocalDate.now().plusDays(1));
		assertEquals(produto, lote.getProduto());
		assertEquals(20, lote.getQuantidade());
		assertEquals(LocalDate.now().plusDays(1), lote.getDataValidade());
	}
	
	@Test
	void CT11produtoNulo() {
		Produto produto = null;
		ValidationException validacao = assertThrows(ValidationException.class, ()-> new Lote(produto, 20, LocalDate.of(2025, 05, 14)));
		assertEquals("Produto inválido", validacao.getMessage());
	}
	
	@Test
	void CT12quantidadeNegativa() {
		Produto produto = new Produto("1234567890123", "Caneta", "Esferográfica", 2.50);
		ValidationException validacao = assertThrows(ValidationException.class, ()-> new Lote(produto, -20, LocalDate.of(2025, 05, 14)));
		assertEquals("Quantidade inválida (deve ser positiva)", validacao.getMessage());
	}
	
	@Test
	void CT13validadeNula() {
		Produto produto = new Produto("1234567890123", "Caneta", "Esferográfica", 2.50);
		ValidationException validacao = assertThrows(ValidationException.class, ()-> new Lote(produto, 20, null));
		assertEquals("Data de validade obrigatória", validacao.getMessage());
	}
	
	@Test
	void CT14validadeNoPassado() {
		Produto produto = new Produto("1234567890123", "Caneta", "Esferográfica", 2.50);
		ValidationException validacao = assertThrows(ValidationException.class, ()-> new Lote(produto, 20, LocalDate.of(2025, 05, 14)));
		assertEquals("Data de validade vencida", validacao.getMessage());
	}

}
