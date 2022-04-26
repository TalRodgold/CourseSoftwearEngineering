package geometriesTests;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    // plane we will use for all the tests
    Plane plane = new Plane(
            new Point(0, 0, 1),
            new Point(0, 2, 0),
            new Point(1, 0, 0));
    /**
     * Checking if the points are on the same line
     */
    @Test
    void testPointsOnSameLine() {
        // =============== Boundary Values Tests ==================

        // TC10: Points on the same line
        try {
            new Plane(
                    new Point(0, 0, 1),
                    new Point(0, 0, 2),
                    new Point(0, 0, 3));
            fail("constructor crated a plane with 3 point on the same line");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * Checking that GetNormal really returns a normal vector
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane pl = new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));
        assertEquals(new Vector(1/Math.sqrt(3), 1/Math.sqrt(3), 1/Math.sqrt(3)), pl.getNormal(new Point(0, 0, 1)), "Bad normal to plane");
    }
    // TC02: Ray intersects the plane (1 point)
    @Test
    void findIntersections1() {
        Ray ray = new Ray(new Point(0,-2,0), new Vector(1, 4,-1));
        List<Point> result = plane.findIntsersections(ray);
        assertEquals(1, result.size(), "ERROR: number of intersection points is incorrect");
    }

    // TC03: Ray does not intersect the plane at all (0 points)
    @Test
    void findIntersections2() {
        Ray ray = new Ray(new Point(0, 5, 0), new Vector(6,-5,0));
        assertNull(plane.findIntsersections(ray), "ERROR: number of intersection points is incorrect");
    }

    // =============== Boundary Values Tests ==================

    // Ray is parallel to the plane

    // TC04: Ray is included in the plane (0 point)
    @Test
    void findIntersections6() {
        Ray ray = new Ray(new Point(-2.61, 2.64, 2.3), new Vector(0.22,0.26,-0.35));
        assertNull(plane.findIntsersections(ray), "ERROR: number of intersection points is incorrect");
    }

    // TC05: Ray is not included in the plane (0 point)
    @Test
    void findIntersections7() {
        Ray ray = new Ray(new Point(5, 0, 0), new Vector(-0.64,-0.2,0.74));
        assertNull(plane.findIntsersections(ray), "ERROR: number of intersection points is incorrect");
    }


    // Ray is orthogonal to the plane

    // TC06: Ray starts before the plane and intersects the plane (1 point)
    @Test
    void findIntersections3() {
        Ray ray = new Ray(new Point(-2.07, 1.95, 0.61), new Vector(2,1,2));
        List<Point> result = plane.findIntsersections(ray);
        assertEquals(1, result.size(), "ERROR: number of intersection points is incorrect");

    }

    // TC07: Ray starts after the plane and does not intersect (0 point)
    @Test
    void findIntersections4() {
        Ray ray = new Ray(new Point(-2, 0, 0), new Vector(-1.53,-0.77,-1.53));
        assertNull(plane.findIntsersections(ray), "ERROR: number of intersection points is incorrect");
    }

    // TC08: Ray starts on the plane (0 point)
    @Test
    void findIntersections5() {
        Ray ray = new Ray(new Point(0, 0, 1), new Vector(1,0.5,2));
        assertNull(plane.findIntsersections(ray), "ERROR: number of intersection points is incorrect");
    }

    // 2 more checks

    // TC09: Ray is neither orthogonal nor parallel to and begins at the plane
    @Test
    void findIntersections8() {
        Ray ray = new Ray(new Point(-0.73, 0, 1.73), new Vector(-0.64,5.6,-1.73));
        assertNull(plane.findIntsersections(ray), "ERROR: number of intersection points is incorrect");
    }

    // TC10: Ray is neither orthogonal nor parallel to the plane and begins in the
    //same point which appears as reference point in the plane (q0)
    @Test
    void findIntersections9() {
        Ray ray = new Ray(plane.getQ0(), new Vector(-0.64,5.6,-1.73));
        assertNull(plane.findIntsersections(ray), "ERROR: number of intersection points is incorrect");
    }
}