package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

public class Scene {
    /**
     * fields with default values
     */
    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries;
    public List<LightSource> lights = new LinkedList<>();

    /**
     * c-tor
     * @param name = string
     */
    public Scene(String name) {
        this.name = name;
        geometries = new Geometries();
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * set using builder pattern method
     * @param background = Color
     * @return
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * set using builder pattern method
     * @param ambientLight = AmbientLight
     * @return Scene
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this; // returning this scene so we can concatenate the object
    }

    /**
     * set using builder pattern method
     * @param geometries = Geometries
     * @return Scene
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this; // returning this scene so we can concatenate the object
    }

    /**
     * set using builder pattern method
     * @param lights = List<LightSource>
     * @return Scene
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

}
