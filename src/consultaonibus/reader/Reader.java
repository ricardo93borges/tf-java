package consultaonibus.reader;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
	
	public Reader(){
		
	}
	
	/*public ArrayList readCsv(String arquivo, String delimitador){
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
	}*/
	
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

	public ArrayList readCsv(String arquivo, String delimitador){
		ArrayList<String[]> matriz = new ArrayList();
		try{
			FileReader arq = new FileReader(arquivo);
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine();

			while ((linha = lerArq.readLine()) != null){
				String[] line = linha.split(delimitador);
				if (arquivo == "paradas.csv"){
					StringBuilder lat = new StringBuilder(line[2]);
					StringBuilder lng = new StringBuilder(line[3]);

					lat.setCharAt(3, '.');
					lng.setCharAt(3, '.');

					line[2] = lat.toString();
					line[3] = lng.toString();
				}
				matriz.add(line);
			}

		} catch (IOException e) {
			System.out.println("NÃO ENCONTREI O ARQUIVO!.");
		}
		return matriz;
	}
}
