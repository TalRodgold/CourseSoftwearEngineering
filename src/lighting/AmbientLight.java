package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight extends Light{


    /**
     * intensity's color will be set by the scale of ka
     * @param ia = Color
     * @param ka = Double3
     */
    public AmbientLight(Color ia, Double3 ka) {
        super(ia.scale(ka));
    }

    /**
     * default ctr
     */
    public AmbientLight() {
        super(Color.BLACK);
    }
}
