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
        Point p0 = ray.getP0(); // get point
        Vector v = ray.getDir(); // get direction
        Vector n = normal; // set vector normal

        if(q0.equals(p0)){ //if the ray starting point is equal to the plane point (meaning the ray starts on the plane)
            return null;
        }

        Vector sub = q0.subtract(p0);
        double ns = alignZero(n.dotProduct(sub)); // normal ∙ sub
        if (isZero(ns)){ // if the starting point of the ray is on the plane return null
            return null;
        }

        double nv = alignZero(n.dotProduct(v)); // normal ∙ direction
        if(isZero(nv)){ // if the ray is vertical to the plane and the starting point is on the plane return null
            return null;
        }

        double t = alignZero(ns / nv); // from presentation 4

        if(t > 0){ // if t > 0 means intersection exists
            return List.of(ray.getPoint(t));
        }
        return null; // else
    }


    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }

}
