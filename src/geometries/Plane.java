package geometries;
import primitives.*;
import static primitives.Util.*;

import java.util.List;

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
        try {
            Vector u = p2.subtract(p1);
            Vector v = p3.subtract(p1);
            // if uXv = (0,0,0)
            Vector n = u.crossProduct(v);
            normal = n.normalize();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("can not create a plane if all 3 point are on the same line");
        }
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
        return normal;
    }


    @Override
    public List<Point> findIntsersections(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        if(q0.equals(p0)){
            return null;
        }

        Vector n = normal;

        // t = n∙(q0 - p0) / n∙v
        // if t > 0 point as found

        Vector p0_q0 = q0.subtract(p0);
        double mone = alignZero(n.dotProduct(p0_q0));
        if (isZero(mone)){ // the starting point of the ray is inside the plane
            return null;
        }

        double nv = alignZero(n.dotProduct(v));
        if(isZero(nv)){ // the ray is vertical on the plane
            return null;
        }

        double t = alignZero(mone / nv);

        if(t > 0){
            return List.of(ray.getPoint(t));
        }
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
