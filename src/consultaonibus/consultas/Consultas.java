package consultaonibus.consultas;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Set;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import consultaonibus.Linha;
import consultaonibus.Parada;
import consultaonibus.Util;
import consultaonibus.reader.Reader;
import consultonibus.gui.MyWaypoint;

public class Consultas {
	
	
	public Consultas(){
		
	}
	
	/*
	 * Retorna as linhas com suas respectivas paradas
	 */
	public ArrayList getLinhas(){
		Reader r = new Reader();
		ArrayList<String[]> paradas = r.readCsv("paradas.csv", ";");
		ArrayList<String[]> paradasLinhas = r.readCsv("paradalinha.csv", ";");
		ArrayList<String[]> linhas = r.readCsv("linhas.csv", ";");
		
		ArrayList<Linha> retorno = new ArrayList<Linha>();
		int linhasSize = linhas.size();
		
		for(int j=1; j < linhas.size(); j++){
			System.out.println("Carregando linhas: "+j+"/"+linhasSize);
			String[] aux2 = linhas.get(j);
			ArrayList<Parada> arrayparadas = this.getParadasDaLinha(aux2[0], paradas, paradasLinhas);
			retorno.add(new Linha(Integer.parseInt(aux2[0]), aux2[1], aux2[2], aux2[3], arrayparadas));
		}
		
		return retorno;
	}

	/*
	 * Retorna todas as paradas de uma linha
	 */
	public ArrayList getParadasDaLinha(String linha, ArrayList<String[]> paradas, ArrayList<String[]> paradasLinhas){		
		ArrayList<Parada> retorno = new ArrayList<Parada>();
		
		for(int i=0; i < paradasLinhas.size(); i++){
			String[] aux = paradasLinhas.get(i);
			if(aux[0].equals(linha)){
				//get parada
				for(int j=0; j < paradas.size(); j++){
					String[] aux2 = paradas.get(j);
					if(aux[1].equals(aux2[0])){
						String lng = Util.substituir(aux2[2], ",", ".");
						String lat = Util.substituir(aux2[3], ",", ".");
						retorno.add(new Parada(Integer.parseInt(aux2[0]), aux2[1], Double.parseDouble(lng), Double.parseDouble(lat), aux2[4], new ArrayList<Linha>()));
						break;
					}
				}
			}
		}
		return retorno;
	}
	
	public ArrayList getLinhasByParada(String parada, ArrayList<String[]> paradasLinhas, ArrayList<String[]> linhas){
		ArrayList<Linha> retorno = new ArrayList<Linha>();
		for(int i=0; i < paradasLinhas.size(); i++){
			String[] aux = paradasLinhas.get(i);
			if(aux[1].equals(parada)){
				for(int j=1; j < linhas.size(); j++){
					String[] aux2 = linhas.get(j);
					if(aux2[0].equals(aux[0])){
						ArrayList<Parada> paradas = new ArrayList<Parada>();//.getParadasByLinha(aux2[0]);
						retorno.add(new Linha(Integer.parseInt(aux2[0]), aux2[1], aux2[2], aux2[3], paradas));
					}
				}
			}
		}
		return retorno;
	}

	/*
	 * Retorna todas as paradas
	 */
	public ArrayList getParadas(){
		Reader r = new Reader();
		ArrayList<String[]> paradas = r.readCsv("paradas.csv", ";");
		ArrayList<String[]> paradasLinhas = r.readCsv("paradalinha.csv", ";");
		ArrayList<String[]> linhas = r.readCsv("linhas.csv", ";");
		
		ArrayList<Parada> retorno = new ArrayList<Parada>();
		int paradasSize = paradas.size();
		
		for(int j=1; j < paradas.size(); j++){
			System.out.println("Carregando paradas: "+j+"/"+paradasSize);
			String[] aux2 = paradas.get(j);
			ArrayList<Linha> arrayLinhas = this.getLinhasByParada(aux2[0], paradasLinhas, linhas);
			String lng = Util.substituir(aux2[2], ",", ".");
			String lat = Util.substituir(aux2[3], ",", ".");
			retorno.add(new Parada(Integer.parseInt(aux2[0]), aux2[1], Double.parseDouble(lng), Double.parseDouble(lat), aux2[4], arrayLinhas));
		}
		
		return retorno;
	}
	
	public ArrayList getParadasByLinha(int linha, ArrayList<Parada> paradas){
		ArrayList<Parada> retorno = new ArrayList<Parada>();
		for(int i=0; i < paradas.size(); i++){
			for(int j=0; j < paradas.get(i).getLinhas().size(); j++){
				if(paradas.get(i).getLinhas().get(j).getId() == linha){
					retorno.add(paradas.get(i));
				}
			}
		}

		return retorno;
	}

	public MyWaypoint getParadaProxima(GeoPosition gp, Set<MyWaypoint> waypoints, JXMapViewer mapa){
		//Converte o ponto clicado para um objeto com coordenadas em pixel
		Point2D pointClicked = mapa.getTileFactory().geoToPixel(gp, mapa.getZoom());
		MyWaypoint pontoProx = null;
		double menorX = 0;
		double menorY = 0;

		//Inicia as variaveis
		for(MyWaypoint ponto : waypoints){
			Point2D pointWp = mapa.getTileFactory().geoToPixel(ponto.getPosition(), mapa.getZoom());
			menorX = Math.abs(pointClicked.getX()-pointWp.getX());
			menorY = Math.abs(pointClicked.getY()-pointWp.getY());
			break;
		}

		//System.out.println("Menor X inicial:"+menorX);
		//System.out.println("Menor Y inicial:"+menorY);

		//Busca pelo waypoint mais proximo do ponto clicado
		for(MyWaypoint ponto : waypoints){
			Point2D pointWp = mapa.getTileFactory().geoToPixel(ponto.getPosition(), mapa.getZoom());
			double calcX = Math.abs(pointClicked.getX()-pointWp.getX());
			double calcY = Math.abs(pointClicked.getY()-pointWp.getY());
			if(calcX < menorX && calcY < menorY){
				menorX = calcX;
				menorY = calcY;
				//System.out.println("calcX:"+calcX);
				//System.out.println("calcX:"+calcY);
				pontoProx = ponto;
			}
		}

		return pontoProx;
	}

	private double calculaDistancia(double lat1, double lng1, double lat2, double lng2) {
		//double earthRadius = 3958.75;//miles
		double earthRadius = 6371;//kilometers
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
				* Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = earthRadius * c;

		return dist * 1000; //em metros
	}

	public ArrayList<Parada> getParadasNumRaio(double latitude, double longetitude, int raio, ArrayList<Parada> paradas){
		double dist = 0.0;
		ArrayList<Integer> id_paradasNumRaio = new ArrayList<>();

		for(int i=1; i<paradas.size(); i++){
			double pLat = paradas.get(i).getLatitude();
			double pLng = paradas.get(i).getLongitude();

			dist = this.calculaDistancia(pLat, pLng, latitude, longetitude);

			if (dist <= raio) id_paradasNumRaio.add(paradas.get(i).getId());
		}

		ArrayList<String> id_linhasNumRaio = new ArrayList<>();

		ArrayList<Parada> pdas = new ArrayList<Parada>();

		for(int i=0; i < paradas.size(); i++){
			if(id_paradasNumRaio.contains(paradas.get(i).getId())){
				pdas.add(paradas.get(i));
			}
		}

		return pdas;
	}
}