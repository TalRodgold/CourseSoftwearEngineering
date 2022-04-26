package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

import java.util.List;

/**
 * class Triangle is type of Polygon
 */
public class Triangle extends Polygon{
    /**
     * ctor calls father ctor
     * @param vertices 3 points
     */
    public Triangle(Point... vertices) {
        super(vertices);
    }

    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        Point p0 = ray.getP0(); // get point
        Vector v = ray.getDir(); // get direction
        List<Point> trianglePlane = plane.findIntsersections(ray); // get the plane of the triangle

        if(trianglePlane == null){ // if there is no intersection between plane and ray then of course there won't be an intersection between ray and triangle
            return null;
        }

        Vector v1 = this.vertices.get(0).subtract(p0);
        Vector v2 = this.vertices.get(1).subtract(p0);
        Vector v3 = this.vertices.get(2).subtract(p0);
        Vector v4 = v1.crossProduct(v2).normalize();
        Vector v5 = v2.crossProduct(v3).normalize();
        Vector v6 = v3.crossProduct(v1).normalize();

        double x1 = alignZero(v.dotProduct(v4));
        double x2 = alignZero(v.dotProduct(v5));
        double x3 = alignZero(v.dotProduct(v6));

        boolean allNegative = x1 < 0 && x2 < 0 && x3 < 0;
        boolean allPositive = x1 > 0 && x2 > 0 && x3 > 0;

        if(allNegative || allPositive){
            return List.of(trianglePlane.get(0)); // return the intersections with the plane that the triangle is on
        }

        return null; // else
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }
}
