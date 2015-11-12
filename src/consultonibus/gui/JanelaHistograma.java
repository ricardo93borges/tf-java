package consultonibus.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import consultaonibus.ParadaLinha;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import consultaonibus.Linha;
import consultaonibus.Parada;
import consultaonibus.consultas.Consultas;
import consultaonibus.reader.Reader;

import javax.swing.*;


public class JanelaHistograma extends JFrame{
	
	public JanelaHistograma(){
		javax.swing.JPanel panelHistograma = new javax.swing.JPanel();
		this.setSize(1080, 720);
		this.setTitle("Histograma");

		panelHistograma.setLayout(new javax.swing.BoxLayout(panelHistograma, javax.swing.BoxLayout.PAGE_AXIS));
		
		XYSeries series = new XYSeries("Paradas por linha");
		Map<Double, Double> dados = this.getDados();
		
		Iterator it = dados.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			series.add((double)pair.getValue(), (double)pair.getKey());
		}

		IntervalXYDataset ds = new XYSeriesCollection(series);
				
		JFreeChart grafico = ChartFactory.createHistogram("Histograma",	"Linhas", "Paradas", ds, PlotOrientation.VERTICAL, true, true, false);
		
		panelHistograma.add(new ChartPanel(grafico));
		this.add(panelHistograma);
		this.setVisible(true);
	}

	public ArrayList getParadasByLinha(String linha){
		Reader r = new Reader();
		ArrayList<String[]> paradasLinhas = r.readCsv("paradalinha.csv", ";");
		ArrayList<String[]> paradas = r.readCsv("paradas.csv", ";");
		ArrayList<Parada> retorno = new ArrayList<Parada>();

		for(int i=0; i < paradasLinhas.size(); i++){
			String[] aux = paradasLinhas.get(i);

			if(aux[0].equals(linha)){
				for(int j=0; j < paradas.size(); j++){
					String[] aux2 = paradas.get(j);
					if(aux2[0].equals(aux[1])){
						retorno.add(new Parada(aux2[0], aux2[1], aux2[2], aux2[3], aux2[4]));
					}
				}
			}
		}

		return retorno;
	}

	public ArrayList<Parada> getParadasByLinha(String linhaId, ArrayList<ParadaLinha> paradasLinhas, ArrayList<Parada> paradas){
		ArrayList<Parada> retorno = new ArrayList<Parada>();

		for(int i=0; i < paradasLinhas.size(); i++){
			ParadaLinha pl = paradasLinhas.get(i);

			if(pl.getIdLinha().equals(linhaId)){
				for(int j=0; j < paradas.size(); j++){
					Parada p = paradas.get(j);
					if(p.getId().equals(pl.getIdParada())){
						retorno.add(p);
					}
				}
			}
		}

		return retorno;
	}

	public Map<Double, Double> getDados(){
		Consultas c = new Consultas();
		Map<String, Integer> linhasParadas = new HashMap<String, Integer>();
		Map<Double, Double> dados = new HashMap<Double, Double>(); 
		
		ArrayList<Linha> linhas = c.getLinhas();
		ArrayList<ParadaLinha> paradasLinhas = c.getParadaLinha();
		ArrayList<Parada> paradas = c.getParadas();
		
		//System.out.println("loop 1");
		for(int i=1; i<linhas.size(); i++){
			ArrayList<Parada> linhaParadas = this.getParadasByLinha(linhas.get(i).getId(), paradasLinhas, paradas);
			linhasParadas.put(linhas.get(i).getId(), linhaParadas.size());
		}
		
		//System.out.println("loop 2");
		Iterator it = linhasParadas.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        double paradasQnt = (double)(int)pair.getValue();
	        if(dados.containsKey(paradasQnt)){
	        	dados.put(paradasQnt, dados.get(paradasQnt)+1);
	        }else{
	        	dados.put(paradasQnt, 1.0);
	        }
	        //it.remove(); 
	    }
		
	    /*System.out.println("loop 3");
	    it = dados.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	        //it.remove(); 
	    }
	    */
	    return dados;
	}
}