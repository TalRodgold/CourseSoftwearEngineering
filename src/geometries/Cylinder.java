package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Class Cylinder which is a type of tube
 */
public class Cylinder extends Tube{
    /**
     * a tube with height
     */
    protected double height;

    /**
     * ctor
     * @param axisRay Ray
     * @param radius double
     * @param height double
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point p) {
        return super.getNormal(p);
    }

    /**
     * ctor that calls the father ctor
     * @param axisRay Ray
     * @param radius double
     */
    public Cylinder(Ray axisRay, double radius) {
        super(axisRay, radius);
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}
