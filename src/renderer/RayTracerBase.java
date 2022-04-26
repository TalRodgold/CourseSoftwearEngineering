package renderer;

import primitivesTests.Color;
import primitives.Ray;
import scene.Scene;

/**
 * abstract class for ray tracer base
 */
public abstract class RayTracerBase {
    protected Scene scene;

    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }
    public abstract Color traceRay(Ray ray);
}
