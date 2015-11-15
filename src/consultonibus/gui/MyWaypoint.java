package consultonibus.gui;

import java.awt.Color;

import consultaonibus.Parada;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

/**
 * Um waypoint que tem uma cor e um valor associado
 *
 * @author Martin Steiger, Marcelo Cohen
 */
public class MyWaypoint extends DefaultWaypoint {

    private Color color;
    private double value;
    private Parada parada;

    /**
     * @param color a cor
     * @param coord a localização
     */
    public MyWaypoint(Color color, double value, GeoPosition coord) {
        super(coord);
        this.color = color;
        this.value = value;
    }

    /**
     * @returns a cor do waypoint
     */
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getValue() {
        return value;
    }

    public Parada getParada() {
        return parada;
    }

    public void setParada(Parada parada) {
        this.parada = parada;
    }


}
