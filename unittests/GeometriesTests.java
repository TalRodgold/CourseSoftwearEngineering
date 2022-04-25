import org.junit.jupiter.api.Test;
import primitivesTests.*;
import geometriesTests.*;
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

    @Test
    void findIntersections1() {
        // =============== Boundary Values Tests ==================
        // TC01: test for case where There are no Shapes
        Geometries g = new Geometries();
        Ray ray = new Ray(new Point(-4,0.5,1), new Vector(-5,0,1));
        assertNull(g.findIntsersections(ray), "Failed to find Intersections when there are no shapes");
    }

    @Test
    void findIntersections2() {
        // TC02 test for case where ray doesn't intersect any geometries
        Ray ray = new Ray(new Point(-4,0.5,1), new Vector(-5,0,1));
        assertNull(gmo.findIntsersections(ray), "Failed to find Intersections when ray doesn't intersect any geometries");
    }


    @Test
    void findIntersections3() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test for case where ray intersects only one of the geometries
        Ray ray = new Ray(new Point(-1.2,0.5,1), new Vector(-5,0,1));
        assertEquals(1, gmo.findIntsersections(ray).size(), "Failed to find Intersections when ray intersects only one of the geometries");
    }

    @Test
    void findIntersections4() {
        // TC02 case where ray intersects some geometries but not all
        Ray ray = new Ray(new Point(-0.5,0.5,1), new Vector(-5,0,1));
        assertEquals(2, gmo.findIntsersections(ray).size(), "Failed to find Intersections when ray intersects some geometries but not all");
    }


    @Test
    void findIntersections5() {
        // TC03 case where ray intersects all the geometries
        Ray ray = new Ray(new Point(1,0.5,1), new Vector(-5,0,1));
        assertEquals(3, gmo.findIntsersections(ray).size(), "Failed to find Intersections when ray intersects all the geometries");
    }
}
