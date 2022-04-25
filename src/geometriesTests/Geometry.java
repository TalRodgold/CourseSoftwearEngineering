package geometriesTests;

import primitivesTests.Point;
import primitivesTests.Vector;

/**
 * Interface for all geometries with one func
 */
public interface Geometry extends Intersectable {
    public Vector getNormal(Point p);
}
