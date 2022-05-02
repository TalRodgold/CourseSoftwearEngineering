package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{

    private Vector direction;

    /**
     * We will send intensity to father ctor and normalize the vector
     * @param intensity = Color
     * @param direction = Vector
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     *
     * @param p = Point
     * @return The Color
     */
    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    /**
     *
     * @param p = Point
     * @return The direction of the Vector
     */
    @Override
    public Vector getL(Point p) {
        return direction;
    }
}
