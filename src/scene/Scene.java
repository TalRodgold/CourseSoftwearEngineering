package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

public class Scene {

    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries;

    public Scene(String name) {
        this.name = name;
        geometries = new Geometries();
    }

    public void setName(String name) {
        this.name = name;
    }

    public Scene setBackground(Color background) {   // returning this scene so we can concatenate the object
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public void setGeometries(Geometries geometries) {
        this.geometries = geometries;
    }
}
