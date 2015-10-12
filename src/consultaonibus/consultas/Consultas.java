package consultaonibus.consultas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import consultaonibus.reader.Reader;

public class Consultas {
	
	
	public Consultas(){
		
	}
	
	public ArrayList getLinhasByParada(String parada){
		Reader r = new Reader();
		ArrayList<String[]> paradasLinhas = r.readCsv("paradalinha.csv", ";");
		ArrayList<String[]> linhas = r.readCsv("linhas.csv", ";");
		ArrayList<String[]> retorno = new ArrayList();
		
		for(int i=0; i < paradasLinhas.size(); i++){
			String[] aux = paradasLinhas.get(i);

			if(aux[1].equals(parada)){
				for(int j=0; j < linhas.size(); j++){
					String[] aux2 = linhas.get(j);
					if(aux2[0].equals(aux[0])){
						retorno.add(aux2);
					}
				}
			}
		}
		
		return retorno;
	}
	
	public ArrayList getParadasByLinha(String linha){
		Reader r = new Reader();
		ArrayList<String[]> paradasLinhas = r.readCsv("paradalinha.csv", ";");
		ArrayList<String[]> paradas = r.readCsv("paradas.csv", ";");
		ArrayList<String[]> retorno = new ArrayList();
		
		for(int i=0; i < paradasLinhas.size(); i++){
			String[] aux = paradasLinhas.get(i);

			if(aux[0].equals(linha)){
				System.out.println(aux[0]+"->"+aux[1]);
				for(int j=0; j < paradas.size(); j++){
					String[] aux2 = paradas.get(j);
					if(aux2[0].equals(aux[1])){
						retorno.add(aux2);
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
		ArrayList<String[]> retorno = new ArrayList();
		
		for(int i=0; i < paradasLinhas.size(); i++){
			String[] aux = paradasLinhas.get(i);

			for(int k=0; k < paradas.size(); k++){
				if(aux[1].equals(paradas.get(k))){
					for(int j=0; j < linhas.size(); j++){
						String[] aux2 = linhas.get(j);
						if(aux2[0].equals(aux[0])){
							retorno.add(aux2);
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
		ArrayList<String[]> retorno = new ArrayList();
		
		//headers
		retorno.add(linhas.get(0));
		
		//dados
		switch(tipo){
			case "onibus":
				for(int i=0; i < linhas.size(); i++){
					String[] aux = linhas.get(i);
					if(aux[3].equals("\"O\"")){
						retorno.add(linhas.get(i));
					}
				}
			break;
			case "lotacao":
				for(int i=0; i < linhas.size(); i++){
					String[] aux = linhas.get(i);
					if(aux[3].equals("\"L\"")){
						retorno.add(linhas.get(i));
					}
				}
			break;
		}
		
		return retorno;
	}
	
	/*public ArrayList getParadaProxima(String lat, String lgt){
		//ArrayList<String[]> paradas = r.readCsv("paradas.csv", ";");
	}*/
}