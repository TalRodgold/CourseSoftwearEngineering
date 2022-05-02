package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;

public class SpotLight extends PointLight{

    private Vector direction;
    private double narrowBeam = 1d; // for bonus

    /**
     * constructor. sending to father and normalizing the vector
     * @param intensity = Color
     * @param position = Point
     * @param direction = Vector
     */
    public SpotLight(Color intensity, Point position , Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * Bonus! setter builder pattern
     * @param narrowBeam = double
     * @return
     */
    public SpotLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }

    /**
     *
     * @param p = Point
     * @return intensity according to Point.
     * narrowbeam is bonus!
     */
    @Override
    public Color getIntensity(Point p) {
        double dotProduct = alignZero(direction.dotProduct(super.getL(p)));
        if (dotProduct <= 0) // if dot product is 0 return default black color
            return Color.BLACK;
        dotProduct= Math.pow(dotProduct,narrowBeam); // if narrow beam is default won't affect the calc
        return super.getIntensity(p).scale(dotProduct);
    }
}

