package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;

public class SpotLight extends PointLight{

    private Vector direction;
    private double narrowBeam = 1d;

    public SpotLight(Color intensity, Point position , Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    public SpotLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double dotProduct = alignZero(direction.dotProduct(super.getL(p)));
        if (dotProduct <= 0)
            return Color.BLACK;
        if(narrowBeam!=1)
            dotProduct= Math.pow(dotProduct,narrowBeam);
        return super.getIntensity(p).scale(dotProduct);
    }
}

