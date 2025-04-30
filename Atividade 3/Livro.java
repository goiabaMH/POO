package bibliotheca;

import java.util.Objects;

public class Livro {
	private int id;
	private String titulo;
	private String autor;
	private String categoria;
	private boolean status = false;
	
	public Livro(int id, String titulo, String autor, String categoria, boolean status) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.categoria = categoria;
		this.status = status;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getId() {
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
	public int hashCode() {
		return Objects.hash(autor, categoria, titulo);
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
				&& Objects.equals(titulo, other.titulo);
	}
	
	
	
}
