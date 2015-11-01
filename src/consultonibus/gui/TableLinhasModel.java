package consultonibus.gui;

import java.util.ArrayList;

import consultaonibus.consultas.Consultas;
import consultaonibus.Linha;

public class TableLinhasModel extends javax.swing.table.AbstractTableModel{
	private String[] columnNames = {"ID", "Nome", "Codigo", "Tipo"};
	private Object[][] data = {};
	
	public TableLinhasModel(String tipo){
		super();
		
		Consultas c = new Consultas();
    	ArrayList<Linha> linhas = c.getLinhas(tipo);
    	
    	this.data = new String[linhas.size()][5];
    	
    	for(int i = 1; i < linhas.size(); i++){
    		String[] l = {linhas.get(i).getId(), linhas.get(i).getNome(), linhas.get(i).getCodigo(), linhas.get(i).getTipo(),};
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
	
	/*
	* JTable uses this method to determine the default renderer/
	* editor for each cell.  If we didn't implement this method,
	* then the last column would contain text ("true"/"false"),
	* rather than a check box.
	*/
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	/*
	* Don't need to implement this method unless your table's
	* editable.
	*/
	public boolean isCellEditable(int row, int col) {
		//Note that the data/cell address is constant,
		//no matter where the cell appears onscreen.
		if (col < 2) {
			return false;
		} else {
			return true;
		}
	}
}
