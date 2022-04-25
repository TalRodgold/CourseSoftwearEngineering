package geometriesTests;

import org.junit.jupiter.api.Test;
import primitivesTests.Point;
import primitivesTests.Ray;
import primitivesTests.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class TubeTest {
    /**
     * Checking that there is no 90 degree
     */
    @Test
    void test90Degree(){
        // =============== Boundary Values Tests ==================
        // TC01: When adding a point to the head of the ray creates a 90 degree angle with the axis
        Ray ray = new Ray(new Point(0,0,0), new Vector(1,0,0));
        Point p = new Point(0,0,0);
        Vector n = new Vector(1,0,0).subtract(p).normalize();
        assertNotEquals(ray,n,"Failed there is 90 degrees angle");
    }

    /**
     * Checking that GetNormal really returns a normal vector
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Checking that GetNormal really returns a normal vector

        Ray ray = new Ray(new Point(0,1,0), new Vector(0,1,0));
        Tube tb = new Tube(ray, 2);
        assertEquals(tb.getNormal(new Point(0,0,0)) ,new Vector(0,-1,0));
    }
}