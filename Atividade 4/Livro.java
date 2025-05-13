package bibliotheca;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

public class Livro {
	private String id;
	private String titulo;
	private String autor;
	private String categoria;
	private LocalDate publicacao;
	private boolean status = false;
	
	public Livro(String id, String titulo, String autor, String categoria, boolean status) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.categoria = categoria;
		this.status = status;
	}
	
	public Livro(String id, String titulo, LocalDate publicacao) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.publicacao = publicacao;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getCategoria() {
		return categoria;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", categoria=" + categoria + ", status="
				+ status + "]";
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(categoria, other.categoria)
				&& Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo);
	}
	
	public static Comparator<Livro> dataPublicacao() {
		return null;
	}
	
	
	
	
}
