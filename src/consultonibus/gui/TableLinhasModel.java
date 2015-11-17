package consultonibus.gui;

import java.util.ArrayList;

import consultaonibus.Linha;
import consultaonibus.Parada;

public class TableLinhasModel extends javax.swing.table.AbstractTableModel{
	private String[] columnNames = {"ID", "Nome", "Codigo", "Tipo"};
	private Object[][] data = {};
	private ArrayList<Linha> linhas;
	
	public TableLinhasModel(String tipo, ArrayList<Linha> linhas){
		super();
		this.linhas = linhas;
		
    	//dados
		switch(tipo){
			case "onibus":
				for(int i=0; i < linhas.size(); i++){
					if(!linhas.get(i).getTipo().equals("\"O\"")){
						this.linhas.remove(i);
					}
				}
			break;
			case "lotacao":
				for(int i=0; i < linhas.size(); i++){
					if(!linhas.get(i).getTipo().equals("\"L\"")){
						this.linhas.remove(i);
					}
				}
			break;
		}
    	
    	this.data = new String[linhas.size()][5];
    	
    	for(int i = 1; i < linhas.size(); i++){
    		String[] l = {String.valueOf(linhas.get(i).getId()), linhas.get(i).getNome(), linhas.get(i).getCodigo(), linhas.get(i).getTipo(),};
    		this.data[i-1] = l;
    	}
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public int getRowCount() {
		return data.length;
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	public boolean isCellEditable(int row, int col) {
		if (col < 2) {
			return false;
		} else {
			return true;
		}
	}
}
