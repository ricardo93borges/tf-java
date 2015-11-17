package consultaonibus;

import java.util.ArrayList;
import java.util.logging.Level;

import consultaonibus.reader.Reader;

public class Parada {
	
	private int id;
	private String codigo;
	private double longitude;
	private double latitude;
	private String terminal;
	private ArrayList<Linha> linhas;
	
	public Parada(int id, String codigo, double longitude, double latitude, String terminal, ArrayList<Linha> linhas) {
		this.id = id;
		this.codigo = codigo;
		this.longitude = longitude;
		this.latitude = latitude;
		this.terminal = terminal;
		this.linhas = linhas;
	}

	public ArrayList<Linha> getLinhas() {
		return linhas;
	}

	public void setLinhas(ArrayList<Linha> linhas) {
		this.linhas = linhas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
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
	
	public void relacionaLinhaParada(int idLinha, int idParada){
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
		return "Parada [id=" + id + ", codigo=" + codigo + ", longitude="
				+ longitude + ", latitude=" + latitude + ", terminal="
				+ terminal + "]";
	}
}