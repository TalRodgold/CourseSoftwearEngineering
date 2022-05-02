package lighting;

import primitives.Color;

abstract class Light {
    private Color intensity;

    /**
     * c-tor
     * @param intensity
     */
    public Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     *
     * @return The Color
     */
    public Color getIntensity() {
        return intensity;
    }


}
