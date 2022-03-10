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
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}
