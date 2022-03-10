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

        Point po = axisRay.getP0();
        Vector v = axisRay.getDir();
        Vector p1 = p.subtract(po);
        double t = v.dotProduct(p1);
        Point O = po.add(p1.scale(t));
        if (O.equals(p)) {
            throw new IllegalArgumentException("point cannot be equal to O");
        }
        Vector n = p.subtract(O);
        return n.normalize();    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}
