package consultonibus.gui;

import consultaonibus.Linha;
import consultaonibus.Parada;
import consultaonibus.consultas.Consultas;

import javax.swing.*;
import java.util.ArrayList;

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


		Consultas c = new Consultas();
		ArrayList<Linha> linhas = c.getLinhasByParadas(this.paradas);
		String[][] data = new String[linhas.size()][5];

		for(int i = 1; i < linhas.size(); i++){
			String[] l = {linhas.get(i).getId(), linhas.get(i).getNome(), linhas.get(i).getCodigo(), linhas.get(i).getTipo(),};
			data[i-1] = l;
		}
		//Cria a tabela
		JTable tabela = new JTable(data, columnNames);

		barraRolagem = new JScrollPane(tabela);
		painel.add(barraRolagem);
		painel.add(panelActions);

		return painel;
	}

}
