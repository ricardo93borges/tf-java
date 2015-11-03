package consultaonibus;

import java.util.ArrayList;

import consultaonibus.reader.Reader;

public class Parada {
	
	private String id;
	private String codigo;
	private String longitude;
	private String latitude;
	private String terminal;
	
	public Parada(String id, String codigo, String longitude, String latitude, String terminal) {
		this.id = id;
		this.codigo = codigo;
		this.longitude = longitude;
		this.latitude = latitude;
		this.terminal = terminal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	
	public void addParada(){
		Reader r = new Reader();
		ArrayList conteudo = new ArrayList();
		
		conteudo.add(this.id);
		conteudo.add(this.codigo);
		conteudo.add(this.longitude);
		conteudo.add(this.latitude);
		conteudo.add(this.terminal);

		r.writeCsv("paradas.csv", conteudo, ";");
	}
	
	public void relacionaLinhaParada(String idLinha, String idParada){
		Reader r = new Reader();
		ArrayList conteudo = new ArrayList();
		
		conteudo.add(idLinha);
		conteudo.add(idParada);

		r.writeCsv("paradalinha.csv", conteudo, ";");
	}

	public static void ordenar(ArrayList<Parada> objetos){
		Parada aux;
		
		objetos.remove(0);//remover headers
		for(int i=0; i < objetos.size(); i ++){
			for(int j=0; j < objetos.size()-1; j++){
				if(Integer.parseInt(objetos.get(j).getId()) > Integer.parseInt(objetos.get(j+1).getId())){
					aux = objetos.get(j);
					objetos.set(j, objetos.get(j+1));
					objetos.set(j+1, aux);
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return "Parada [id=" + id + ", codigo=" + codigo + ", longitude="
				+ longitude + ", latitude=" + latitude + ", terminal="
				+ terminal + "]";
	}
}