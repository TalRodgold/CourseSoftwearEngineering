package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * abstract class for ray tracer base
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * constructor
     * @param scene = Scene
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * abstract func will be implemented in inherited class's
     * @param ray = Ray
     * @return
     */
    public abstract Color traceRay(Ray ray);
}
