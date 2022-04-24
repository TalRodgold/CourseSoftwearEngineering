package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase{
    public RayTracerBasic(Scene scene) {
        super(scene);
    }
    private Color calcColor(Point point){
        return scene.ambientLight.getIntensity(); // returning the color of the ambient light
    }

    public Color traceRay(Ray ray){
        List<Point> intersections = scene.geometries.findIntsersections(ray); // Looking for intersections between the scene and the ray
        if(intersections == null)
            return scene.background; // if none, then return the color of the background

        Point closest = ray.findClosestPoint(intersections); // if there are, then find the closest point to start of ray
        return calcColor(closest); // and return that point's color
    }
}
