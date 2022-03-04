package geometries;
import primitives.*;

/**
 * Class Tube
 */
public class Tube implements Geometry {

    protected Ray axisRay;
    protected double radius;

    /**
     *  ctor
     * @param axisRay Ray
     * @param radius double
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    public Ray getAxisRay() {
        return axisRay;
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
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}
