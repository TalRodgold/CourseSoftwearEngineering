package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{

    private Point position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;


    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double dist = p.distance(position);
        if(dist <= 0){
            return getIntensity();
        }
        return getIntensity().reduce(kC + dist * kL + (dist * dist) * kQ); // מהמצגת
    }
    @Override
    public Vector getL(Point p) {
        Vector dir = p.subtract(position);
        return dir.normalize();
    }
}
