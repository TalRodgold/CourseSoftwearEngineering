package primitives;

/**
 *  This class will serve all primitive classes based on a point
 */
public class Point {

    public static final Point ZERO = new Point(0,0,0);
    protected Double3 xyz;

    /**
     * constructor
     * @param xyz a double3 type
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * constructor
     * @param x coordinate x
     * @param y coordinate y
     * @param z coordinate z
     */
    public Point(double x, double y, double z) {
       xyz = new Double3(x,y,z);
    }

    /**
     * new point based on 2 points
     * @param v vector
     * @return Point
     */
    public Point add(Vector v){
       return new Point(xyz.add(v.xyz));
    }

    /**
     * new vector based on 2 points
     * @param p point
     * @return Vector
     */
    public Vector subtract( Point p){
        return new Vector((xyz.subtract(p.xyz)));
        }

    /**
     * calculate distance sqrt
     * @param p point
     * @return double
     */
    public double distanceSquared( Point p){
        return ((this.xyz.d1 - p.xyz.d1) * (this.xyz.d1 - p.xyz.d1)) + ((this.xyz.d2 - p.xyz.d2) * (this.xyz.d2 - p.xyz.d2)) + ((this.xyz.d3 - p.xyz.d3) * (this.xyz.d3 - p.xyz.d3));
    }

    /**
     * calculate distance
     * @param p point
     * @return double
     */
    public double distance(Point p){
        return Math.sqrt(this.distanceSquared(p));
    }

    public double getX(){return xyz.d1;}

    public double getY(){return xyz.d2;}

    public double getZ(){return xyz.d3;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return xyz.equals(point.xyz);
    }


    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }
}
