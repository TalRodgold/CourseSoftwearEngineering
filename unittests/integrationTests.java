import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
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
    LinkedList<Ray> rayList = findRaysThroughVpPixels(camera, 3, 3);
    Sphere sphere;
    Plane plane;
    Triangle triangle;

    String sphereErrorMessage = "ERROR: Wrong number of intersections of camera rays with sphere";
    String planeErrorMessage = "ERROR: Wrong number of intersections of camera rays with plane";
    String triangleErrorMessage = "ERROR: Wrong number of intersections of camera rays with triangle";
    private LinkedList<Ray> findRaysThroughVpPixels(Camera camera, int nX, int nY){
    LinkedList<Ray> rayList = new LinkedList<Ray>();
    for (int j = 0; j < nY; j++){
        for (int i = 0; i < nX; i++){
            rayList.add(camera.constructRay(nX,nY,j,i));
        }
    }
     return rayList;
    }

    private int countIntersections(LinkedList<Ray> list, Geometry shape){
        int counter = 0;
        for (Ray r: list) {
            if (shape.findIntsersections(r) != null)
            counter += shape.findIntsersections(r).size();
        }
        return counter;
    }

    @Test
    void testCameraSphereIntersections(){
        // ** Group: Sphere&Camera integration test cases **//
        // #1: Camera rays intersects 2 points with sphere
        sphere = new Sphere(new Point(0, 0, -3), 1);
        assertEquals(2,countIntersections(rayList, sphere), sphereErrorMessage);

        // #2: Camera rays intersects 18 points with sphere
        sphere = new Sphere(new Point(0, 0, -3), 2);
        assertEquals(18,countIntersections(rayList, sphere),sphereErrorMessage);

        // #3: Camera rays intersects 10 points with sphere
        sphere = new Sphere(new Point(0, 0, -3), 1.5);
        assertEquals(10,countIntersections(rayList, sphere),sphereErrorMessage);

        // #4: Camera rays intersects 9 points with sphere
        sphere = new Sphere(new Point(0, 0, -3), 4);
        assertEquals(9,countIntersections(rayList, sphere),sphereErrorMessage);

        // #5: No camera rays intersection with sphere
        sphere = new Sphere(new Point(0, 0, 1), 0.5);
        assertEquals(0,countIntersections(rayList, sphere),sphereErrorMessage);

    }
    @Test
    void testCameraPlaneIntersections(){
// ** Group: Plane&Camera integration test cases **//
        // #11: Camera intersects 9 points with plan
        plane = new Plane(new Point(0, 0, -4), camera.getvTo());
        assertEquals( 9, countIntersections(rayList, plane),planeErrorMessage);

        // #12: Camera rays intersects 9 points with plan
        plane = new Plane(new Point(0, 0, -4), new Vector(new Double3(0, -0.5, 1)));
        assertEquals( 9, countIntersections(rayList, plane),planeErrorMessage);

        // #13: Camera rays intersects 6 points with plan
        plane = new Plane(new Point(0, 0, -4), new Vector(new Double3(0, -2, 1)));
        assertEquals( 6, countIntersections(rayList, plane),planeErrorMessage);
    }
    @Test
    void testCameraTriangleIntersections(){
            // ** Group: Triangle&Camera integration test cases **//
            // #21: Camera rays intersects 1 points with triangle
            triangle = new Triangle(new Point(0, 1, -2),
                    new Point(1, -1, -2),
                    new Point(-1, -1, -2));
            assertEquals( 1, countIntersections(rayList, triangle),triangleErrorMessage);

            // #22: Camera rays intersects 2 points with triangle
            triangle = new Triangle(new Point(0, 20, -2),
                    new Point(1, -1, -2),
                    new Point(-1, -1, -2));
            assertEquals(2, countIntersections(rayList, triangle),triangleErrorMessage);
    }
}
