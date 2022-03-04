package geometries;
import primitives.*;

/**
 * Plane class represents a plane in the space
 */
public class Plane implements Geometry{
    /**
     * Point represents a point on the plane
     */
    protected Point q0;
    /**
     * associated vector which is normal to the plane
     */
    protected Vector normal;

    /**
     * ctor
     * @param q0 Point
     * @param normal Vector
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * ctor
     * @param p1 Point
     * @param p2 Point
     * @param p3 Point
     */
    public Plane(Point p1, Point p2, Point p3){
        q0 = p1;
        normal = null;
    }

    /**
     *
     * @return Point of the plane
     */
    public Point getQ0() {
        return q0;
    }

    /**
     *
     * @return the normal to the plane
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }
}
