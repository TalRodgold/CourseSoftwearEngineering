package primitives;
import java.util.List;
import geometries.Intersectable.GeoPoint;

/**
 * class for a ray
 */
public class Ray {

    private Point p0;
    private Vector dir;

    /**
     * constructor
     * @param p0 point
     * @param dir vector
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * get point
     * @return Point
     */
    public Point getP0() {
        return p0;
    }

    /**
     * get vector
     * @return Vector
     */
    public Vector getDir() {
        return dir;
    }

    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    public  GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList){
        if (geoPointList.size() == 0){
            return null;
        }
        if (geoPointList.size() == 1){
            return geoPointList.get(0);
        }
        GeoPoint closest = geoPointList.get(0);
        for (int i = 1; i < geoPointList.size(); i++) {
            if (closest.point.distance(p0) > geoPointList.get(i).point.distance(p0)){
                closest = geoPointList.get(i);
            }
        }
        return closest;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

     public Point getPoint(double t){
        return p0.add(dir.scale(t));
     }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}
