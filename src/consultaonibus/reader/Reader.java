package consultaonibus.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
	
	public Reader(){
		
	}
	
	public ArrayList readCsv(String arquivo, String delimitador){
		ArrayList<String[]> matriz = new ArrayList();
		try{
			Scanner scanner = new Scanner(new File(arquivo));
	        scanner.useDelimiter("\n");
	        
	        while (scanner.hasNext()){
	        	String[] line = scanner.next().split(delimitador);
	        	matriz.add(line);
	        }
	        scanner.close();

		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		return matriz;
	}
	
	public void writeCsv(String arquivo, ArrayList conteudo, String delimitador){
		try(PrintWriter writer = new PrintWriter(new FileWriter(arquivo, true))){
			for (int i=0; i<conteudo.size(); i++) {
				writer.print(conteudo.get(i));
				writer.print(';');
			}
			writer.print("\n");
		} catch (IOException e) {
			System.err.format("Erro de E/S: %s%n", e);
		}
	}
}
