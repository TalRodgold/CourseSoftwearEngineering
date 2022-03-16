package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable{
    protected List<Intersectable> inter;

    public Geometries() {
        this.inter = new ArrayList<Intersectable>();
    }

    public Geometries(Intersectable... geometries){
        this.inter = List.of(geometries);
    }

    public void add(Intersectable... geometries){

    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        List<Point> result = null;
        for(var item : inter){
            List<Point>  itemPoints = item.findIntsersections(ray);
            if(itemPoints != null){
                if(result == null){
                    result = new ArrayList<>();
                }
                result.addAll(itemPoints);
            }
        }
        return result;
    }
}

