package primitives;
import java.util.Objects;

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
