package consultonibus.gui;

import java.awt.Color;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

/**
 * Um waypoint que tem uma cor e um valor associado
 *
 * @author Martin Steiger, Marcelo Cohen
 */
public class MyWaypoint extends DefaultWaypoint {

    private final Color color;
    private double value;

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

    public double getValue() {
        return value;
    }
}
