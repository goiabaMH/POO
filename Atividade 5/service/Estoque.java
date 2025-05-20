package br.ifs.tdd.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.ifs.tdd.exception.EstoqueInsuficienteException;
import br.ifs.tdd.exception.OperacaoInvalidaException;
import br.ifs.tdd.exception.ProdutoDuplicadoException;
import br.ifs.tdd.exception.ProdutoNaoEncontradoException;
import br.ifs.tdd.exception.ValidationException;
import br.ifs.tdd.model.Lote;
import br.ifs.tdd.model.Produto;

public class Estoque {
	private List<Produto> produtos;
	private List<Lote> lotes;

	public Estoque() {
		this.lotes = new ArrayList<>();
		this.produtos = new ArrayList<>();

	}

	public List<Lote> getLotes() {
		return lotes;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void cadastrarProduto(Produto produto) {
		verificarProdutoNulo(produto);
		produtoDuplicado(produto);
		produtos.add(produto);
	}

//	public void cadastrarProduto(String identificador, String nome, String desc, double preco) {
//		Produto produto = new Produto(identificador, nome, desc, preco);
//		verificarProdutoNulo(produto);
//		produtoDuplicado(produto);
//		produtos.add(produto);
//	}

	private void verificarProdutoNulo(Produto produto) {
		if (produto == null) {
			throw new ValidationException("Produto inválido");
		}
	}

	private void produtoDuplicado(Produto produto) {
		if (produtos.contains(produto)) {
			throw new ProdutoDuplicadoException("Produto duplicado");
		}
	}

	private Produto buscarProduto(String identificador) {
		if (identificador == null) {
			throw new ValidationException("Identificador de produto inválido");
		}
		for (Produto produto : produtos) {
			if (produto.getIdentificador().equals(identificador)) {
				return produto;
			}
		}
		throw new ProdutoNaoEncontradoException("Produto não existe");
	}

	public void removerProduto(String identificador) {
		verificarProdutoLote(identificador);
		produtos.remove(buscarProduto(identificador));
	}

	private void verificarProdutoLote(String identificador) {
		for (Lote lote : lotes) {
			if (lote.getProduto().getIdentificador().equals(identificador)) {
				throw new OperacaoInvalidaException("Remova os lotes associados primeiro");
			}
		}
	}

	public void cadastrarLote(Lote lote) {
		lotes.add(lote);
	}

	public String buscar(String identificador) {
		return buscarProduto(identificador).toString();
	}

	public void atualizarProduto(Produto produto) {
		atualizarNulo(produto);
		atualizarProdutoSemCadastro(produto);
		produtos.add(produto);
	}

	private void atualizarNulo(Produto produto) {
		if (produto == null) {
			throw new ValidationException("Produto inválido");
		}
	}

	private void atualizarProdutoSemCadastro(Produto produto) {
		if (!(produtos.contains(produto))) {
			throw new ProdutoNaoEncontradoException("Produto não existe");
		}
	}

	public void adicionar(String identificador, int quantidade, LocalDate dataValidade) {
		lotes.add(new Lote(buscarProduto(identificador), quantidade, dataValidade));
	}

	public void retirar(String identificador, int quantidade) {
		int elemento = 0;
		if (identificador == null) {
			throw new ValidationException("Identificador de produto inválido");
		}
		if (quantidade < 0) {
			throw new ValidationException("Quantidade inválida");
		}
		for (Lote lote : lotes) {
			if (lote.getProduto().getIdentificador().equals(identificador) && lote.getQuantidade() >= quantidade) {
				lote.diminuirQuantidade(quantidade);
				return;
			}
			if (lote.getProduto().getIdentificador().equals(identificador) && lote.getQuantidade() < quantidade) {
				quantidade = quantidade - lote.getQuantidade();
				lote.diminuirQuantidade(lote.getQuantidade());
				elemento += 1;
			}
		}

		if (quantidade > 0 && elemento > 0) {
			throw new EstoqueInsuficienteException("Estoque insuficiente");
		}

		throw new ProdutoNaoEncontradoException("Produto não existe");

	}

	public int obterQuantidade() {
		int qnt = 0;
		for (Lote lote : lotes) {
			qnt += lote.getQuantidade();
		}
		return qnt;
	}

	public int obterQuantidadeProduto(String identificador) {
		return qtdProdutoSemCadastro(identificador);
	}

	private int qtdProdutoSemCadastro(String identificador) {
		if(identificador == null) {
			throw new ValidationException("Identificador de produto inválido");
		}
		for (Lote lote:lotes) {
			if(lote.getProduto().getIdentificador().equals(identificador)) {
				return lote.getQuantidade();
			}
		}
		throw new ProdutoNaoEncontradoException("Produto não existe");
	}
	
	public String impressaoTextual() {
		StringBuilder texto = new StringBuilder();
		for(Lote lote:lotes) {
			texto.append(lote + "\n");
		}
		return texto.toString();
	}
	
	public String impressaoCSV() {
		StringBuilder texto = new StringBuilder();
		texto.append("Quantidade,Validade,Identificador,Nome,Descrição,Preço\n");
		for(Lote lote:lotes) {
			texto.append(lote.getQuantidade()+","+lote.getDataValidade()+","+lote.getProduto().getIdentificador()+","+lote.getProduto().getNome()+","+lote.getProduto().getDesc()+","+lote.getProduto().getPreco()+"\n");
		}
		return texto.toString();
	}
	
	public String impressaoJSON() {
		StringBuilder texto = new StringBuilder();
		int x = 1;
		texto.append("{\n");
		for(Lote lote:lotes) {
			if(x == lotes.size()) {
				texto.append(" {\n"+
					    "  \"Quantidade\": "+lote.getQuantidade()+",\n" +
					    "  \"Validade\": \""+lote.getDataValidade()+"\",\n" +
					    "  \"Produto\": {\n" +
					    "    \"Identificador\": \""+lote.getProduto().getIdentificador()+"\",\n" +
					    "    \"Nome\": \""+lote.getProduto().getNome()+"\",\n" +
					    "    \"Descrição\": \""+lote.getProduto().getDesc()+"\",\n" +
					    "    \"Preço\": "+lote.getProduto().getPreco()+"\n" +
					    "  }\n" +
					    " }\n");
			}else{
				texto.append(" {\n"+
					    "  \"Quantidade\": "+lote.getQuantidade()+",\n" +
					    "  \"Validade\": \""+lote.getDataValidade()+"\",\n" +
					    "  \"Produto\": {\n" +
					    "    \"Identificador\": \""+lote.getProduto().getIdentificador()+"\",\n" +
					    "    \"Nome\": \""+lote.getProduto().getNome()+"\",\n" +
					    "    \"Descrição\": \""+lote.getProduto().getDesc()+"\",\n" +
					    "    \"Preço\": "+lote.getProduto().getPreco()+"\n" +
					    "  }\n" +
					    " },\n");
			x+=1;
			}
			
		}
		texto.append("}");
		return texto.toString();
	}
}
