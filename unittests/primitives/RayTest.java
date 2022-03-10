package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void getP0() {
        Point p = new Point(1,2,3);
        Vector v = new Vector(3,4,5);
        Ray r = new Ray(p,v);
        assertEquals(r.getP0(), p, "Get p0 Failed");
    }

    @Test
    void getDir() {
        Point p = new Point(1,2,3);
        Vector v = new Vector(3,4,5);
        Ray r = new Ray(p,v);
        assertEquals(r.getDir(), v.normalize(), "GetDir Failed");
    }
}