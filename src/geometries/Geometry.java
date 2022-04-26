package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Interface for all geometries with one func
 */
public abstract class Geometry extends Intersectable {
    protected Color emission = Color.BLACK;
    public abstract Vector getNormal(Point p);

    public Color getEmission() {
        return emission;
    }

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
}
