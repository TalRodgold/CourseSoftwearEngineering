package geometriesTests;

import primitivesTests.Point;
import primitivesTests.Ray;

import java.util.List;

/**
 * Interface for all intersections
 */
public interface Intersectable {
    public List<Point> findIntsersections(Ray ray);
}
