package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    /**
     * Adding a vector to a point and getting correct point
     */
    @Test
    void testAdd() {
        Point p1 = new Point(1,1,1);
        Vector v1 = new Vector(2,2,2);
        Point p2 = new Point(3,3,3);
        assertEquals(p1.add(v1), p2, "Add point to vector Failed");
    }

    /**
     * Subtracting a point from a point and getting correct vector
     */
    @Test
    void testSubtract() {
        Point p1 = new Point(1,1,1);
        Point p2 = new Point(3,3,3);
        Vector v1 = new Vector(2,2,2);
        assertEquals(p2.subtract(p1), v1, "Subtract point from point Failed");
    }

    /**
     * Distance squared between points
     */
    @Test
    void testDistanceSquared() {
        Point p1 = new Point(1,1,1);
        Point p2 = new Point(2,2,2);
        double d1 = 3;
        assertEquals(p2.distanceSquared(p1), d1, "Distance squared from point to point Failed");

    }

    /**
     * Distance between points
     */
    @Test
    void testDistance() {
        Point p1 = new Point(1,1,1);
        Point p2 = new Point(2,2,2);
        double d1 = 3;
        assertEquals(p2.distanceSquared(p1), d1, "Distance from point to point Failed");
    }
}