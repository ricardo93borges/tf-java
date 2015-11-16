package consultaonibus.consultas;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;
import java.util.*;

import consultaonibus.Util;
import consultaonibus.reader.Reader;
import consultaonibus.Linha;
import consultaonibus.Parada;
import consultaonibus.ParadaLinha;
import consultonibus.gui.MyWaypoint;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;

public class Consultas {
	
	
	public Consultas(){
		
	}
	
	/*
	 * Retorna todas as linhas
	 */
	public ArrayList getLinhas(){
		Reader r = new Reader();
		ArrayList<String[]> linhas = r.readCsv("linhas.csv", ";");
		ArrayList<Linha> retorno = new ArrayList<Linha>();
		
		for(int j=0; j < linhas.size(); j++){
			String[] aux2 = linhas.get(j);
			retorno.add(new Linha(aux2[0], aux2[1], aux2[2], aux2[3]));
		}
	
		return retorno;
	}
	
	/*
	 * retorna um ArrayList de objetos Linha
	 */
	public ArrayList getLinhasByParada(String parada){
		Reader r = new Reader();
		ArrayList<String[]> paradasLinhas = r.readCsv("paradalinha.csv", ";");
		ArrayList<String[]> linhas = r.readCsv("linhas.csv", ";");
		ArrayList<Linha> retorno = new ArrayList<Linha>();
		
		for(int i=0; i < paradasLinhas.size(); i++){
			String[] aux = paradasLinhas.get(i);
			if(aux[1].equals(parada)){
				for(int j=0; j < linhas.size(); j++){
					String[] aux2 = linhas.get(j);
					if(aux2[0].equals(aux[0])){
						retorno.add(new Linha(aux2[0], aux2[1], aux2[2], aux2[3]));
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
		ArrayList<Parada> retorno = new ArrayList<Parada>();
		
		for(int j=0; j < paradas.size(); j++){
			String[] aux2 = paradas.get(j);
			retorno.add(new Parada(aux2[0], aux2[1], aux2[2], aux2[3], aux2[4]));
		}
		
		return retorno;
	}

	/*
	 * Retorna todas as paradas
	 */
	public ArrayList getParadaLinha(){
		Reader r = new Reader();
		ArrayList<String[]> paradaLinhas = r.readCsv("paradalinha.csv", ";");
		ArrayList<ParadaLinha> retorno = new ArrayList<>();

		for(int j=0; j < paradaLinhas.size(); j++){
			String[] aux2 = paradaLinhas.get(j);
			retorno.add(new ParadaLinha(aux2[0], aux2[1]));
		}

		return retorno;
	}

	public ArrayList getParadasByLinha(String linha){
		Reader r = new Reader();
		ArrayList<String[]> paradasLinhas = r.readCsv("paradalinha.csv", ";");
		ArrayList<String[]> paradas = r.readCsv("paradas.csv", ";");
		ArrayList<Parada> retorno = new ArrayList<Parada>();
		
		for(int i=0; i < paradasLinhas.size(); i++){
			String[] aux = paradasLinhas.get(i);

			if(aux[0].equals(linha)){
				for(int j=0; j < paradas.size(); j++){
					String[] aux2 = paradas.get(j);
					if(aux2[0].equals(aux[1])){
						retorno.add(new Parada(aux2[0], aux2[1], aux2[2], aux2[3], aux2[4]));
					}
				}
			}
		}
		
		return retorno;
	}
	
	public ArrayList getLinhasByParadas(ArrayList<Parada> paradas){
		Reader r = new Reader();
		ArrayList<String[]> paradasLinhas = r.readCsv("paradalinha.csv", ";");
		ArrayList<String[]> linhas = r.readCsv("linhas.csv", ";");
		ArrayList<Linha> retorno = new ArrayList<Linha>();
		
		for(int i=0; i < paradasLinhas.size(); i++){
			String[] aux = paradasLinhas.get(i);

			for(int k=0; k < paradas.size(); k++){
				if(aux[1].equals(paradas.get(k).getId())){
					for(int j=0; j < linhas.size(); j++){
						String[] aux2 = linhas.get(j);
						if(aux2[0].equals(aux[0])){
							retorno.add(new Linha(aux2[0], aux2[1], aux2[2], aux2[3]));
						}
					}
				}
			}
			
		}
		return retorno;
	}
	
	public ArrayList getLinhas(String tipo){
		Reader r = new Reader();

		ArrayList<String[]> linhas = r.readCsv("linhas.csv", ";");
		ArrayList<Linha> retorno = new ArrayList<Linha>();
		
		//dados
		switch(tipo){
			case "onibus":
				for(int i=0; i < linhas.size(); i++){
					String[] aux = linhas.get(i);
					if(aux[3].equals("\"O\"")){
						retorno.add(new Linha(linhas.get(i)[0], linhas.get(i)[1], linhas.get(i)[2], linhas.get(i)[3]));
					}
				}
			break;
			case "lotacao":
				for(int i=0; i < linhas.size(); i++){
					String[] aux = linhas.get(i);
					if(aux[3].equals("\"L\"")){
						retorno.add(new Linha(linhas.get(i)[0], linhas.get(i)[1], linhas.get(i)[2], linhas.get(i)[3]));
					}
				}
			break;
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

	public ArrayList<Parada> getParadasNumRaio(double latitude, double longetitude, int raio){

		Reader r = new Reader();
		double dist = 0.0;
		//ArrayList<String[]> paradas = r.readCsv("paradas.csv", ";");
		ArrayList<Parada> paradas = this.getParadas();
		ArrayList<String> id_paradasNumRaio = new ArrayList<>();

		//for (String[] contLinha : paradas) {
		for(int i=1; i<paradas.size(); i++){
			//Parada p = new Parada(contLinha[0], contLinha[1].substring(1, contLinha[1].length()-1), contLinha[2].substring(0, contLinha[2].length()-6), contLinha[3].substring(0,  contLinha[3].length()-6), contLinha[4]);
			String pLat = Util.substituir(paradas.get(i).getLatitude(), ",", ".");
			String pLng = Util.substituir(paradas.get(i).getLongitude(), ",", ".");
			System.out.println(pLat+" : "+pLng);
			dist = this.calculaDistancia(Double.parseDouble(pLat), Double.parseDouble(pLng), latitude, longetitude);

			if (dist <= raio) id_paradasNumRaio.add(paradas.get(i).getId());
		}

		ArrayList<String[]> paradaLinha = r.readCsv("paradalinha.csv", ";");
		ArrayList<String> id_linhasNumRaio = new ArrayList<>();

		for (String[] contLinha: paradaLinha) {
			ParadaLinha l = new ParadaLinha(contLinha[0], contLinha[1]);
			if(id_paradasNumRaio.contains(l.getIdParada())) id_linhasNumRaio.add(l.getIdLinha());
		}

		ArrayList<Parada> pdas = new ArrayList<Parada>();
		ArrayList<Parada> todasParadas = this.getParadas();
		for(int i=0; i < todasParadas.size(); i++){
			if(id_paradasNumRaio.contains(todasParadas.get(i).getId())){
				pdas.add(todasParadas.get(i));
			}
		}

		return pdas;
		/*
		ArrayList<String[]> linhas = r.readCsv("linhas.csv", ";");
		ArrayList<Linha> respLinhas = new ArrayList<Linha>();

		for (String[] contLinha: linhas) {
			Linha l = new Linha(contLinha[0], contLinha[1], contLinha[2], contLinha[3]);
			if(id_linhasNumRaio.contains(l.getId())) respLinhas.add(l);
		}
		*/

	}

}