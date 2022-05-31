/*
import org.junit.jupiter.api.Test;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;
import static java.awt.Color.*;

public class FinalPictureTest {

    Point A = new Point(-1.31,-4.3,0);
    Point B = new Point(-0.27,-4.03,0);
    Point C = new Point(-0.02,-3.1,0);
    Point O = new Point(-1.01,-3.34,0);
    Point D = new Point(-1.01,-3.34,-2.02);
    Point E = new Point(-1.01,-3.34,2.02);

    double raggio = 1.01;
    Sphere sphere_b = new Sphere(O,4.08);
    Cylinder cylinder_d = new Cylinder(new Ray(O,new Vector(0,0,1)),raggio,2.02);
    Plane plane_g = new Plane()


    @Test
    public void finalTest(){
        Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(2500, 2500).setVPDistance(10000); //

        scene.setAmbientLight(new AmbientLight(new Color(white), new Double3(0.15)));

        scene.geometries.add( //
                new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(8, 5, 10)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(new Double3(0.5))),
                new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(255,102,102)) //
                        .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                new Triangle(new Point(15000, -1500, -15000), new Point(-15000, 1500, -15000), new Point(6700, 670, 30000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setkR(new Double3(1))),
                new Triangle(new Point(1500, -15000, -15000), new Point(-1500, 15000, -15000), new Point(670, 6700, 30000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setkR(new Double3(1))),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setkR(new Double3(1))),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setkR(new Double3(0.5))));

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setkL(4E-5).setkQ(2E-7));

        ImageWriter imageWriter = new ImageWriter("TalsBeautifulPic", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(9) //
                .writeToImage();
    }
}
*/
