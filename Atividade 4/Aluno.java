package bibliotheca;

import java.util.Comparator;

public class Aluno extends Usuario implements Comparable {
	private int qtdEmprestimos = 0;
	private int multa = 0;

	public Aluno(String id, String nome, String email, String senha) {
		super(id, nome, email, senha);
	}

	public Aluno(String id, String nome, String email, String senha, int qtdEmprestimos, int multa) {
		super(id, nome, email, senha);
		this.qtdEmprestimos = qtdEmprestimos;
		this.multa = multa;
	}

	public Aluno(String id, String nome) {
		super(id, nome, "", "");
	}

	public int getqtdEmprestimos() {
		return qtdEmprestimos;
	}

	public void setqtdEmprestimos(int emprestimos) {
		this.qtdEmprestimos = emprestimos;
	}

	public int getMulta() {
		return multa;
	}

	public void setMulta(int multa) {
		this.multa = multa;
	}

	private static void buscarLivro() {

	}

	public void emprestar(Livro livro) {

	}

	public void renovar(Livro livro) {

	}

	public void devolver(Livro livro) {

	}

	public static Comparator<Aluno> comparadorNome() {
		return null;
	}

	@Override
	public int compareTo(Object o) {
		Aluno aluno = (Aluno) o;
		if (this.getId().compareTo(aluno.getId()) > 0) {
			return 1;
		} else if (this.getId().compareTo(aluno.getId()) < 0) {
			return -1;
		} else {
			return 0;
		}
	}

}
