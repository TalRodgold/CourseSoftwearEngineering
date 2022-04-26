import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import renderer.Camera;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class integrationTests {
    // construct a camera
    static private Point ZERO_POINT = new Point(0, 0, 0);
    Camera camera = new Camera(ZERO_POINT,
            new Vector(0, 0, -1),
            new Vector(0, 1, 0))
            .setVPSize(3,3)
            .setVPDistance(2);
    LinkedList<Ray> rayList = constructListOfRays(camera, 3, 3); // create rays for each pixel
    // create shapes
    Sphere sphere;
    Plane plane;
    Triangle triangle;


    private LinkedList<Ray> constructListOfRays(Camera camera, int nX, int nY){ // construct a ray for each pixel
    LinkedList<Ray> rayList = new LinkedList<Ray>(); // new list
    for (int j = 0; j < nY; j++){ // for every colum
        for (int i = 0; i < nX; i++){ // for every row
            rayList.add(camera.constructRay(nX,nY,j,i)); // add ray to list
        }
    }
     return rayList;
    }

    private int countIntersections(LinkedList<Ray> list, Geometry shape){ // count how many rays intersected the shape
        int counter = 0; // counter
        for (Ray r: list) { // for each ray in list of rays
            if (shape.findIntsersections(r) != null) // if there was an intersection
            counter += shape.findIntsersections(r).size(); // add to counter
        }
        return counter; // return number of intersections
    }

    /**
     * All the cases of camera and sphere intersection
     */
    @Test
    void testCameraSphereIntersections(){
        // #1: 2 intersection points between camera rays and sphere
        sphere = new Sphere(new Point(0, 0, -3), 1);
        assertEquals(2,countIntersections(rayList, sphere), "ERROR: Wrong number of intersections of camera rays with sphere");

        // #2: 18 intersection points between camera rays and sphere
        sphere = new Sphere(new Point(0, 0, -3), 2);
        assertEquals(18,countIntersections(rayList, sphere),"ERROR: Wrong number of intersections of camera rays with sphere");

        // #3: 10 intersection points between camera rays and sphere
        sphere = new Sphere(new Point(0, 0, -3), 1.5);
        assertEquals(10,countIntersections(rayList, sphere),"ERROR: Wrong number of intersections of camera rays with sphere");

        // #4: 9 intersection points between camera rays and sphere
        sphere = new Sphere(new Point(0, 0, -3), 4);
        assertEquals(9,countIntersections(rayList, sphere),"ERROR: Wrong number of intersections of camera rays with sphere");

        // #5: 0 intersection points between camera rays and sphere
        sphere = new Sphere(new Point(0, 0, 1), 0.5);
        assertEquals(0,countIntersections(rayList, sphere),"ERROR: Wrong number of intersections of camera rays with sphere");

    }

    /**
     * All the cases of camera and plane intersection
     */
    @Test
    void testCameraPlaneIntersections(){
        // #1: 9 intersection points between camera rays and plan
        plane = new Plane(new Point(0, 0, -4), camera.getvTo());
        assertEquals( 9, countIntersections(rayList, plane),"ERROR: Wrong number of intersections of camera rays with plane");

        // #2: 9 intersection points between camera rays and plane
        plane = new Plane(new Point(0, 0, -4), new Vector(new Double3(0, -0.5, 1)));
        assertEquals( 9, countIntersections(rayList, plane),"ERROR: Wrong number of intersections of camera rays with plane");

        // #3: 6 intersection points between camera rays and plan
        plane = new Plane(new Point(0, 0, -4), new Vector(new Double3(0, -2, 1)));
        assertEquals( 6, countIntersections(rayList, plane),"ERROR: Wrong number of intersections of camera rays with plane");
    }
    /**
     * All the cases of camera and triangle intersection
     */
    @Test
    void testCameraTriangleIntersections(){
            // #1: 1 intersection points between camera rays and triangle
            triangle = new Triangle(new Point(0, 1, -2),
                    new Point(1, -1, -2),
                    new Point(-1, -1, -2));
            assertEquals( 1, countIntersections(rayList, triangle),"ERROR: Wrong number of intersections of camera rays with triangle");

            // #2: 2 intersection points between camera rays and triangle
            triangle = new Triangle(new Point(0, 20, -2),
                    new Point(1, -1, -2),
                    new Point(-1, -1, -2));
            assertEquals(2, countIntersections(rayList, triangle),"ERROR: Wrong number of intersections of camera rays with triangle");
    }
}
