package consultonibus.gui;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import consultaonibus.Util;
import consultaonibus.consultas.Consultas;
import consultaonibus.Parada;
import consultaonibus.Linha;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;

public class JanelaLinhas extends JFrame{
	private javax.swing.JTable tabelaLinhasOnibus;
    private javax.swing.JTable tabelaLinhasLotacao;
	private GerenciadorMapa gerenciador;
	
	public JanelaLinhas(GerenciadorMapa gerenciador){
		this.gerenciador = gerenciador;
		this.gerenciador.resetWaypoints();
		this.setTitle("Linhas");
		javax.swing.JTabbedPane tabPaneLinhas = new javax.swing.JTabbedPane();
		javax.swing.JPanel panelLinhas = new javax.swing.JPanel();

		panelLinhas.setLayout(new javax.swing.BoxLayout(panelLinhas, javax.swing.BoxLayout.PAGE_AXIS));

    	tabPaneLinhas.addTab("Onibus", tabelaOnibus());
    	tabPaneLinhas.addTab("Lotação", tabelaLotacao());
    	
    	panelLinhas.add(tabPaneLinhas);
    	this.add(panelLinhas);

		this.pack();
		this.setVisible(true);

	}
	
	/*
     * Metodo para contruir a tabela de onibus para a janela das linhas
    */
    private javax.swing.JPanel tabelaOnibus(){
    	javax.swing.JPanel painel;
    	javax.swing.JScrollPane barraRolagem;
    	javax.swing.JPanel panelActions = new javax.swing.JPanel();
    	
    	javax.swing.JButton btnConsulta = new javax.swing.JButton();
    	btnConsulta.setText("Consultar paradas");
    	btnConsulta.setName("consultar_paradas_onibus");
    	btnConsulta.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            consultarParadas(evt, "onibus");
	        }
	    });
    	
    	panelActions.add(btnConsulta);
    	
    	painel = new javax.swing.JPanel(); 
    	//painel.setLayout(new BorderLayout());
    	painel.setLayout(new javax.swing.BoxLayout(painel, javax.swing.BoxLayout.PAGE_AXIS));
    	this.tabelaLinhasOnibus = new javax.swing.JTable(new TableLinhasModel("onibus")); 
    	
    	barraRolagem = new javax.swing.JScrollPane(tabelaLinhasOnibus); 
    	painel.add(barraRolagem); 
    	painel.add(panelActions);
    	//getContentPane().add(painel, BorderLayout.CENTER);

    	return painel;
    }

    /*
     * Metodo para contruir a tabela de lotações para a janela das linhas
     */
    private javax.swing.JPanel tabelaLotacao(){
    	javax.swing.JPanel painel;
    	javax.swing.JScrollPane barraRolagem;
    	javax.swing.JPanel panelActions = new javax.swing.JPanel();
    	
    	javax.swing.JButton btnConsulta = new javax.swing.JButton();
    	btnConsulta.setText("Consultar paradas");
    	btnConsulta.setName("consultar_paradas_lotacao");
    	btnConsulta.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            consultarParadas(evt, "lotacao");
	        }
	    });
    	
    	panelActions.add(btnConsulta);
    	
    	painel = new javax.swing.JPanel(); 
    	//painel.setLayout(new BorderLayout());
    	painel.setLayout(new javax.swing.BoxLayout(painel, javax.swing.BoxLayout.PAGE_AXIS));
    	this.tabelaLinhasLotacao = new javax.swing.JTable(new TableLinhasModel("lotacao")); 
    	
    	barraRolagem = new javax.swing.JScrollPane(tabelaLinhasLotacao); 
    	painel.add(barraRolagem); 
    	painel.add(panelActions);
    	//getContentPane().add(painel, BorderLayout.CENTER);

    	return painel;
    }
    
    /*
     * Metodo para consultar as paradas de uma linha
     */
    private void consultarParadas(java.awt.event.ActionEvent evt, String tipo){
    	String idLinha = "";
    	if(tipo == "onibus"){
    		if(this.tabelaLinhasOnibus.getSelectedRow() < 0){
    	    	javax.swing.JOptionPane.showMessageDialog(null, "Selecione uma linha.");
    		}else{
		    	int row = this.tabelaLinhasOnibus.getSelectedRow();
		    	idLinha = (String)this.tabelaLinhasOnibus.getValueAt(row, 0);
    		}
    	}else if(tipo == "lotacao"){
    		if(this.tabelaLinhasLotacao.getSelectedRow() < 0){
    	    	javax.swing.JOptionPane.showMessageDialog(null, "Selecione uma linha.");	
    		}else{
    			int row = this.tabelaLinhasLotacao.getSelectedRow();
		    	idLinha = (String)this.tabelaLinhasLotacao.getValueAt(row, 0);
    		}
    	}
    	
    	Consultas c = new Consultas();
    	ArrayList<Parada> paradas = c.getParadasByLinha(idLinha);
    	if(paradas.size() == 0){
    		javax.swing.JOptionPane.showMessageDialog(null, "Não foi encontrado paradas para esta linha.");
    	}else{
    		System.out.print(paradas);

			for(int i=0; i< paradas.size(); i++){
				for(MyWaypoint wp : gerenciador.getPontos()){
					if(paradas.get(i).getId().equals(wp.getParada().getId())){
						wp.setColor(Color.GREEN);
					}
				}
			}

			gerenciador.getMapKit().repaint();
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
    }
}
