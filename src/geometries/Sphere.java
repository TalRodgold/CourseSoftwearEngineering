package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

import java.util.List;

/**
 * class Sphere
 */
public class Sphere extends Geometry{

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
        return p.subtract(center).normalize();
    }


    @Override
    public List<Point> findIntsersections(Ray ray) {

        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        if(p0.equals(center)){ // if p0 equals to center of sphere
            throw new IllegalArgumentException("ray p0 cannot be equals to the center of the sphere");
        }

        Vector u = center.subtract(p0);
        double tm = u.dotProduct(v);
        double d = alignZero(Math.sqrt(u.lengthSquared() - (tm * tm) ));

        if(d >= radius){
            return null; // there is no intersections points
        }

        double th = alignZero(Math.sqrt( (radius * radius) - (d * d) ));

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if(t1 > 0 && t2 > 0){
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);

            return List.of( p1, p2);
        }

        if(t1 > 0){
            return List.of(ray.getPoint(t1));
        }

        if(t2 > 0){
            return List.of(ray.getPoint(t2));
        }

        return null;
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        if(p0.equals(center)){ // if p0 equals to center of sphere
            throw new IllegalArgumentException("ray p0 cannot be equals to the center of the sphere");
        }

        Vector u = center.subtract(p0);
        double tm = u.dotProduct(v);
        double d = alignZero(Math.sqrt(u.lengthSquared() - (tm * tm) ));

        if(d >= radius){
            return null; // there is no intersections points
        }

        double th = alignZero(Math.sqrt( (radius * radius) - (d * d) ));

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if(t1 > 0 && t2 > 0){
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);

            return List.of(new GeoPoint(this, p1), new GeoPoint(this, p2));
        }

        if(t1 > 0){
            return List.of(new GeoPoint(this, ray.getPoint(t1)));
        }

        if(t2 > 0){
            return List.of(new GeoPoint(this, ray.getPoint(t2)));
        }

        return null;    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

}
