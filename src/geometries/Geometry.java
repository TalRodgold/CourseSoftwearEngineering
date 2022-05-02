package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * Changed to Abstract Class
 */
public abstract class Geometry extends Intersectable {
    /**
     * default values
     */
    protected Color emission = Color.BLACK;
    private Material material = new Material();

    /**
     * set with builder pattern method
     * @param material = Material
     * @return
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     *
     * @return Material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     *
     * @param p = Point
     * @return normal vector
     */
    public abstract Vector getNormal(Point p);

    /**
     *
     * @return Color emission
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * set using builder pattern
     * @param emission = Color
     * @return
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
}
