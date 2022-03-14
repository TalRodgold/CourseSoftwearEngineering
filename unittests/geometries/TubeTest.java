package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class TubeTest {
    /**
     * Checking that there is no 90 degree
     */
    @Test
    void test90Degree(){
        Ray ray = new Ray(new Point(0,0,0), new Vector(1,0,0));
        Point p = new Point(0,0,0);
        Vector n = new Vector(1,0,0).subtract(p).normalize();
        assertNotEquals(p,n,"Failed there is 90 degrees angle");
    }

    /**
     * Checking that GetNormal really returns a normal vector
     */
    @Test
    void getNormal() {
        Ray ray = new Ray(new Point(0,1,0), new Vector(0,1,0));
        Tube tb = new Tube(ray, 2);
        assertEquals(tb.getNormal(new Point(0,0,0)) ,new Vector(0,-1,0));
    }
}