package consultonibus.gui;

import consultaonibus.Linha;
import consultaonibus.Parada;
import consultaonibus.consultas.Consultas;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class JanelaParada extends JFrame{

	private Parada parada;

	 public JanelaParada(Parada parada) {
		 this.parada = parada;

		 String title = "Linhas da parada "+parada.getId()+" [Código: "+parada.getCodigo()+", Terminal: "+parada.getTerminal()+"]";
		 this.setTitle(title);
		 this.add(this.tabelaLinhas());
		 this.pack();
		 this.setVisible(true);
	 }

	private javax.swing.JPanel tabelaLinhas(){
		javax.swing.JPanel painel;
		javax.swing.JScrollPane barraRolagem;
		javax.swing.JPanel panelActions = new javax.swing.JPanel();

		painel = new javax.swing.JPanel();
		painel.setLayout(new javax.swing.BoxLayout(painel, javax.swing.BoxLayout.PAGE_AXIS));

		//Obtem dados para a tabela
		String[] columnNames = {"ID", "Nome", "Codigo", "Tipo"};

		System.out.println("pda ID: "+this.parada.getId());
		Consultas c = new Consultas();
		ArrayList<Linha> linhas = c.getLinhasByParada(this.parada.getId());
		String[][] data = new String[linhas.size()][5];

		for(int i = 1; i < linhas.size(); i++){
			String[] l = {linhas.get(i).getId(), linhas.get(i).getNome(), linhas.get(i).getCodigo(), linhas.get(i).getTipo(),};
			data[i-1] = l;
		}
		//Cria a tabela
		javax.swing.JTable tabela = new javax.swing.JTable(data, columnNames);

		barraRolagem = new javax.swing.JScrollPane(tabela);
		painel.add(barraRolagem);
		painel.add(panelActions);

		return painel;
	}

}
