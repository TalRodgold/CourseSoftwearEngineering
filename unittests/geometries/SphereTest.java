package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal() {
        Point p1 =  new Point(0, 0, 1);
        Point p2 = new Point(1, 0, 1);
        Vector v1 = new Vector(1,0,0);
        Sphere sp = new Sphere(p1,1);
        Vector normal = sp.getNormal(p2);
        assertEquals(v1, normal);
    }
}