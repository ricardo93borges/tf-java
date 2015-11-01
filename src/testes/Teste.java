package testes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import consultaonibus.ConsultaOnibus;
import consultaonibus.consultas.Consultas;
import consultaonibus.Linha;
import consultaonibus.Parada;

public class Teste {
	public static void main(String[] args){
		//getLinhasByParada("124");
		getParadasByLinha("127621");
	}
	
	public static void getLinhasByParada(String parada){
		Consultas c = new Consultas();
		ArrayList<Linha> linhas  = c.getLinhasByParada(parada);
		
		for(int i=0; i < linhas.size(); i++){
			System.out.println("\n Linha:");
			System.out.println("ID: "+linhas.get(i).toString());
		}
	}
	
	public static void getParadasByLinha(String parada){
		Consultas c = new Consultas();
		ArrayList<Parada> paradas  = c.getParadasByLinha(parada);
		for(int i=0; i < paradas.size(); i++){
			System.out.println("\n Parada:");
			System.out.println(paradas.get(i).toString());
		}
	}
	
	
}
