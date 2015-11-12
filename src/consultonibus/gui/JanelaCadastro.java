package consultonibus.gui;

import java.util.ArrayList;
import java.util.Vector;

import consultaonibus.consultas.Consultas;
import consultaonibus.Parada;
import consultaonibus.Linha;

import javax.swing.*;

public class JanelaCadastro extends JFrame{
	
	private javax.swing.JTextField cadLinhaTextId;
    private javax.swing.JTextField cadLinhaTextNome;
    private javax.swing.JTextField cadLinhaTextCodigo;
    private javax.swing.JComboBox cadLinhaComboTipo;
    private javax.swing.JTextField cadParadaTextID;
    private javax.swing.JTextField cadParadaTextLongitude;
    private javax.swing.JTextField cadParadaTextLatitude;
    private javax.swing.JTextField cadParadaTextCodigo;
    private javax.swing.JComboBox cadParadaComboTerminal;
    private javax.swing.JComboBox cadParadaComboLinha;
	
	 public JanelaCadastro() {
		 javax.swing.JTabbedPane tabPaneCadastro = new javax.swing.JTabbedPane();
		 this.setSize(400, 450);
		 this.setTitle("Cadastro");

	     tabPaneCadastro.addTab("Linha", initCadastroLinhaTab());
		 tabPaneCadastro.addTab("Parada", initCadastroParadaTab());

		 this.add(tabPaneCadastro);
		 this.setVisible(true);
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

		javax.swing.JLabel cadParadaLabelId = new javax.swing.JLabel("ID");
		javax.swing.JLabel cadParadaLabelCodigo = new javax.swing.JLabel("Código");
		javax.swing.JLabel cadParadaLabelLatitude = new javax.swing.JLabel("Latitude");
		javax.swing.JLabel cadParadaLabelLongitude = new javax.swing.JLabel("Longitude");
		javax.swing.JLabel cadParadaLabelLinha = new javax.swing.JLabel("Linha");
		javax.swing.JLabel cadParadaLabelTerminal = new javax.swing.JLabel("Terminal");

		cadParadaTextID = new javax.swing.JTextField();
		cadParadaTextCodigo = new javax.swing.JTextField();
		cadParadaTextLatitude = new javax.swing.JTextField();
		cadParadaTextLongitude = new javax.swing.JTextField();

		cadParadaTextID = new javax.swing.JTextField();
		cadParadaTextID.setText(nextId);
		cadParadaTextID.setEditable(false);

		javax.swing.JComboBox cadParadaComboLinha = new javax.swing.JComboBox<>();
		javax.swing.JComboBox cadParadaComboTerminal = new javax.swing.JComboBox<>();
		javax.swing.JButton cadParadaBtnCadastrar = new javax.swing.JButton();

		//Combo terminal
		String[] itens = {"Sim", "Não"};
		this.cadParadaComboTerminal = new javax.swing.JComboBox(itens);

		//Combo linha
		Vector<Linha> model = new Vector();
		for(int i=1; i < linhas.size(); i++){
			model.add(linhas.get(i));
		}
		this.cadParadaComboLinha = new javax.swing.JComboBox(model);

		cadParadaBtnCadastrar.setText("Cadastrar");
		cadParadaBtnCadastrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cadastrarParada(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panelCadastro);
		panelCadastro.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(cadParadaBtnCadastrar)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(this.cadParadaComboLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(cadParadaLabelId, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(cadParadaTextID, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(cadParadaLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(cadParadaTextCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(cadParadaLabelLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(cadParadaTextLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(cadParadaLabelLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(cadParadaTextLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(cadParadaLabelTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGroup(layout.createSequentialGroup()
																		.addGap(4, 4, 4)
																		.addComponent(cadParadaLabelLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
												.addComponent(this.cadParadaComboTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(24, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(cadParadaLabelId)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cadParadaTextID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(cadParadaLabelCodigo)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cadParadaTextCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(cadParadaLabelLatitude)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cadParadaTextLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(cadParadaLabelLongitude)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cadParadaTextLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(cadParadaLabelLinha)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.cadParadaComboLinha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(cadParadaLabelTerminal)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.cadParadaComboTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
								.addComponent(cadParadaBtnCadastrar)
								.addContainerGap())
		);

		return panelCadastro;
	}

	    public void cadastrarParada(java.awt.event.ActionEvent evt){
	    	String id = cadParadaTextID.getText();
	    	Linha linha = (Linha)cadParadaComboLinha.getSelectedItem();
	    	String idLinha = linha.getId();
	    	String codigo = 	"\""+cadParadaTextCodigo.getText()+"\"";
	    	String terminal = 	cadParadaComboTerminal.getSelectedItem().toString();
	    	String latitude = 	"\""+cadParadaTextLatitude.getText()+"\"";
	    	String longitude = 	"\""+cadParadaTextLongitude.getText()+"\"";
	    	
	    	
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
			
	    	javax.swing.JLabel labelId = new javax.swing.JLabel("ID");
	    	javax.swing.JLabel labelNome = new javax.swing.JLabel("Nome");
	    	javax.swing.JLabel labelCodigo = new javax.swing.JLabel("Código");
	        javax.swing.JLabel labelTipo = new javax.swing.JLabel("Tipo");
	        
	    	cadLinhaTextId = new javax.swing.JTextField();
	        cadLinhaTextNome = new javax.swing.JTextField();
	        cadLinhaTextCodigo = new javax.swing.JTextField();
	        javax.swing.JButton btnCadastrarLinha = new javax.swing.JButton();

	        cadLinhaTextId.setText(nextId);
	        cadLinhaTextId.setEditable(false);
			
	        String[] itens = {"Onibus", "Lotação"};
			javax.swing.JLabel cadastroTipoLabel = new javax.swing.JLabel("Tipo");
			cadLinhaComboTipo = new javax.swing.JComboBox(itens);
			
	        btnCadastrarLinha.setText("Cadastrar");
			btnCadastrarLinha.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					cadastrarLinha(evt);
				}
			});

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panelCadastro);
	        panelCadastro.setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(25, 25, 25)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(btnCadastrarLinha)
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                        .addComponent(cadLinhaTextId, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
	                        .addComponent(labelId, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(labelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(labelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(labelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addComponent(cadLinhaTextNome, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
	                        .addComponent(cadLinhaTextCodigo)
	                        .addComponent(cadLinhaComboTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
	                .addContainerGap(29, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(labelId)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(cadLinhaTextId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addComponent(labelNome)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(cadLinhaTextNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addComponent(labelCodigo)
	                .addGap(5, 5, 5)
	                .addComponent(cadLinhaTextCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addComponent(labelTipo)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(cadLinhaComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
	                .addComponent(btnCadastrarLinha)
	                .addContainerGap())
	        );
	        
	        return panelCadastro;
	    }  
	    
	    public void cadastrarLinha(java.awt.event.ActionEvent evt){
			System.out.println("Click");
	    	String id = cadLinhaTextId.getText();
	    	String nome = 	"\""+cadLinhaTextNome.getText()+"\"";
	    	String codigo = "\""+cadLinhaTextCodigo.getText()+"\"";
	    	String tipo = 	cadLinhaComboTipo.getSelectedItem().toString();

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
}
