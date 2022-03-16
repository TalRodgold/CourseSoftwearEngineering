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
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        var result = plane.findIntsersections(ray);

        // if there is no intersections with the plane is a fortiori (kal&homer)
        // that there is no intersections with the triangle
        if(result == null){
            return null;
        }

        Vector v1 = this.vertices.get(0).subtract(p0),
                v2 = this.vertices.get(1).subtract(p0),
                v3 = this.vertices.get(2).subtract(p0);

        Vector n1 = v1.crossProduct(v2).normalize(),
                n2 = v2.crossProduct(v3).normalize(),
                n3 = v3.crossProduct(v1).normalize();

        double x1 = alignZero(v.dotProduct(n1)),
                x2 = alignZero(v.dotProduct(n2)),
                x3 = alignZero(v.dotProduct(n3));

        boolean allNegative = x1 < 0 && x2 < 0 && x3 < 0;
        boolean allPositive = x1 > 0 && x2 > 0 && x3 > 0;

        if(allNegative || allPositive){
            return List.of(result.get(0)); // return the intersections with the plane that the triangle is on
        }

        return null;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }
}
