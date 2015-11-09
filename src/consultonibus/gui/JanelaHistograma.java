package consultonibus.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

public class JanelaHistograma {
	
	public JanelaHistograma(){
		javax.swing.JFrame janelaHistograma = new javax.swing.JFrame("Linhas");
		javax.swing.JPanel panelHistograma = new javax.swing.JPanel();
		janelaHistograma.setSize(1080, 720);

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
		janelaHistograma.add(panelHistograma);
		janelaHistograma.setVisible(true);
	}
	
	public Map<Double, Double> getDados(){
		Consultas c = new Consultas();
		Map<String, Integer> linhasParadas = new HashMap<String, Integer>();
		Map<Double, Double> dados = new HashMap<Double, Double>(); 
		
		ArrayList<Linha> linhas = c.getLinhas();
		
		//System.out.println("loop 1");
		for(int i=1; i<linhas.size(); i++){
			ArrayList<Parada> paradas = c.getParadasByLinha(linhas.get(i).getId());
			linhasParadas.put(linhas.get(i).getId(), paradas.size());
		}
		
		//System.out.println("loop 2");
		Iterator it = linhasParadas.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        double paradas = (double)(int)pair.getValue();
	        if(dados.containsKey(paradas)){
	        	dados.put(paradas, dados.get(paradas)+1);
	        }else{
	        	dados.put(paradas, 1.0);
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
