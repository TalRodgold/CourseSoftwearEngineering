package primitives;
import java.util.List;
import geometries.Intersectable.GeoPoint;

/**
 * class for a ray
 */
public class Ray {

    private static final double DELTA = 0.1;
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
     * constructing reflected and refracted ray
     * @param p Point
     * @param d Vector
     * @param n Vector
     */
    public  Ray( Point p, Vector d, Vector n){
        double delta = n.dotProduct(d) >= 0d ? DELTA : - DELTA;
        p0 = p.add(n.scale(delta));
        dir = d.normalize();
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

    /**
     *
     * @param points = List<Point>
     * @return calls findClosestGeoPoint
     */
    public Point findClosestPoint(List<Point> points) {
        if (points == null || points.isEmpty())
            return null;
        return findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     *
     * @param geoPointList = List<GeoPoint>
     * @return closest GeoPoint to p0
     */
    public  GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList){
        if (geoPointList == null){
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

    /**
     *
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     *
     * @param t = double
     * @return the point after adding t to p0
     */
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
