package geometries;

import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable{
    protected List<Intersectable> inter;

    /**
     * default constructor
     */
    public Geometries() {
        this.inter = new LinkedList<Intersectable>();
    }

    /**
     * constructor
     * @param geometries
     */
    public Geometries(Intersectable... geometries){
        this.inter = List.of(geometries);
    }

    /**
     * adding geo to our list
     * @param geometries
     */
    public void add(Intersectable... geometries){
    this.inter.addAll(Arrays.asList(geometries));
    }

    /**
     * Helper func that returns List of Geo points
     * @param ray = Ray
     * @return List of Geo points
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> intersections = null;
        for(var item : inter){
            List<GeoPoint>  itemPoints = item.findGeoIntersections(ray);
            if(itemPoints != null){
                if(intersections == null){
                    intersections = new LinkedList();
                }
                intersections.addAll(itemPoints);
            }
        }
        return intersections;
    }
}

