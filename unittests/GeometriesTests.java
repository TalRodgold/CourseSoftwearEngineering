import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;
import static org.junit.jupiter.api.Assertions.*;

public class GeometriesTests {
            Plane p = new Plane(
                    new Point(-2,0,0),
                    new Point(0,0,4),
                    new Point(-2,-2,0));
            Sphere s = new Sphere(new Point(1, 1, 1),1 );
           Triangle t = new Triangle(
                    new Point(-1,1,0),
                    new Point(-1.5, 0, 0),
                    new Point(0, 0.5,2));
    Geometries gmo = new Geometries(p,s,t);


    // 01 There are no Shapes
    @Test
    void findIntersections1() {
        Geometries g = new Geometries();
        Ray ray = new Ray(new Point(-4,0.5,1), new Vector(-5,0,1));
        assertNull(g.findIntsersections(ray), "Failed to find Intersections when there are no shapes");
    }


    // 02 ray doesn't intersect any geometries
    @Test
    void findIntersections2() {
        Ray ray = new Ray(new Point(-4,0.5,1), new Vector(-5,0,1));
        assertNull(gmo.findIntsersections(ray), "Failed to find Intersections when ray doesn't intersect any geometries");
    }


    // 03 ray intersects only one of the geometries
    @Test
    void findIntersections3() {
        Ray ray = new Ray(new Point(-1.2,0.5,1), new Vector(-5,0,1));
        assertEquals(1, gmo.findIntsersections(ray).size(), "Failed to find Intersections when ray intersects only one of the geometries");
    }


    // 04 ray intersects some geometries but not all
    @Test
    void findIntersections4() {
        Ray ray = new Ray(new Point(-0.5,0.5,1), new Vector(-5,0,1));
        assertEquals(2, gmo.findIntsersections(ray).size(), "Failed to find Intersections when ray intersects some geometries but not all");
    }


    // 05 ray intersects all the geometries
    @Test
    void findIntersections5() {
        Ray ray = new Ray(new Point(1,0.5,1), new Vector(-5,0,1));
        assertEquals(3, gmo.findIntsersections(ray).size(), "Failed to find Intersections when ray intersects all the geometries");
    }
}
