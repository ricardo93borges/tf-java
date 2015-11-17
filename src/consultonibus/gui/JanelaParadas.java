package consultonibus.gui;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import consultaonibus.Linha;
import consultaonibus.Parada;
//import consultaonibus.consultas.Consultas;

public class JanelaParadas extends JFrame{

	private ArrayList<Parada> paradas;

	 public JanelaParadas(ArrayList<Parada> paradas) {
		 this.paradas = paradas;

		 String title = "Linhas das paradas selecionadas";
		 this.setTitle(title);
		 this.add(this.tabelaLinhas());
		 this.pack();
		 this.setVisible(true);
	 }

	private JPanel tabelaLinhas(){
		JPanel painel;
		JScrollPane barraRolagem;
		JPanel panelActions = new JPanel();

		painel = new JPanel();
		painel.setLayout(new BoxLayout(painel, BoxLayout.PAGE_AXIS));

		//Obtem dados para a tabela
		String[] columnNames = {"ID", "Nome", "Codigo", "Tipo"};

		int linhasSize = 0;
		for(int j=0; j < paradas.size(); j++){
			linhasSize += paradas.get(j).getLinhas().size();
		}
		
		String[][] data = new String[linhasSize][5];

		for(int j=0; j < paradas.size(); j++){
			ArrayList<Linha> linhas = paradas.get(j).getLinhas();
			for(int i = 0; i < linhas.size(); i++){
				String[] l = {String.valueOf(linhas.get(i).getId()), linhas.get(i).getNome(), linhas.get(i).getCodigo(), linhas.get(i).getTipo(),};
				data[i] = l;
			}
		}
		//Cria a tabela
		JTable tabela = new JTable(data, columnNames);

		barraRolagem = new JScrollPane(tabela);
		painel.add(barraRolagem);
		painel.add(panelActions);

		return painel;
	}

}