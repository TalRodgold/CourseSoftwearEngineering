package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Interface for all intersections
 */
public interface Intersectable {
    public List<Point> findIntsersections(Ray ray);
}
