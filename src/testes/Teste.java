package testes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import consultaonibus.ConsultaOnibus;
import consultaonibus.consultas.Consultas;
import consultaonibus.reader.Reader;
import consultaonibus.Linha;
import consultaonibus.Parada;

public class Teste {
	public static void main(String[] args){
		//getLinhasByParada("124");
		//getParadasByLinha("127621");
		write();
	}
	

	
	public static void write(){
		ArrayList<String> conteudo = new ArrayList<String>();
		conteudo.add("teste");
		conteudo.add("wirite");
		Reader r = new Reader();
		r.writeCsv("teste.csv", conteudo, ";");
	}
	
	
}
