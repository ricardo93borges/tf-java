package testes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import consultaonibus.ConsultaOnibus;
import consultaonibus.consultas.Consultas;

public class Teste {
	public static void main(String[] args){
		
		Consultas c = new Consultas();
		//ArrayList<String[]> linhas  = c.getLinhasByParada("124");
		//ArrayList<String[]> paradas = c.getParadasByLinha("128018");
		ArrayList<String> pdas = new ArrayList();
		pdas.add("124");
		pdas.add("127");
		pdas.add("129");
		ArrayList<String[]> linhas2  = c.getLinhasByParadas(pdas);
		
		ArrayList<String[]> matriz = linhas2;
		for(int i=0; i < matriz.size(); i++){
			System.out.println(matriz.get(i)[0]+"|"+matriz.get(i)[1]);
		}
	}
	
	
}
