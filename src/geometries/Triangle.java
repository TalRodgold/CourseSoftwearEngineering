package geometries;

import primitives.Point;
import primitives.Vector;

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

    /**
     *
     * @param point Point
     * @return calls the father getNormal func
     */
    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }
}
