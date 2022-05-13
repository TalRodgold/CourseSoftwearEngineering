package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.Objects;

/**
 * Changed to Abstract Class
 */
public abstract class Intersectable {
    /**
     *
     * @param ray = Ray
     * @return List of ray's intersection points
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * calls helper
     * @param ray = Ray
     * @return List of Geo Points
     */
    public List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * will be implemented in inherited class's
     * @param ray = Ray
     * @return
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * Inner Class
     */
    public static class GeoPoint {
        public final Geometry geometry;
        public final Point point;
        public final Vector normal;
        /**
         * Constructor
         * @param geometry = Geometry
         * @param point = Point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
            this.normal = this.geometry.getNormal(this.point);
        }

        /**
         * override of equals
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        /**
         * override of ToString
         * @return
         */
        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

}