package geometriesTests;

import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    // triangle we will use for all tests
    Triangle triangle = new Triangle(
            new Point(2, 0, 0),
            new Point(0, 3, 0),
            new Point(0, 0, 0));
    /**
     * Checking that GetNormal really returns a normal vector
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Checking that GetNormal really returns a normal vector
        Triangle tr = new Triangle(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), tr.getNormal(new Point(0, 0, 1)), "Bad normal to triangle");
    }

    //TC02: Intersects the triangle(1 Point)
    @Test
    void findIntersections1() {
        Ray ray = new Ray(new Point(0, 0, -1), new Vector(1, 1, 1));
        List<Point> result = triangle.findIntsersections(ray);
        assertEquals(1, result.size(), "ERROR: number of intersection points is incorrect");
    }

    //TC02: No intersection with the triangle, intersects outside of edge(0 Point)
    @Test
    void findIntersections2() {
        Ray ray = new Ray(new Point(0, -0.5, 0), new Vector(4, -0.5, 0));
        assertNull(triangle.findIntsersections(ray), "ERROR: number of intersection points is incorrect");
    }

    //TC03: No intersection with triangle, intersects between continuation of edges(0 Point)
    @Test
    void findIntersections3() {
        Ray ray = new Ray(new Point(-0.5, -0.5, 0), new Vector(-1, -1, 0.5));
        assertNull(triangle.findIntsersections(ray), "ERROR: number of intersection points is incorrect");
    }

    // =============== Boundary Values Tests ==================

    //TC04: Not considered intersection with triangle, Intersects On edge(0 Point)
    @Test
    void findIntersections4() {
        Ray ray = new Ray(new Point(0, 0, -1), new Vector(0, 1, 1));
        assertNull(triangle.findIntsersections(ray), "ERROR: number of intersection points is incorrect");
    }

    //TC05: No intersection with triangle, Intersects on vertex(0 Point)
    @Test
    void findIntersections5() {
        Ray ray = new Ray(new Point(2, 3, 0), new Vector(-1, 3, 0));
        assertNull(triangle.findIntsersections(ray), "ERROR: number of intersection points is incorrect");
    }

    //TC06: No intersection with triangle, Intersects On an edge's continuation(0 Point)
    @Test
    void findIntersections6() {
        Ray ray = new Ray(new Point(0, 4, 0), new Vector(0, 3.5, 0));
        assertNull(triangle.findIntsersections(ray), "ERROR: number of intersection points is incorrect");
    }
}