package bibliotheca;

public class Aluno extends Usuario {
	private int qtdEmprestimos = 0;
	private int multa = 0;

	public Aluno(int id, String nome, String email, String senha) {
		super(id, nome, email, senha);
	}
	
	public Aluno(int id, String nome, String email, String senha, int qtdEmprestimos, int multa) {
		super(id, nome, email, senha);
		this.qtdEmprestimos = qtdEmprestimos;
		this.multa = multa;
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
}
