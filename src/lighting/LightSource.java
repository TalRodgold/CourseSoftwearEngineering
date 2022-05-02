package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public interface LightSource {
    /**
     *
     * @param p = Point
     * @return The Color
     */
    Color getIntensity(Point p);

    /**
     *
     * @param p = Point
     * @return The Vector
     */
    Vector getL(Point p);

}
