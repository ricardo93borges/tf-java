/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultonibus.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import org.jxmapviewer.viewer.GeoPosition;

import consultaonibus.consultas.Consultas;

/**
 *
 * @author Sandro
 */
public class JanelaConsulta extends JFrame {

    private GerenciadorMapa gerenciador;
    private javax.swing.JButton btnConsulta;
    private javax.swing.JButton btnLinhas;
    private javax.swing.JButton btnHistograma;
    private javax.swing.JButton btnAutores;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel painelMapa;

    /**
     * Creates new form JanelaConsulta2
     */
    public JanelaConsulta() {
        initComponents();
        initMyComponents();
    }

    private void initMyComponents() {
        GeoPosition poa = new GeoPosition(-30.05, -51.18);
        gerenciador = new GerenciadorMapa(poa, GerenciadorMapa.FonteImagens.VirtualEarth);

        painelMapa.setLayout(new BorderLayout());
        painelMapa.add(gerenciador.getMapKit(), BorderLayout.CENTER);
         
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }


    private void initComponents() {
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        btnConsulta = new javax.swing.JButton();
        btnLinhas = new javax.swing.JButton();
        btnHistograma = new javax.swing.JButton();
        btnAutores = new javax.swing.JButton();
        painelMapa = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //btnConsulta
        btnConsulta.setText("Consulta");
        btnConsulta.setName(""); // NOI18N
        btnConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaActionPerformed(evt);
            }
        });
        //btnLinhas
        btnLinhas.setText("Linhas");
        btnLinhas.setName("linhas");
        btnLinhas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showLinhas(evt);
            }
        });
        //btnHistograma
        btnHistograma.setText("Histograma");
        btnHistograma.setName("histograma");
        //btnAutores
        btnAutores.setText("Autores");
        btnAutores.setName("autores");
        btnAutores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showAutores(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup()
                //.addContainerGap()
                .addComponent(btnConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLinhas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHistograma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAutores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                //.addContainerGap())
            )
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnConsulta)
                .addComponent(btnLinhas)
                .addComponent(btnHistograma)
                .addComponent(btnAutores)
                .addContainerGap(270, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel1);

        javax.swing.GroupLayout painelMapaLayout = new javax.swing.GroupLayout(painelMapa);
        painelMapa.setLayout(painelMapaLayout);
        painelMapaLayout.setHorizontalGroup(
            painelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 363, Short.MAX_VALUE)
        );
        painelMapaLayout.setVerticalGroup(
            painelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(painelMapa);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    private void showAutores(java.awt.event.ActionEvent evt) {
    	javax.swing.JOptionPane.showMessageDialog(null, "Júlia Arone, Marcelo Barbosa, Ricardo Borges");
    }
    
    private void showLinhas(java.awt.event.ActionEvent evt) {
    	javax.swing.JFrame janelaLinhas = new javax.swing.JFrame("Linhas");
    	javax.swing.JTabbedPane tabPaneLinhas = new javax.swing.JTabbedPane();
    	
    	tabPaneLinhas.addTab("Onibus", tabelaOnibus());
    	tabPaneLinhas.addTab("Lotação", tabelaLotacao());
    	
    	janelaLinhas.add(tabPaneLinhas);
    	
    	janelaLinhas.pack();
    	janelaLinhas.setVisible(true);

    }
    
    private javax.swing.JPanel tabelaOnibus(){
    	javax.swing.JPanel painel;
    	javax.swing.JTable tabela;
    	javax.swing.JScrollPane barraRolagem;
    	
    	Consultas c = new Consultas();
    	ArrayList<String[]> linhas = c.getLinhas("onibus");
    	 
    	String [] colunas = linhas.get(0);
    	String [][] dados = new String[linhas.size()][5];
    	
    	for(int i = 1; i < linhas.size(); i++){
    		dados[i-1] = linhas.get(i);
    	}
    	
    	painel = new javax.swing.JPanel(); 
    	painel.setLayout(new BorderLayout()); 
    	tabela = new javax.swing.JTable(dados, colunas); 
    	tabela.setEnabled(false);
    	barraRolagem = new javax.swing.JScrollPane(tabela); 
    	painel.add(barraRolagem); 
    	getContentPane().add(painel, BorderLayout.CENTER);

    	return painel;
    }
    
    private javax.swing.JPanel tabelaLotacao(){
    	javax.swing.JPanel painel;
    	javax.swing.JTable tabela;
    	javax.swing.JScrollPane barraRolagem;
    	
    	Consultas c = new Consultas();
    	ArrayList<String[]> linhas = c.getLinhas("lotacao");
    	 
    	String [] colunas = linhas.get(0);
    	String [][] dados = new String[linhas.size()][4];
    	
    	for(int i = 1; i < linhas.size(); i++){
    		dados[i-1] = linhas.get(i);
    	}
    	
    	painel = new javax.swing.JPanel(); 
    	painel.setLayout(new BorderLayout()); 
    	tabela = new javax.swing.JTable(dados, colunas); 
    	barraRolagem = new javax.swing.JScrollPane(tabela); 
    	painel.add(barraRolagem); 
    	getContentPane().add(painel, BorderLayout.CENTER);

    	return painel;
    }
    
    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed

        // Para obter o centro e o raio, usar como segue:
        GeoPosition centro = gerenciador.getSelecaoCentro();
        int raio = gerenciador.getRaio();

        // Lista para armazenar o resultado da consulta
        List<MyWaypoint> lstPoints = new ArrayList<>();

        // Exemplo:
        double valor = 250; // ex: valor da consulta (criminalidade ou distância)
        GeoPosition loc = new GeoPosition(-30.05, -51.18); // ex: localização da parada
        lstPoints.add(new MyWaypoint(Color.BLUE, valor, loc));

        // Informa o resultado para o gerenciador
        gerenciador.setPontos(lstPoints);
        // Informa o intervalo de valores gerados, para calcular a cor de cada ponto
        double menorValor = 15;  // exemplo
        double maiorValor = 250; // exemplo
        gerenciador.setIntervaloValores(menorValor, maiorValor);

        this.repaint();
    }

}
