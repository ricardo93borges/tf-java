/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultonibus.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFrame;

import consultaonibus.Parada;
import consultaonibus.Util;
import consultaonibus.consultas.Consultas;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

/**
 *
 * @author Sandro
 */
public class JanelaConsulta extends JFrame {

    private GerenciadorMapa gerenciador;
    private javax.swing.JButton btnConsulta;
    private javax.swing.JButton btnLinhas;
    private javax.swing.JButton btnCadastro;
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

    /*
     * Inicializa mapa
     */
    private void initMyComponents() {
        GeoPosition poa = new GeoPosition(-30.05, -51.18);
        gerenciador = new GerenciadorMapa(poa, GerenciadorMapa.FonteImagens.VirtualEarth);

        painelMapa.setLayout(new BorderLayout());
        painelMapa.add(gerenciador.getMapKit(), BorderLayout.CENTER);

        this.showPontos();

        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

    }

    public void showPontos(){
        Consultas c = new Consultas();
        ArrayList<Parada> paradas = c.getParadas();
        List<MyWaypoint> pontos = new ArrayList<MyWaypoint>();

        for(int i=1; i<paradas.size(); i++) {
            //System.out.println(paradas.get(i).getLatitude());
            String latStr = Util.substituir(paradas.get(i).getLatitude(), ",", ".");
            String lngStr = Util.substituir(paradas.get(i).getLongitude(), ",", ".");

            double lat = Double.parseDouble(latStr);
            double lng = Double.parseDouble(lngStr);

            //Color color = new Color(0, 0, 0);
            GeoPosition coord = new GeoPosition(lat, lng);
            MyWaypoint wp = new MyWaypoint(Color.BLUE, 2.0, coord);
            wp.setParada(paradas.get(i));
            pontos.add(wp);

        }

        gerenciador.setPontos(pontos);

    }


    /*
     * Inicializa janela principal
     */
    private void initComponents() {
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        btnConsulta = new javax.swing.JButton();
        btnLinhas = new javax.swing.JButton();
        btnCadastro = new javax.swing.JButton();
        btnHistograma = new javax.swing.JButton();
        btnAutores = new javax.swing.JButton();
        painelMapa = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        /*
         * Inicializa botões do lado esquerdo
         */
        
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
        btnHistograma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showHistograma(evt);
            }
        });
        //btnAutores
        btnAutores.setText("Autores");
        btnAutores.setName("autores");
        btnAutores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showAutores(evt);
            }
        });
        //btnCadastro
        btnCadastro.setText("Cadastro");
        btnCadastro.setName("cadastro");
        btnCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showCadastro(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        
        /*
         * Adiciona os botões a janela principal
         */
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup()
                //.addContainerGap()
                .addComponent(btnConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLinhas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(btnCadastro)
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

    /*
     * Metodo para mostrar os autores
     */
    private void showAutores(java.awt.event.ActionEvent evt) {
    	javax.swing.JOptionPane.showMessageDialog(null, "Júlia Arone, Marcelo Barbosa, Ricardo Borges");
    }
    
	public void showCadastro(java.awt.event.ActionEvent evt){
	   JanelaCadastro cadastro = new JanelaCadastro();
	}
    
	public void showLinhas(java.awt.event.ActionEvent evt){
		JanelaLinhas linhas = new JanelaLinhas(gerenciador);
	}

	public void showHistograma(java.awt.event.ActionEvent evt){
		JanelaHistograma histograma = new JanelaHistograma();
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
