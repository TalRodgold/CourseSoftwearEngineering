package lighting;

import primitives.Color;
import primitives.Double3;
import primitivesTests.Color;
import primitivesTests.Double3;

public class AmbientLight {

    private Color intensity;

    /**
     * intensity's color will be set by the scale of ka
     * @param ia = Color
     * @param ka = Double3
     */
    public AmbientLight(Color ia, Double3 ka) {
       intensity = ia.scale(ka);
    }

    /**
     * default ctr
     */
    public AmbientLight() {
        this.intensity = Color.BLACK;
    }

    /**
     * get intensity
     * @return Color
     */
    public Color getIntensity() {
        return intensity;
    }
}
