package bibliotheca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programa {

	public static void main(String[] args) {
		List<Livro> livros = new ArrayList<Livro>();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		List<Aluno> alunos = new ArrayList<Aluno>();
		List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		Scanner s = new Scanner(System.in);
		int idsDeEmprestimo = 0;

		livros.add(new Livro(1, "bom", "dia", "porra", false));
		usuarios.add(new Aluno(1, "va", "tomar", "no", 2, 0));
		usuarios.add(new Bibliotecario(11, "vai", "pra", "casa"));
		usuarios.add(new Administrador(111, "vao", "se", "fude"));
		emprestimos.add(new Emprestimo(1, 2, 3, LocalDate.now(), LocalDate.now().plusDays(3), true));

		login(livros, usuarios, s, emprestimos, alunos, idsDeEmprestimo);

	}

	private static void login(List<Livro> livros, List<Usuario> usuarios, Scanner s, List<Emprestimo> emprestimos,
			List<Aluno> alunos, int idsDeEmprestimo) {
		System.out.println("Digite 0 Para Sair do Sistema, Para Logar Digite Qualquer Outro Número");
		int opcao = s.nextInt();
		while (opcao != 0) {
			System.out.println("Digite seu ID: ");
			int idExemplo = s.nextInt();
			System.out.println("Digite sua Senha: ");
			String senhaExemplo = s.next();
			Usuario usuario = null;
			for (Usuario usuario1 : usuarios) {
				if (usuario1.getId() == idExemplo && usuario1.getSenha().equals(senhaExemplo)) {
					System.out.println("Login realizado com sucesso!");
					usuario = usuario1;
				}
			}
			if (usuario instanceof Aluno) {
				System.out.println("1. Buscar Livros");
				System.out.println("2. Reservar Livro");
				System.out.println("3. Renovar Empréstimos");
				System.out.println("0. Voltar pra Tela de Login");
				int opcao1 = s.nextInt();
				while (opcao1 != 0) {
					if (opcao1 == 1) {
						buscarLivro(s, livros);
					} else if (opcao1 == 2) {
						reservarLivro(s, livros, emprestimos, alunos, idsDeEmprestimo);
					} else if (opcao1 == 3) {
						renovarEmprestimo(s, emprestimos, idsDeEmprestimo);
					} else {
						System.out.println("Opção Inválida");
					}
					System.out.println("1. Buscar Livros");
					System.out.println("2. Reservar Livro");
					System.out.println("3. Renovar Empréstimos");
					System.out.println("0. Voltar pra Tela de Login");
					opcao1 = s.nextInt();
				}
			}
			if (usuario instanceof Bibliotecario) {
				System.out.println("1. Aprovar Empréstimo");
				System.out.println("2. Registrar Devoluções");
				System.out.println("3. Exibir Emprestimos");
				int opcao2 = s.nextInt();
				while (opcao2 != 0) {
					if (opcao2 == 1) {
						aprovarEmprestimo(s, emprestimos, alunos);
					} else if (opcao2 == 2) {
						registrarDevolucao(s, emprestimos, livros, alunos);
					} else if (opcao == 3) {
						exibirEmprestimos(emprestimos);
					} else {
						System.out.println("Opção Inválida");
					}
					System.out.println("1. Aprovar Empréstimo");
					System.out.println("2. Registrar Devoluções");
					System.out.println("3. Exibir Emprestimos");
					opcao2 = s.nextInt();
				}
			}
			if (usuario instanceof Administrador) {
				System.out.println("1. Exibir emprestimos");
				System.out.println("2. Gerenciar Cadastros");
				System.out.println("3. Excluir Cadastros");
				System.out.println("4. Gerar Relatórios");
				int opcao3 = s.nextInt();
				while (opcao3 != 0) {
					if (opcao3 == 1) {
						exibirEmprestimos(emprestimos);
					} else if (opcao3 == 2) {
						gerenciarCadastro(s, alunos);
					} else if (opcao3 == 3) {
						excluirRegistro(s, emprestimos);
					}
					if (opcao3 == 4) {
						gerarRelatorios(livros);
					} else {
						System.out.println("Opção Inválida");
					}
					System.out.println("1. Exibir emprestimos");
					System.out.println("2. Gerenciar Cadastros");
					System.out.println("3. Excluir Cadastros");
					System.out.println("4. Gerar Relatórios");
					opcao3 = s.nextInt();
				}
			}
			if (usuario == null) {
				System.out.println("ID ou Senha Inválidos!");
			}
			System.out.println("Digite 0 Para Sair do Sistema, Para Logar Digite Qualquer Outro Número");
			opcao = s.nextInt();
			}
		
	}

	private static void buscarLivro(Scanner s, List<Livro> livros) {
		System.out.print("Digite Título, Autor ou Categoria: ");
		String LLivro = s.next();
		for (Livro livro : livros) {
			if (livro.getTitulo().equals(LLivro) || livro.getAutor().equals(LLivro)
					|| livro.getCategoria().equals(LLivro)) {
				System.out.println(livro);
			} else {
				System.out.println("Livro não encontrado!");
			}
		}
	}

	private static void reservarLivro(Scanner s, List<Livro> livros, List<Emprestimo> emprestimos, List<Aluno> alunos,
			int idsDeEmprestimo) {
		System.out.print("Digite ID do Aluno: ");
		int idAluno = s.nextInt();
		System.out.print("Digite ID do Livro: ");
		int idLivro = s.nextInt();
		for (Aluno aluno : alunos) {
			for (Livro livro : livros) {
				if (aluno.getId() == idAluno && livro.getId() == idLivro && livro.getStatus() == false) {
					emprestimos.add(new Emprestimo(idsDeEmprestimo++, aluno.getId(), livro.getId(), LocalDate.now(),
							LocalDate.now().plusDays(3), false));
				}
			}
		}
	}

	private static void renovarEmprestimo(Scanner s, List<Emprestimo> emprestimos, int idsDeEmprestimo) {
		System.out.println("Digite o ID do Livro: ");
		int idLivro = s.nextInt();
		for (Emprestimo emprestimo : emprestimos) {
			if (emprestimo.getIdLivro() == idLivro && LocalDate.now().isAfter(emprestimo.getDataDevolucao())) {
				System.out.println("Atraso na Devolução, Renovação não Permitida!");
			}
			if (emprestimo.getIdLivro() == idLivro && LocalDate.now().isBefore(emprestimo.getDataDevolucao())) {
				emprestimo.setDataDevolucao(LocalDate.now().plusDays(3));
				System.out.println("Renovação de Emprestimo Realizada com Sucesso!");
			}
			if (idLivro != emprestimo.getIdLivro()) {
				System.out.println("Emprestimo não Encontrado");
			}
		}
	}

	private static void aprovarEmprestimo(Scanner s, List<Emprestimo> emprestimos, List<Aluno> alunos) {
		System.out.println("Digite o ID do Emprestimo: ");
		int idEmprestimo = s.nextInt();
		for (Emprestimo emprestimo : emprestimos) {
			if (emprestimo.getIdEmprestimo() == idEmprestimo) {
				for (Aluno aluno : alunos) {
					if (aluno.getId() == emprestimo.getIdAluno() && aluno.getqtdEmprestimos() < 5
							&& aluno.getMulta() == 0) {
						emprestimo.setSiutacao(true);
						System.out.println("Emprestimo aprovado!");
					} else {
						System.out.println("Aluno com Pendências");
					}
				}
			}
		}
	}

	private static void registrarDevolucao(Scanner s, List<Emprestimo> emprestimos, List<Livro> livros,
			List<Aluno> alunos) {
		System.out.println("Digite o ID do Aluno: ");
		int idAluno = s.nextInt();
		System.out.println("Digite o ID do Livro: ");
		int idLivro = s.nextInt();
		for (Emprestimo emprestimo : emprestimos) {
			if (emprestimo.getIdAluno() == idAluno && emprestimo.getIdLivro() == idLivro) {
				for (Livro livro : livros) {
					for (Aluno aluno : alunos) {
						if (LocalDate.now().isBefore(emprestimo.getDataDevolucao())) {
							livro.setStatus(false);
							System.out.println("Livro Devolvido");
						}
						if (LocalDate.now().isAfter(emprestimo.getDataDevolucao())) {
							aluno.setMulta(
									(int) (LocalDate.now().toEpochDay() - emprestimo.getDataDevolucao().toEpochDay())
											* 2);
							livro.setStatus(false);
						}
					}
				}
			}
		}
	}

	private static void exibirCadastros(List<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}

	private static void gerenciarCadastro(Scanner s, List<Aluno> alunos) {
		System.out.println("1. Adicionar Novo Aluno");
		System.out.println("2. Remover Cadastro de Aluno");
		int opcao = s.nextInt();
		if (opcao == 1) {
			System.out.println("Digite o Id do Novo Aluno: ");
			int idAluno = s.nextInt();
			System.out.println("Digite o Nome do Aluno: ");
			String nomeAluno = s.next();
			System.out.println("Digite o Email do Aluno: ");
			String emailAluno = s.next();
			System.out.println("Digite a Senha do Aluno: ");
			String senhaAluno = s.next();
			alunos.add(new Aluno(idAluno, nomeAluno, emailAluno, senhaAluno));
		}
	}

	private static void excluirRegistro(Scanner s, List<Emprestimo> emprestimos) {
		System.out.println("Digite o ID do Emprestimo a Ser Excluido");
		int idEmprestimo = s.nextInt();
		for (Emprestimo emprestimo : emprestimos) {
			if (emprestimo.getIdEmprestimo() == idEmprestimo) {
				emprestimos.remove(emprestimo);
			}
		}
	}

	private static void gerarRelatorios(List<Livro> livros) {
		int livroDisponivel = 0;
		int livroIndisponivel = 0;
		for (Livro livro : livros) {
			if (livro.getStatus() == false) {
				livroDisponivel = +1;
			}
			if (livro.getStatus() == true) {
				livroIndisponivel = +1;
			}
			System.out.println("Quantidade de Livros Disponíveis Para Emprestimo: " + livroDisponivel);
			System.out.println("Quantidade de Livros Indisponíveis Para Emprestimo: " + livroIndisponivel);
		}
	}

	private static void exibirEmprestimos(List<Emprestimo> emprestimos) {
		for (Emprestimo emprestimo : emprestimos) {
			System.out.println(emprestimo);
		}
	}

}
