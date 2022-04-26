package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.List;

public class RayTracerBasic extends RayTracerBase{
    public RayTracerBasic(Scene scene) {
        super(scene);
    }
    
    private Color calcColor(GeoPoint geoPoint){
        geoPoint.geometry.setEmission(geoPoint.geometry.getEmission());
        return  geoPoint.geometry.getEmission(); // returning the color of the object

    }

    public Color traceRay(Ray ray){
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray); // Looking for intersections between the scene and the ray
        if(intersections == null)
            return scene.background; // if none, then return the color of the background

        GeoPoint closest = ray.findClosestGeoPoint(intersections); // if there are, then find the closest point to start of ray
        return calcColor(closest); // and return that point's color
    }
}
