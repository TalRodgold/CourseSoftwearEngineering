package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * class Sphere
 */
public class Sphere implements Geometry{

    /**
     * Center Point
     */
    protected Point center;
    /**
     * Radius
     */
    protected double radius;

    /**
     * ctor
     * @param center Point
     * @param radius double
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
