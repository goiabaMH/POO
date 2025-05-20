package br.ifs.tdd.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.ifs.tdd.exception.EstoqueInsuficienteException;
import br.ifs.tdd.exception.OperacaoInvalidaException;
import br.ifs.tdd.exception.ProdutoDuplicadoException;
import br.ifs.tdd.exception.ProdutoNaoEncontradoException;
import br.ifs.tdd.exception.ValidationException;
import br.ifs.tdd.model.Lote;
import br.ifs.tdd.model.Produto;
import br.ifs.tdd.service.Estoque;

class EstoqueTest {

	@Test
	void CT15produtoValido() {
		Produto produto = new Produto("1234567890123", "Caneta", "Esferográfica", 2.50);
		Estoque estoque = new Estoque();
		estoque.cadastrarProduto(produto);
	}

//	@Test
//	void CT15produtoValido() {
//		Estoque estoque = new Estoque();
//		estoque.cadastrarProduto("1234567890123", "Caneta", "Esferográfica", 2.50));
//	}

	@Test
	void CT16produtoNulo() {
		Produto produto = null;
		Estoque estoque = new Estoque();
		ValidationException validacao = assertThrows(ValidationException.class,
				() -> estoque.cadastrarProduto(produto));
		assertEquals("Produto inválido", validacao.getMessage());
	}

	@Test
	void CT17cadastroProdutoDuplicado() {
		Produto produto = new Produto("1234567890123", "Caneta", "Esferográfica", 2.50);
		Estoque estoque = new Estoque();
		estoque.cadastrarProduto(produto);
		Produto produto2 = new Produto("1234567890123", "Geladeira", "Eletrolux", .50);
		ProdutoDuplicadoException validacao = assertThrows(ProdutoDuplicadoException.class,
				() -> estoque.cadastrarProduto(produto2));
		assertEquals("Produto duplicado", validacao.getMessage());
	}

	@Test
	void CT18exclusaoProduto() {
		Produto produto = new Produto("1234567890123", "Caneta", "Esferográfica", 2.50);
		Estoque estoque = new Estoque();
		estoque.cadastrarProduto(produto);
		estoque.removerProduto("1234567890123");
	}

	@Test
	void CT19exclusaoProdutoNulo() {
		Estoque estoque = new Estoque();
		ValidationException validacao = assertThrows(ValidationException.class, () -> estoque.removerProduto(null));
		assertEquals("Identificador de produto inválido", validacao.getMessage());
	}

	@Test
	void CT20exclusaoProdutoNaoCadastrado() {
		Estoque estoque = new Estoque();
		ProdutoNaoEncontradoException validacao = assertThrows(ProdutoNaoEncontradoException.class,
				() -> estoque.removerProduto("1234567890123"));
		assertEquals("Produto não existe", validacao.getMessage());
	}

	@Test
	void CT21exclusaoProdutoComLoteAssociado() {
		Produto produto = new Produto("1234567890123", "Caneta", "Esferográfica", 2.50);
		Lote lote = new Lote(produto, 20, LocalDate.now().plusDays(1));
		Estoque estoque = new Estoque();
		estoque.cadastrarLote(lote);
		OperacaoInvalidaException validacao = assertThrows(OperacaoInvalidaException.class,
				() -> estoque.removerProduto("1234567890123"));
		assertEquals("Remova os lotes associados primeiro", validacao.getMessage());
	}

	@Test
	void CT22buscarProdutoNoEstoque() {
		Produto produto = new Produto("1234567890123", "Caneta", "Esferográfica", 2.50);
		Estoque estoque = new Estoque();
		estoque.cadastrarProduto(produto);
		assertEquals("Produto [identificador=1234567890123, nome=Caneta, desc=Esferográfica, preco=2.5]",
				estoque.buscar("1234567890123"));
	}

	@Test
	void CT23buscaProdutoNulo() {
		Estoque estoque = new Estoque();
		ValidationException validacao = assertThrows(ValidationException.class, () -> estoque.buscar(null));
		assertEquals("Identificador de produto inválido", validacao.getMessage());
	}

	@Test
	void CT24buscaProdutoNaoCadastrado() {
		Estoque estoque = new Estoque();
		ProdutoNaoEncontradoException validacao = assertThrows(ProdutoNaoEncontradoException.class,
				() -> estoque.buscar("1234567890122"));
		assertEquals("Produto não existe", validacao.getMessage());
	}

	@Test
	void CT25atualizaProdutoNoEstoque() {
		Produto produto = new Produto("1234567890123", "Caneta", "Esferográfica", 2.50);
		Produto produto2 = new Produto("1234567890123", "Geladeira", "Esferográfica", 2.50);
		Estoque estoque = new Estoque();
		estoque.cadastrarProduto(produto);
		estoque.atualizarProduto(produto2);
	}

	@Test
	void CT26atualizarProdutoNulo() {
		Estoque estoque = new Estoque();
		ValidationException validacao = assertThrows(ValidationException.class, () -> estoque.atualizarProduto(null));
		assertEquals("Produto inválido", validacao.getMessage());
	}

	@Test
	void CT27atualizarProdutoNaoCadastrado() {
		Produto produto = new Produto("1234567890123", "Caneta", "Esferográfica", 2.50);
		Estoque estoque = new Estoque();
		ProdutoNaoEncontradoException validacao = assertThrows(ProdutoNaoEncontradoException.class,
				() -> estoque.atualizarProduto(produto));
		assertEquals("Produto não existe", validacao.getMessage());
	}

	@Test
	void CT28adicaoDeItensNoEstoque() {
		Produto produto = new Produto("1234567890123", "Caneta", "Esferográfica", 2.50);
		Estoque estoque = new Estoque();
		estoque.cadastrarProduto(produto);
		estoque.adicionar("1234567890123", 30, LocalDate.now().plusDays(3));
	}

	@Test
	void CT29adicaoDeItensComIdentificadorNulo() {
		Estoque estoque = new Estoque();
		ValidationException validacao = assertThrows(ValidationException.class,
				() -> estoque.adicionar(null, 30, LocalDate.now().plusDays(3)));
		assertEquals("Identificador de produto inválido", validacao.getMessage());
	}

	@Test
	void CT30adicaoDeIdentifciadorNaoCadastrado() {
		Estoque estoque = new Estoque();
		ProdutoNaoEncontradoException validacao = assertThrows(ProdutoNaoEncontradoException.class,
				() -> estoque.adicionar("1234567890123", 30, LocalDate.now().plusDays(3)));
		assertEquals("Produto não existe", validacao.getMessage());
	}

	@Test
	void CT31adicaoDeEstoqueNegativo() {
		Estoque estoque = new Estoque();
		estoque.cadastrarProduto(new Produto("1234567890123", "Caneta", "Esferográfica", 2.50));
		ValidationException validacao = assertThrows(ValidationException.class,
				() -> estoque.adicionar("1234567890123", -30, LocalDate.now().plusDays(3)));
		assertEquals("Quantidade inválida (deve ser positiva)", validacao.getMessage());
	}

	@Test
	void CT32adicaoDeEstoqueComDataNula() {
		Estoque estoque = new Estoque();
		estoque.cadastrarProduto(new Produto("1234567890123", "Caneta", "Esferográfica", 2.50));
		ValidationException validacao = assertThrows(ValidationException.class,
				() -> estoque.adicionar("1234567890123", 30, null));
		assertEquals("Data de validade obrigatória", validacao.getMessage());
	}

	@Test
	void CT33adicaoDeEstoqueComDataVencida() {
		Estoque estoque = new Estoque();
		estoque.cadastrarProduto(new Produto("1234567890123", "Caneta", "Esferográfica", 2.50));
		ValidationException validacao = assertThrows(ValidationException.class,
				() -> estoque.adicionar("1234567890123", 30, LocalDate.now().minusDays(3)));
		assertEquals("Data de validade vencida", validacao.getMessage());
	}

	@Test
	void CT34retiradaValidaNoEstoque() {
		Estoque estoque = new Estoque();
		estoque.cadastrarProduto(new Produto("1234567890123", "Caneta", "Esferográfica", 2.50));
		estoque.adicionar("1234567890123", 15, LocalDate.now().plusDays(3));
		estoque.retirar("1234567890123", 5);
		assertEquals(10, estoque.getLotes().get(0).getQuantidade());
	}

	@Test
	void CT35retiradaDeValidadeProxima() {
		Estoque estoque = new Estoque();
		estoque.cadastrarProduto(new Produto("1234567890123", "Caneta", "Esferográfica", 2.50));
		estoque.adicionar("1234567890123", 10, LocalDate.now().plusDays(1));
		estoque.adicionar("1234567890123", 20, LocalDate.now().plusDays(3));
		estoque.retirar("1234567890123", 15);
		assertEquals(0, estoque.getLotes().get(0).getQuantidade());
		assertEquals(15, estoque.getLotes().get(1).getQuantidade());
	}

	@Test
	void CT36retiradaComIdentificadorNulo() {
		Estoque estoque = new Estoque();
		ValidationException validacao = assertThrows(ValidationException.class, () -> estoque.retirar(null, 5));
		assertEquals("Identificador de produto inválido", validacao.getMessage());
	}

	@Test
	void CT37retiradaComIdentificadorNaoCadastrado() {
		Estoque estoque = new Estoque();
		ProdutoNaoEncontradoException validacao = assertThrows(ProdutoNaoEncontradoException.class,
				() -> estoque.retirar("1234567890123", 5));
		assertEquals("Produto não existe", validacao.getMessage());
	}

	@Test
	void CT38retiradaDoEstoqueComQuantidadeNegativa() {
		Estoque estoque = new Estoque();
		ValidationException validacao = assertThrows(ValidationException.class,
				() -> estoque.retirar("1234567890123", -5));
		assertEquals("Quantidade inválida", validacao.getMessage());
	}

	@Test
	void CT39retiradaDoEstoqueMaiorQueDisponivel() {
		Produto produto = new Produto("1234567890123", "Caneta", "Esferográfica", 2.50);
		Estoque estoque = new Estoque();
		estoque.cadastrarProduto(produto);
		estoque.cadastrarLote(new Lote(produto, 4, LocalDate.now().plusDays(3)));
		EstoqueInsuficienteException validacao = assertThrows(EstoqueInsuficienteException.class,
				() -> estoque.retirar("1234567890123", 5));
		assertEquals("Estoque insuficiente", validacao.getMessage());
	}

	@Test
	void CT40ObterQuantidadeTotalDeItens() {
		Produto produto = new Produto("1234567890123", "Caneta", "Esferográfica", 2.50);
		Produto produto2 = new Produto("1234567890125", "Geladeira", "Média", 2.2000);
		Estoque estoque = new Estoque();
		estoque.cadastrarLote(new Lote(produto, 15, LocalDate.now().plusDays(3)));
		estoque.cadastrarLote(new Lote(produto2, 5, LocalDate.now().plusDays(3)));
		assertEquals(20, estoque.obterQuantidade());
	}

	@Test
	void CT41ObterQuantidadeEspecifica() {
		Produto produto = new Produto("1234567890123", "Caneta", "Esferográfica", 2.50);
		Produto produto2 = new Produto("1234567890125", "Geladeira", "Média", 2.2000);
		Estoque estoque = new Estoque();
		estoque.cadastrarLote(new Lote(produto, 15, LocalDate.now().plusDays(3)));
		estoque.cadastrarLote(new Lote(produto2, 5, LocalDate.now().plusDays(3)));
		assertEquals(15, estoque.obterQuantidadeProduto("1234567890123"));
	}

	@Test
	void CT42obterQuantidadeProdutoSemCadastro() {
		Estoque estoque = new Estoque();
		ProdutoNaoEncontradoException validacao = assertThrows(ProdutoNaoEncontradoException.class,
				() -> estoque.obterQuantidadeProduto("1234567890123"));
		assertEquals("Produto não existe", validacao.getMessage());
	}

	@Test
	void CT43obterQuantidadeIdentificadorNulo() {
		Estoque estoque = new Estoque();
		ValidationException validacao = assertThrows(ValidationException.class,
				() -> estoque.obterQuantidadeProduto(null));
		assertEquals("Identificador de produto inválido", validacao.getMessage());
	}

	@Test
	void CT44impressaoTextual() {
		Estoque estoque = new Estoque();
		estoque.cadastrarProduto(new Produto("1234567890123", "Caneta", "Esferográfica", 2.50));
		estoque.cadastrarProduto(new Produto("1234567890124", "Geladeira", "Eletrolux", 20000.50));
		estoque.adicionar("1234567890123", 5, LocalDate.now().plusDays(3));
		estoque.adicionar("1234567890124", 10, LocalDate.now().plusDays(3));
		assertEquals(
				"Lote [Quantidade=5, Validade=" + LocalDate.now().plusDays(3)
						+ ", Produto [identificador=1234567890123, nome=Caneta, desc=Esferográfica, preco=2.5]]\n"
						+ "Lote [Quantidade=10, Validade=" + LocalDate.now().plusDays(3)
						+ ", Produto [identificador=1234567890124, nome=Geladeira, desc=Eletrolux, preco=20000.5]]\n",
				estoque.impressaoTextual());
	}

	@Test
	void CT45impressaoCSV() {
		Estoque estoque = new Estoque();
		estoque.cadastrarProduto(new Produto("1234567890123", "Caneta", "Esferográfica", 2.50));
		estoque.cadastrarProduto(new Produto("1234567890124", "Geladeira", "Eletrolux", 20000.50));
		estoque.adicionar("1234567890123", 5, LocalDate.now().plusDays(3));
		estoque.adicionar("1234567890124", 10, LocalDate.now().plusDays(3));
		assertEquals("Quantidade,Validade,Identificador,Nome,Descrição,Preço\n" + "5," + LocalDate.now().plusDays(3)
				+ ",1234567890123,Caneta,Esferográfica,2.5\n" + "10," + LocalDate.now().plusDays(3)
				+ ",1234567890124,Geladeira,Eletrolux,20000.5\n", estoque.impressaoCSV());
	}

	@Test
	void CT46impressaoJSON() {
		Estoque estoque = new Estoque();
		estoque.cadastrarProduto(new Produto("1234567890123", "Caneta", "Esferográfica", 2.50));
		estoque.cadastrarProduto(new Produto("1234567890124", "Geladeira", "Eletrolux", 20000.50));
		estoque.adicionar("1234567890123", 5, LocalDate.now().plusDays(3));
		estoque.adicionar("1234567890124", 10, LocalDate.now().plusDays(3));
		assertEquals("{\n" +
				" {\n"+
			    "  \"Quantidade\": 5,\n" +
			    "  \"Validade\": \"" + LocalDate.now().plusDays(3) + "\",\n" +
			    "  \"Produto\": {\n" +
			    "    \"Identificador\": \"1234567890123\",\n" +
			    "    \"Nome\": \"Caneta\",\n" +
			    "    \"Descrição\": \"Esferográfica\",\n" +
			    "    \"Preço\": 2.5\n" +
			    "  }\n" +
			    " },\n" +
			    " {\n"+
			    "  \"Quantidade\": 10,\n" +
			    "  \"Validade\": \"" + LocalDate.now().plusDays(3) + "\",\n" +
			    "  \"Produto\": {\n" +
			    "    \"Identificador\": \"1234567890124\",\n" +
			    "    \"Nome\": \"Geladeira\",\n" +
			    "    \"Descrição\": \"Eletrolux\",\n" +
			    "    \"Preço\": 20000.5\n" +
			    "  }\n" +
			    " }\n"+
			    "}", estoque.impressaoJSON());

	}
}
