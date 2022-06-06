

import geometries.*;
import lighting.AmbientLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

public class FinalPictureTest {
    Scene scene = new Scene("Test scene SnookerBalls");
    Camera camera = new Camera(
            new Point(40,-700,30),
            new Vector(0,1,0),
            new Vector(0,0,1))
            .setVPSize(200, 200)
            .setVPDistance(800)
            .setRayTracer(new RayTracerBasic(scene));


    /**
     * Produce a picture of a two triangles lighted by a spot light with a Sphere
     * producing a shading
     */
    @Test
    public void trianglesSphere() {
        Material material = new Material().setkD(0.5).setkS(0.2).setnShininess(30).setkR(new Double3(0.8));
        scene.geometries.add(
                new Polygon(
                        new Point(-500,500,-40),
                        new Point(500,500,-40),
                        new Point(500,-500,-40),
                        new Point(-500,-500,-40))
                        .setEmission(new Color(java.awt.Color.GRAY).reduce(30)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.2).setnShininess(30)));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));
        for (int i = 0; i < 5; i++) {
            int min = 50;
            int max = 100;
            int red = (int)Math.floor(Math.random()*(max-min+1)+min);
            int green = (int)Math.floor(Math.random()*(max-min+1)+min);
            int blue = (int)Math.floor(Math.random()*(max-min+1)+min);
            scene.geometries.add(
                    new Sphere(new Point(i*25, i*70, 0), 35d) //
                            .setEmission(new Color(220, 100+green, 100+blue).reduce(3))
                            .setMaterial(material));
        }

        scene.lights.add( //
                new PointLight(new Color(700, 400, 400), new Point(30, -90, 150)) //
                        .setkL(4E-4).setkQ(2E-5));

        camera.setImageWriter(new ImageWriter("Binyamin'sBeautifulPic", 600, 600)) //
                .renderImage();
        camera.writeToImage();
    }
}
