package renderer;

import geometries.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.List;
import static primitives.Util.alignZero;
import primitives.Color;

public class RayTracerBasic extends RayTracerBase {
    /**
     * stop conditions for reflection and transparency
     */
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

    /**
     * find's the closest intersection point to thr ray's head
     * @param ray Ray
     * @return GeoPoint
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        if(ray == null){
            return null;
        }
        List<GeoPoint> points = scene.geometries.findGeoIntersections(ray);
        return ray.findClosestGeoPoint(points);
    }

   /* *//**
     * function for adding shade based on the presentation
     *
     * @param gp
     * @param l
     * @param n
     * @return bool
     */
   /*
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
    }*/

    /**
     * @param gp = GeoPoint
     * @param ray          = Ray
     * @return color of ambient light after adding color according to intersection and ray
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = gp.geometry.getEmission().add(calcLocalEffects(gp, ray, k));
        if (level == 1){
            return color;
        }
        return color.add(calcGlobalEffects(gp, ray, level, k));
    }

    /**
     * calls calcColor with stop conditions
     * @param gp Geopoint
     * @param ray Ray
     * @return calculation of color along with lighting
     */
    private Color calcColor(GeoPoint gp, Ray ray){
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * calculates the effects of the lighting at each point on the geometry
     * @param gp GeoPoint
     * @param ray Ray
     * @param level int
     * @param k Double3
     * @return the color with all the surrounding effects
     */
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
            color = color.add(calcGlobalEffect(new Ray(gp.point, ray.getDir(), n), level, kt, kkt));
        }
        return color;
    }

    /**
     *  finds closest intersection and calculates color according to that point
     * @param ray Ray
     * @param level int
     * @param kx Double3
     * @param kkx Double3
     * @return color according to point
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection (ray);
        if (gp == null){
            return scene.background.scale(kx);
        }
        return calcColor(gp, ray, level-1, kkx).scale(kx);
    }
    /**
     * took from presentation in moodle
     *
     * @param intersection = GeoPoint
     * @param ray          = Ray
     * @return color while calculating the diffusive and specular effects
     */
    private Color calcLocalEffects(Intersectable.GeoPoint intersection, Ray ray, Double3 k) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) // if nv equals 0
            return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().nShininess; // shininess
        Double3 kd = intersection.geometry.getMaterial().kD;
        Double3 ks = intersection.geometry.getMaterial().kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // checks if nl == nv
                Double3 ktr = transparency(intersection, l,lightSource);
                if (ktr.product(k).biggerThan(MIN_CALC_COLOR_K) ){
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
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
        GeoPoint closest = findClosestIntersection(ray);
        if(closest==null)
            return  scene.background;
        return calcColor(closest,ray);
    }

    /**
     * figures the transparency param
     * @param geoPoint GeoPoint
     * @param l Vector
     * @param lightSource LightSource
     * @return value of transparency
     */
    private Double3 transparency(GeoPoint geoPoint , Vector l, LightSource lightSource ){
        Vector lightDirection = l.scale(-1); // vector from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, geoPoint.normal); // ray from point to light source
        List<Intersectable.GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay); // if blocked by other obj
        if (intersections == null){
            return new Double3(1);
        }
        double lightDistance = lightSource.getDistance(geoPoint.point); //calc the distance from the light source to the point
        Double3 ktr = new Double3(1);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0) {
                ktr = gp.geometry.getMaterial().kT.product(ktr);
                if (ktr.lowerThan( MIN_CALC_COLOR_K)){
                    return new Double3(0);
                }
            }
        }
        return ktr;
    }
}
