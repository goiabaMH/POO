package bibliotheca;

import java.time.LocalDate;

public class Emprestimo {
	private int idEmprestimo;
	private int idAluno;
	private int idLivro;
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucao;
	private boolean situacao = false;
	
	public Emprestimo(int idEmprestimo, int idAluno, int idLivro, LocalDate dataEmprestimo, LocalDate dataDevolucao, boolean situacao) {
		super();
		this.idEmprestimo = idEmprestimo;
		this.idAluno = idAluno;
		this.idLivro = idLivro;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
		this.situacao = situacao;
	}
	
	public int getIdEmprestimo() {
		return idEmprestimo;
	}
	
	public int getIdAluno() {
		return idAluno;
	}
	
	public int getIdLivro() {
		return idLivro;
	}
	
	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}
	
	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}
	
	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	public boolean getSiutacao() {
		return situacao;
	}

	public void setSiutacao(boolean siutacao) {
		this.situacao = siutacao;
	}

	@Override
	public String toString() {
		return "Emprestimo [idAluno=" + idAluno + ", idLivro=" + idLivro + ", dataEmprestimo=" + dataEmprestimo
				+ ", dataDevolucao=" + dataDevolucao + "]";
	}
	
}
