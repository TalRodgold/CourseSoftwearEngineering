package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Interface for all geometries with one func
 */
public interface Geometry extends Intersectable {
    public Vector getNormal(Point p);
}
