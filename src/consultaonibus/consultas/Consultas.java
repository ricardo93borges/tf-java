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
	
	public ArrayList getLinhasByParadas(ArrayList<String> paradas){
		Reader r = new Reader();
		ArrayList<String[]> paradasLinhas = r.readCsv("paradalinha.csv", ";");
		ArrayList<String[]> linhas = r.readCsv("linhas.csv", ";");
		ArrayList<Linha> retorno = new ArrayList<Linha>();
		
		for(int i=0; i < paradasLinhas.size(); i++){
			String[] aux = paradasLinhas.get(i);

			for(int k=0; k < paradas.size(); k++){
				if(aux[1].equals(paradas.get(k))){
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

}