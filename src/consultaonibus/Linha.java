package consultaonibus;

import java.util.ArrayList;

import consultaonibus.reader.Reader;

public class Linha {
	
	private String id;
	private String nome;
	private String codigo;
	private String tipo;
	
	public Linha(String id, String nome, String codigo, String tipo) {
		this.id = id;
		this.nome = nome;
		this.codigo = codigo;
		this.tipo = tipo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Linha [id=" + id + ", nome=" + nome + ", codigo=" + codigo + ", tipo=" + tipo + "]";
	}
}