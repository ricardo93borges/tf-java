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
import java.util.Vector;

import javax.swing.JFrame;
import org.jxmapviewer.viewer.GeoPosition;
import consultonibus.gui.TableLinhasModel;

import consultaonibus.consultas.Consultas;
import consultaonibus.Parada;
import consultaonibus.Linha;

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
    private javax.swing.JTable tabelaLinhasOnibus;
    private javax.swing.JTable tabelaLinhasLotacao;
    private javax.swing.JTextField cadastroIdField;
    private javax.swing.JTextField cadastroNomeField;
    private javax.swing.JTextField cadastroCodigoField;
    private javax.swing.JComboBox cadastroTipoCombo;
    private javax.swing.JTextField cadastroParadaIdField;
    private javax.swing.JTextField cadastroParadaLongitudeField;
    private javax.swing.JTextField cadastroParadaLatitudeField;
    private javax.swing.JTextField cadastroParadaCodigoField;
    private javax.swing.JComboBox cadastroParadaTerminalCombo;
    private javax.swing.JComboBox cadastroParadaLinhaCombo; 

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
         
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
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

    /*
     * Metodo para mostrar a janela de cadastro
     */
    private void showCadastro(java.awt.event.ActionEvent evt) {
    	javax.swing.JFrame janelaCadastro = new javax.swing.JFrame("Cadastro");
    	javax.swing.JTabbedPane tabPaneCadastro = new javax.swing.JTabbedPane();
    	janelaCadastro.setSize(500, 300);

    	tabPaneCadastro.addTab("Linha", initCadastroLinhaTab());
    	tabPaneCadastro.addTab("Parada", initCadastroParadaTab());
    	
    	janelaCadastro.add(tabPaneCadastro);
    	janelaCadastro.setVisible(true);
    }
    
    /*
     * Metodo para contruir o formulario de cadastro de paradas
     */
    private javax.swing.JPanel initCadastroParadaTab() {
    	javax.swing.JPanel panelCadastro = new javax.swing.JPanel();
    	
    	//Define o proximo ID
		Consultas c = new Consultas();
		ArrayList<Parada> paradas = c.getParadas();
		Parada.ordenar(paradas);
		ArrayList<Linha> linhas = c.getLinhas();
		int index = paradas.size()-1;
		Parada p = paradas.get(index);
		String nextId = String.valueOf((Integer.parseInt(p.getId()))+1);
		
		javax.swing.JLabel cadastroIdLabel = new javax.swing.JLabel("ID");
    	cadastroParadaIdField = new javax.swing.JTextField();
    	cadastroParadaIdField.setText(nextId);
    	cadastroParadaIdField.setEditable(false);
    	
    	//ComboBox Linhas
    	Vector<Linha> model = new Vector();
     	for(int i=0; i < linhas.size(); i++){
     		model.add(linhas.get(i));
     	}
     	javax.swing.JLabel cadastroLinhaLabel = new javax.swing.JLabel("Linha");
     	cadastroParadaLinhaCombo = new javax.swing.JComboBox(model);
     	//
    	
    	javax.swing.JLabel cadastroCodigoLabel = new javax.swing.JLabel("Codigo");
    	cadastroParadaCodigoField = new javax.swing.JTextField();
		
    	javax.swing.JLabel cadastroLongitudeLabel = new javax.swing.JLabel("Longitude");
        cadastroParadaLongitudeField = new javax.swing.JTextField();
        
        javax.swing.JLabel cadastroLatitudeLabel = new javax.swing.JLabel("Latitude");
        cadastroParadaLatitudeField = new javax.swing.JTextField();

		String[] itens = {"Sim", "Não"};
		javax.swing.JLabel cadastroTerminalLabel = new javax.swing.JLabel("Terminal");
		cadastroParadaTerminalCombo = new javax.swing.JComboBox(itens);
		
		javax.swing.JButton btnCadastro = new javax.swing.JButton();
		btnCadastro.setText("Cadastrar");
		btnCadastro.setName("cadastrar");
		btnCadastro.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		        cadastrarParada(evt);
		    }
		});
		
		 javax.swing.GroupLayout cadastroLayout = new javax.swing.GroupLayout(panelCadastro);
		 panelCadastro.setLayout(cadastroLayout);
		 
		 cadastroLayout.setHorizontalGroup(
				 cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		     .addGroup(cadastroLayout.createParallelGroup()
		         //.addContainerGap()
		     .addComponent(cadastroIdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(cadastroParadaIdField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(cadastroLinhaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(cadastroParadaLinhaCombo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(cadastroCodigoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(cadastroParadaCodigoField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(cadastroLongitudeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(cadastroParadaLongitudeField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(cadastroLatitudeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(cadastroParadaLatitudeField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(cadastroTerminalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(cadastroParadaTerminalCombo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(btnCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     //.addContainerGap())
		     )
		 );
		 cadastroLayout.setVerticalGroup(
			cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		     .addGroup(cadastroLayout.createSequentialGroup()
		         .addContainerGap()
		         .addComponent(cadastroIdLabel)
			     .addComponent(cadastroParadaIdField)
			     .addComponent(cadastroLinhaLabel)
			     .addComponent(cadastroParadaLinhaCombo)
			     .addComponent(cadastroCodigoLabel)
			     .addComponent(cadastroParadaCodigoField)
			     .addComponent(cadastroLongitudeLabel)
			     .addComponent(cadastroParadaLongitudeField)
			     .addComponent(cadastroLatitudeLabel)
			     .addComponent(cadastroParadaLatitudeField)
			     .addComponent(cadastroTerminalLabel)
			     .addComponent(cadastroParadaTerminalCombo)
			     .addComponent(btnCadastro)
		         .addContainerGap(270, Short.MAX_VALUE))
		 );
		 
    	return panelCadastro;
    }
    
    public void cadastrarParada(java.awt.event.ActionEvent evt){
    	String id = cadastroParadaIdField.getText();
    	Linha linha = (Linha)cadastroParadaLinhaCombo.getSelectedItem();
    	String idLinha = linha.getId();
    	String codigo = 	"\""+cadastroParadaCodigoField.getText()+"\"";
    	String terminal = 	cadastroParadaTerminalCombo.getSelectedItem().toString();
    	String latitude = 	"\""+cadastroParadaLatitudeField.getText()+"\"";
    	String longitude = 	"\""+cadastroParadaLongitudeField.getText()+"\"";
    	
    	
    	if(id == null || id.isEmpty() || latitude == null || latitude.isEmpty() || codigo == null || codigo.isEmpty() || longitude == null || longitude.isEmpty()){
    		javax.swing.JOptionPane.showMessageDialog(null, "Informe todos os campos");
    	}else{
	    	if(terminal.equals("Sim")){
	    		terminal = "\"S\"";
	    	}else if(terminal.equals("Não")){
	    		terminal = "\"N\"";
	    	}
	    	Parada parada = new Parada(id, codigo, latitude, longitude, terminal);
	    	parada.addParada();
	    	parada.relacionaLinhaParada(idLinha, id);
	    	
	    	javax.swing.JOptionPane.showMessageDialog(null, "Nova parada da cadastrada com sucesso!");
    	}
    }

    
    /*
     * Metodo para contruir o formulario de cadastro de linhas
     */
    private javax.swing.JPanel initCadastroLinhaTab() {
		javax.swing.JPanel panelCadastro = new javax.swing.JPanel();
		
		//Define o proximo ID
		Consultas c = new Consultas();
		ArrayList<Linha> linhas = c.getLinhas();
		Linha.ordenar(linhas);
		int index = linhas.size()-1;
		Linha l = linhas.get(index);
		String nextId = String.valueOf((Integer.parseInt(l.getId()))+1);
		
		javax.swing.JLabel cadastroIdLabel = new javax.swing.JLabel("ID");
		cadastroIdField = new javax.swing.JTextField();
		cadastroIdField.setText(nextId);
		cadastroIdField.setEditable(false);
		
		javax.swing.JLabel cadastroNomeLabel = new javax.swing.JLabel("Nome");
		cadastroNomeField = new javax.swing.JTextField();
		
		javax.swing.JLabel cadastroCodigoLabel = new javax.swing.JLabel("Codigo");
		cadastroCodigoField = new javax.swing.JTextField();

		String[] itens = {"Onibus", "Lotação"};
		javax.swing.JLabel cadastroTipoLabel = new javax.swing.JLabel("Tipo");
		cadastroTipoCombo = new javax.swing.JComboBox(itens);
		
		javax.swing.JButton btnCadastro = new javax.swing.JButton();
		btnCadastro.setText("Cadastrar");
		btnCadastro.setName("cadastrar");
		btnCadastro.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		        cadastrarLinha(evt);
		    }
		});
		
		 javax.swing.GroupLayout cadastroLayout = new javax.swing.GroupLayout(panelCadastro);
		 panelCadastro.setLayout(cadastroLayout);
		 
		 cadastroLayout.setHorizontalGroup(
				 cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		     .addGroup(cadastroLayout.createParallelGroup()
		         //.addContainerGap()
		     .addComponent(cadastroIdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(cadastroIdField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(cadastroNomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(cadastroNomeField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(cadastroCodigoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(cadastroCodigoField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(cadastroCodigoField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(cadastroTipoCombo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     .addComponent(btnCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		     //.addContainerGap())
		     )
		 );
		 cadastroLayout.setVerticalGroup(
			cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		     .addGroup(cadastroLayout.createSequentialGroup()
		         .addContainerGap()
		         .addComponent(cadastroIdLabel)
		         .addComponent(cadastroIdField)
		         .addComponent(cadastroNomeLabel)
		         .addComponent(cadastroNomeField)
		         .addComponent(cadastroCodigoLabel)
		         .addComponent(cadastroCodigoField)
		         .addComponent(cadastroTipoCombo)
		         .addComponent(btnCadastro)
		         .addContainerGap(270, Short.MAX_VALUE))
		 );
    	
    	return panelCadastro;
    }
    
    public void cadastrarLinha(java.awt.event.ActionEvent evt){
    	String id = cadastroIdField.getText();
    	String nome = 	"\""+cadastroNomeField.getText()+"\"";
    	String codigo = "\""+cadastroCodigoField.getText()+"\"";
    	String tipo = 	cadastroTipoCombo.getSelectedItem().toString();
    	
    	if(id == null || id.isEmpty() || nome == null || nome.isEmpty() || codigo == null || codigo.isEmpty()){
    		javax.swing.JOptionPane.showMessageDialog(null, "Informe todos os campos");
    	}else{
	    	if(tipo.equals("Onibus")){
	    		tipo = "\"O\"";
	    	}else if(tipo.equals("Lotação")){
	    		tipo = "\"L\"";
	    	}
	    	Linha linha = new Linha(id, nome, codigo, tipo);
	    	linha.addLinha();
	    	
	    	javax.swing.JOptionPane.showMessageDialog(null, "Nova linha da cadastrada com sucesso!");
    	}
    }
    
    /*
     * Metodo para mostrar as linhas
     */
    private void showLinhas(java.awt.event.ActionEvent evt) {
    	javax.swing.JFrame janelaLinhas = new javax.swing.JFrame("Linhas");
    	javax.swing.JTabbedPane tabPaneLinhas = new javax.swing.JTabbedPane();
    	javax.swing.JPanel panelLinhas = new javax.swing.JPanel();
    	panelLinhas.setLayout(new javax.swing.BoxLayout(panelLinhas, javax.swing.BoxLayout.PAGE_AXIS));

    	tabPaneLinhas.addTab("Onibus", tabelaOnibus());
    	tabPaneLinhas.addTab("Lotação", tabelaLotacao());
    	
    	
    	panelLinhas.add(tabPaneLinhas);
    	//panelLinhas.add(panelActions);
    	
    	janelaLinhas.add(panelLinhas);
    	
    	janelaLinhas.pack();
    	janelaLinhas.setVisible(true);
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
    	}
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
