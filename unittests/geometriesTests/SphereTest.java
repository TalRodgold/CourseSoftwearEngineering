package geometriesTests;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {
    /**
     * Checking that GetNormal really returns a normal vector
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Checking that GetNormal really returns a normal vector

        Point p1 =  new Point(0, 0, 1);
        Point p2 = new Point(1, 0, 1);
        Vector v1 = new Vector(1,0,0);
        Sphere sp = new Sphere(p1,1);
        Vector normal = sp.getNormal(p2);
        assertEquals(v1, normal);
    }

    Sphere sphere = new Sphere(new Point(1, 0, 0), 1);

    // TC02: case where the ray starts outside the sphere and hase 2 intersections
    @Test
    void testFindIntersections1(){
        Ray ray = new Ray(new Point(4, 0, 0), new Vector(-4, 0, 0));
        List<Point> result = sphere.findIntersections(ray);
        assertEquals(2, result.size(), "ERROR: number of intersection points is incorrect");
    }

    // TC03: case where the ray starts inside the sphere and hase 1 intersections
    @Test
    void testFindIntersections2(){
        Ray ray = new Ray(new Point(0.5,0,0), new Vector(1.5,0,0));
        List<Point> result = sphere.findIntersections(ray);
        assertEquals(1, result.size(), "ERROR: number of intersection points is incorrect");
    }

    // TC04: case where the ray is outside the sphere but is not parallel to it and has 0 intersections
    @Test
    void testFindIntersections3() {
        Ray ray = new Ray(new Point(4, 0, 0), new Vector(4, 0, 0));
        List<Point> result = sphere.findIntersections(ray);
        assertNull(result, "ERROR: number of intersection points is incorrect");
    }

    // TC05: case where the ray is outside the sphere but is parallel to it and has 0 intersections
    @Test
    void testFindIntersections4(){
        Ray ray = new Ray(new Point(3,0,0), new Vector(1,0,0));
        List<Point> result = sphere.findIntersections(ray);
        assertNull(result, "ERROR: number of intersection points is incorrect");
    }

    // =============== Boundary Values Tests ==================//
    // TC06: case where the ray starts on the sphere and goes outside and has 0 intersections
    @Test
    void testFindIntersections5(){
        Ray ray = new Ray(new Point(1,1,0), new Vector(2,2,0));
        List<Point> result = sphere.findIntersections(ray);
        assertNull(result, "ERROR: number of intersection points is incorrect");
    }

    // TC07: case where the ray starts on the sphere and goes inside and has 1 intersections
    @Test
    void testFindIntersections6(){
        Ray ray = new Ray(new Point(0,0,0), new Vector(2,2,0));
        List<Point> result = sphere.findIntersections(ray);
        assertEquals(1 ,result.size(),"ERROR: number of intersection points is incorrect");
    }

    // TC08: case where the ray starts on the sphere and goes outside and has 0 intersections
    @Test
    void testFindIntersections7(){
        Ray ray = new Ray(new Point(2,0,0), new Vector(2,0,0));
        List<Point> result = sphere.findIntersections(ray);
        assertNull(result,"ERROR: number of intersection points is incorrect");
    }

    // TC09: case where the ray starts inside and has 1 intersections
    @Test
    void testFindIntersections8() {
        Ray ray = new Ray(new Point(0.59, 0, 0), new Vector(-0.59, 0, 0));
        List<Point> result = sphere.findIntersections(ray);
        assertEquals(1 ,result.size(),"ERROR: number of intersection points is incorrect");
    }

    // TC10: case where the ray starts outside the sphere and has 0 intersections
    @Test
    void testFindIntersections9(){
        Ray ray = new Ray(new Point(3,0,0), new Vector(1,0,0));
        List<Point> result = sphere.findIntersections(ray);
        assertNull(result,"ERROR: number of intersection points is incorrect");
    }

    // TC11: case where the ray starts in the center of the sphere and has 1 intersections
    @Test
    void testFindIntersections10(){
        Ray ray = new Ray(new Point(1,0,0), new Vector(2,2,0));
        assertThrows(IllegalArgumentException.class, ()-> sphere.findIntersections(ray));
    }

    // TC12: case where the ray starts on the sphere and goes inside and has 1 intersections
    @Test
    void testFindIntersections11() {
        Ray ray = new Ray(new Point(2, 0, 0), new Vector(-1, 0, 0));
        List<Point> result = sphere.findIntersections(ray);
        assertEquals(1 ,result.size(),"ERROR: number of intersection points is incorrect");
    }

    // TC13: case where the ray starts outside the sphere and has 2 intersections
    @Test
    void testFindIntersections12(){
        Ray ray = new Ray(new Point(0,-2,0), new Vector(2,2,0));
        List<Point> result = sphere.findIntersections(ray);
        assertEquals(2, result.size());
    }

    // TC14: case where the ray starts before the point of contact has one point of contact with the sphere and has 0 intersections
    @Test
    void testFindIntersections13(){
        Ray ray = new Ray(new Point(2,1,1), new Vector(-1,-1,0));
        List<Point> result = sphere.findIntersections(ray);
        assertNull(result,"ERROR: number of intersection points is incorrect");
    }

    // TC15: case where the ray starts on the point of contact and has 0 intersections
    @Test
    void testFindIntersections14(){
        Ray ray = new Ray(new Point(1,0,1), new Vector(1,1,0));
        List<Point> result = sphere.findIntersections(ray);
        assertNull(result,"ERROR: number of intersection points is incorrect");
    }

    // TC16: case where the ray starts after the point of contact with the sphere and has 0 intersections
    @Test
    void testFindIntersections15(){
        Ray ray = new Ray(new Point(2,1,1), new Vector(4,4,0));
        List<Point> result = sphere.findIntersections(ray);
        assertNull(result,"ERROR: number of intersection points is incorrect");
    }

    // TC17: case where the ray is orthogonal to the ray that starts at the sphere's center point and has 0 intersections
    @Test
    void testFindIntersections16(){
        Ray ray = new Ray(new Point(3,0,0), new Vector(0,0,1));
        List<Point> result = sphere.findIntersections(ray);
        assertNull(result,"ERROR: number of intersection points is incorrect");
    }
}