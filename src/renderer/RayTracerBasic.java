package renderer;

import geometries.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.List;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

import primitives.Color;

public class RayTracerBasic extends RayTracerBase {

    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = new Double3(1.0);

    /**
     * C-tor calls father RayTracerBase
     *
     * @param scene = Scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

  /* private Ray constructRefractedRay(Point p, Vector v, Vector n){
        return new Ray(p.add(n.scale(-DELTA)),v);
    }

    private Ray constructReflectedRay(Point p, Vector v, Vector n){
        return new Ray(p.add(n.scale(DELTA)), v.subtract(n.scale(2 * v.dotProduct(n))));
    }*/

    private GeoPoint findClosestIntersection(Ray ray) {
        if(ray == null){
            return null;
        }
        List<GeoPoint> points = scene.geometries.findGeoIntersections(ray);
        return ray.findClosestGeoPoint(points);
    }

    /**
     * function for adding shade based on the presentation
     *
     * @param gp
     * @param l
     * @param n
     * @return bool
     */
    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector;
        if (n.dotProduct(lightDirection) > 0) { // if bigger than 0
            epsVector = n.scale(DELTA);
        } else epsVector = n.scale(-DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) {
            return true;
        }
        double dis = light.getDistance(gp.point);
        for (var element : intersections) {
            if (gp.point.distance(element.point) < dis) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param gp = GeoPoint
     * @param ray          = Ray
     * @return color of ambient light after adding color according to intersection and ray
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = gp.geometry.getEmission().add(calcLocalEffects(gp, ray, k)); // do we need to send k?
        if (level == 1){
            return color;
        }
        return color.add(calcGlobalEffects(gp, ray, level, k));
    }

    private Color calcColor(GeoPoint gp, Ray ray){
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {

        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDir().subtract(n.scale(ray.getDir().dotProduct(n) * 2));
        Double3 kr =  gp.geometry.getMaterial().kR;
        Double3 kkr = kr.product(k);
        if (kkr.biggerThan(MIN_CALC_COLOR_K)) {
            color = color.add(calcGlobalEffect(new Ray(gp.point, v, n), level, kr, kkr));
        }
        Double3 kt = gp.geometry.getMaterial().kT;
        Double3 kkt = kt.product(k);
        if (kkt.biggerThan(MIN_CALC_COLOR_K)) {
            color = color.add(calcGlobalEffect(new Ray(gp.point, ray.getDir(), n), level, kt, kkt)); //// changed here!!!
        }
        return color;



      /*  Color color = Color.BLACK;
        Double3 kr =  gp.geometry.getMaterial().kR;
        Double3 kkr = kr.product(k);
        if (kkr.biggerThan(MIN_CALC_COLOR_K)) {
            color = color.add(calcGlobalEffect(constructReflectedRay(gp.point, ray.getDir(), gp.geometry.getNormal(gp.point)), level, kr, kkr));
        }
        Double3 kt = gp.geometry.getMaterial().kT;
        Double3 kkt = kt.product(k);
        if (kkt.biggerThan(MIN_CALC_COLOR_K)) {
            color = color.add(calcGlobalEffect(constructRefractedRay(gp.point, ray.getDir(), gp.geometry.getNormal(gp.point)), level, kt, kkt));
        }
        return color;*/
    }

    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection (ray);
        return ((gp == null ? scene.background : calcColor(gp, ray, level-1, kkx)
        ).scale(kx));
    }


    // in unshaded only obj that their kt == 0 will cause shadowing


    /**
     * took from presentation in moodle
     *
     * @param intersection = GeoPoint
     * @param ray          = Ray
     * @return color while calculating the diffusive and specular effects
     */
    private Color calcLocalEffects(Intersectable.GeoPoint intersection, Ray ray, Double3 k) {
      /*  Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().nShininess;

        Double3 kd = intersection.geometry.getMaterial().kD;
        Double3 ks = intersection.geometry.getMaterial().kS;


        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point).normalize();
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // checks if nl == nv
                if (unshaded(intersection, lightSource, l, n)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;*/
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().nShininess;

        Double3 kd = intersection.geometry.getMaterial().kD;
        Double3 ks = intersection.geometry.getMaterial().kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // checks if nl == nv
                //if (unshaded(intersection,l,n,nv,lightSource)) {
                Double3 ktr = transparency(intersection, l,lightSource);
                if (ktr.product(k).biggerThan(MIN_CALC_COLOR_K) ){
                    Color lightIntensity = lightSource.getIntensity(intersection.point);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * Calculates diffusive light
     *
     * @param kd             = Double3
     * @param l              = Vector
     * @param n              = Vector
     * @param lightIntensity = Color
     * @return The color of diffusive effects
     */
    private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
        double ln = alignZero(l.dotProduct(n));
        if (ln < 0)
            ln = ln * -1;
        return lightIntensity.scale(kd.scale(ln));
    }

    /**
     * Calculate specular light
     *
     * @param ks             = Double3
     * @param l              = Vector
     * @param n              = Vector
     * @param v              = Vector
     * @param nShininess     = int
     * @param lightIntensity = Color
     * @return The color of specular reflection
     */
    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
        double vr = alignZero(v.scale(-1).dotProduct(r));
        if (vr < 0)
            vr = 0;
        vr = Math.pow(vr, nShininess);
        return lightIntensity.scale(ks.scale(vr));
    }

    /**
     * @param ray = Ray
     * @return color of closest point to p0
     */
    public Color traceRay(Ray ray) {
  /*      List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray); // Looking for intersections between the scene and the ray
        if (intersections == null)
            return scene.background; // if none, then return the color of the background
        GeoPoint closest = findClosestIntersection(ray);
        //GeoPoint closest = ray.findClosestGeoPoint(intersections); // if there are, then find the closest point to start of ray
        return calcColor(closest, ray); // and return that point's color*/

        GeoPoint closest = findClosestIntersection(ray);
        if(closest==null)
            return  scene.background;
        return calcColor(closest,ray);
    }

    private Double3 transparency(GeoPoint geoPoint , Vector l, LightSource lightSource ){
        Vector lightDirection = l.scale(-1); // from point to light source
        // create a new ray that is sent from point to the light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, geoPoint.normal);
        // check if another geometry is blocking us by finding intersections
        var intersections = scene.geometries.findGeoIntersections(lightRay);

        if (intersections == null){
            return new Double3(1); // There is no shadow
        }

        // the distance from the light source to the point
        double lightDistance = lightSource.getDistance(geoPoint.point);
        Double3 ktr = new Double3(1);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0) {
                ktr = gp.geometry.getMaterial().kT.product(ktr); // The transparency of each intersection
                if (ktr.lowerThan( MIN_CALC_COLOR_K)){
                    return new Double3(0); // full shadow
                }
            }
        }
        return ktr;
    }


}
