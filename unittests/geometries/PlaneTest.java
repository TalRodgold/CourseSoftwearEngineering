package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
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
}