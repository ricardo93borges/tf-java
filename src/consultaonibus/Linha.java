package consultaonibus;

import java.util.ArrayList;

import consultaonibus.reader.Reader;

public class Linha {
	
	private int id;
	private String nome;
	private String codigo;
	private String tipo;
	private ArrayList<Parada> paradas;
	
	public Linha(int id, String nome, String codigo, String tipo, ArrayList<Parada> paradas) {
		this.id = id;
		this.nome = nome;
		this.codigo = codigo;
		this.tipo = tipo;
		this.paradas = paradas;
	}

	public ArrayList<Parada> getParadas() {
		return paradas;
	}

	public void setParadas(ArrayList<Parada> paradas) {
		this.paradas = paradas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public void addLinha(){
		Reader r = new Reader();
		ArrayList conteudo = new ArrayList();
		
		conteudo.add(this.id);
		conteudo.add(this.nome);
		conteudo.add(this.codigo);
		conteudo.add(this.tipo);

		r.writeCsv("linhas.csv", conteudo, ";");
	}
	
	public static void ordenar(ArrayList<Linha> objetos){
		Linha aux;
		
		objetos.remove(0);//remover headers
		for(int i=0; i < objetos.size(); i ++){
			for(int j=0; j < objetos.size()-1; j++){
				if(objetos.get(j).getId() > objetos.get(j+1).getId()){
					aux = objetos.get(j);
					objetos.set(j, objetos.get(j+1));
					objetos.set(j+1, aux);
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return this.nome;
	}
}