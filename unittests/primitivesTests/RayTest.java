package primitivesTests;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {
    /**
     * Receiving the correct starting point of the ray
     */
    // ============ Equivalence Partitions Tests ==============
    @Test
    void testGetP0() {
        // TC01: test Receiving the correct starting point of the ray
        Point p = new Point(1,2,3);
        Vector v = new Vector(3,4,5);
        Ray r = new Ray(p,v);
        assertEquals(r.getP0(), p, "Get p0 Failed");
    }

    /**
     * Receiving the correct direction of the ray
     */
    @Test
    void testGetDir() {
        // TC02: test Receiving the correct direction of the ray
        Point p = new Point(1,2,3);
        Vector v = new Vector(3,4,5);
        Ray r = new Ray(p,v);
        assertEquals(r.getDir(), v.normalize(), "GetDir Failed");
    }


    Ray ray = new Ray(new Point(2,-2,3), new Vector(-2,-2,-2));

    @Test
    void testFindClosestPoint1(){
        // TC03: test that the closest point is in the middle of the list
        var points = List.of(
                new Point(0,0,1),
                new Point(0,-1,0),
                new Point(1,-2,3),
                new Point(1,1,1),
                new Point(1,2,3));

        assertEquals(points.get(points.size() / 2), ray.findClosestPoint(points));
    }

    // =============== Boundary Values Tests ==================
    // TC04: test if the list is empty
    @Test
    void testFindClosestPoint2() {
        List<Point> points = Collections.emptyList();
        assertNull(ray.findClosestPoint(points));
    }

    // TC05: test if the first point is the closest to p0
    @Test
    void testFindClosestPoint3() {
        var points = List.of(
                new Point(1,-2,3),
                new Point(0,-1,0),
                new Point(1,-2,3),
                new Point(1,1,1),
                new Point(1,2,3));
        assertEquals(points.get(0), ray.findClosestPoint(points));
    }

    // TC06: test if the last point is the closest to p0
    @Test
    void testFindClosestPoint4() {
        var points = List.of(
                new Point(1,-2,3),
                new Point(0,-1,0),
                new Point(1,-2,3),
                new Point(1,1,1),
                new Point(1,-2,3));
        assertEquals(points.get(points.size()-1), ray.findClosestPoint(points));
    }
}