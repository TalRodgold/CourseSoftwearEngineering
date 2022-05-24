package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import static primitives.Util.*;
import java.util.stream.*;
import renderer.Pixel.*;
public class Camera {
    private Point p0; // Camera's field's
    private Vector vTo; // camera to
    private Vector vUp; // camera up
    private Vector vRight; // camera right
    private double width; // width
    private double height; // height
    private double distance; //distance
    private ImageWriter imageWriter;
    private RayTracerBasic rayTracerBasic;

    /**
     * Camera
     * @param p01 p01
     * @param vTo1 vTo1
     * @param vUp1 vUp1
     */
    public Camera(Point p01, Vector vTo1, Vector vUp1) { // C-tor for camera
        if (vTo1.dotProduct(vUp1) != 0) { // if they are not orthogonal
        throw new IllegalArgumentException("Two vectors are not orthogonal");
        }
        this.p0 = p01;
        this.vTo = vTo1.normalize(); // normalizing the vectors
        this.vUp = vUp1.normalize();
        this.vRight = vTo1.crossProduct(vUp1).normalize();
    }
    /**
     * getP0
     * @return Point
     */
    public Point getP0() {
        return p0;
    }  // getters

    /**
     * getvTo
     * @return Vector
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * getvUp
     * @return Vector
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * getvRight
     * @return Vector
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * getWidth
     * @return double
     */
    public double getWidth() {
        return width;
    }

    /**
     * getHeight
     * @return double
     */
    public double getHeight() {
        return height;
    }

    /**
     * get Distance
     * @return double
     */
    public double getDistance() {
        return distance;
    }

    /**
     *  set VP Size
     * @param width1 width
     * @param height1 height
     * @return Camera
     */
    public Camera setVPSize(double width1, double height1){  // setters
        this.width = width1;
        this.height = height1;
        return this;
    }

    /**
     * set VP Distance
     * @param distance1 distance1
     * @return Camera
     */
    public Camera setVPDistance(double distance1){ // calculate the distance
        this.distance = distance1;
        return this;
    }

    /**
     * set P0
     * @param p0 p0
     */
    public void setP0(Point p0) {
        this.p0 = p0;
    }

    /**
     * set vTo
     * @param vTo vTo
     */
    public void setvTo(Vector vTo) {
        this.vTo = vTo;
    }

    /**
     * set vUp
     * @param vUp vUp
     */
    public void setvUp(Vector vUp) {
        this.vUp = vUp;
    }

    /**
     * set vRight
     * @param vRight  vRight
     */
    public void setvRight(Vector vRight) {
        this.vRight = vRight;
    }

    /**
     * set Width
     * @param width  width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * set Height
     * @param height  height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     *  set Distance
     * @param distance  distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * set image writer
     * @param imageWriter  image Writer
     * @return camera
     */
    public Camera setImageWriter(ImageWriter imageWriter) {   // func returning this camera so we can concatenate the object
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * calls ray tracer basic
     * @param rayTracerBasic rayTracerBasic
     * @return camera
     */
    public Camera setRayTracer(RayTracerBasic rayTracerBasic) {
        this.rayTracerBasic = rayTracerBasic;
        return this;
    }

    /**
     * render the image
     * @param antialiasing if we want multiple rays for antialiasing
     * @return camera
     */
    public Camera renderImage(int antialiasing){
        if (p0 == null)
            throw new MissingResourceException("p0 is null","Camera","p0");
        if (vTo == null)
            throw new MissingResourceException("vTo is null","Camera","vTo");
        if (vUp == null)
            throw new MissingResourceException("vUp is null","Camera","vUp");
        if (vRight == null)
            throw new MissingResourceException("vRight is null","Camera","vRight");
        if (imageWriter == null)
            throw new MissingResourceException("imageWriter is null","Camera","imageWriter");
        if (rayTracerBasic == null)
            throw new MissingResourceException("rayTracerBase is null","Camera","rayTracerBase");
        int Nx = imageWriter.getNx();
        int Ny = imageWriter.getNy();
        for (int i = 0; i < Ny; ++i){
            for (int j = 0; j < Nx; ++j){
                castRay(Nx, Ny, j, i, antialiasing);
            }
        }
        return this;
    }

    /**
     * print the grid
     * @param interval interval
     * @param color color
     */
    public void printGrid(int interval, primitives.Color color){
        if (imageWriter == null)
            throw new MissingResourceException("imageWriter is null","Camera","imageWriter");
        // define resolution
        int Nx = imageWriter.getNx(), Ny = imageWriter.getNy();
        for(int i = 0; i < Nx; i++){ // create grid
            for(int j = 0; j < Ny; j++){
                if(i % interval == 0 || j % interval == 0){
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
    }

    /**
     *  writing the image to a file
     */
    public void writeToImage(){
        if (imageWriter == null) // if im
            throw new MissingResourceException("imageWriter is null","Camera","imageWriter");
        imageWriter.writeToImage();
    }

    /**
     * function for casting the ray to the grid
     * @param nX num of pixels in width
     * @param nY  num of pixels in height
     * @param col length of colum in grid
     * @param row length of row in grid
     * @param antialiasing if we want multiple rays for antialiasing
     * @return color
     */
    private Color castRay(int nX, int nY, int col, int row, int antialiasing) {
        List<Ray> ray = constructRay(nX, nY, col, row, antialiasing);// castRay func will create a ray and will figure the color using traceRay func
        Color avgColor = rayTracerBasic.traceRay(ray.get(0));
        for (int p = 1; p < ray.size(); p++)
            avgColor = avgColor.add(rayTracerBasic.traceRay(ray.get(p)));
        avgColor = avgColor.scale(1.0/ray.size());
        imageWriter.writePixel(col, row, avgColor); // write the colored pixel
        return avgColor;
    }

    /**
     * func for constructing a ray
     * @param nX num of pixels in width
     * @param nY num of pixels in height
     * @param j colum
     * @param i row
     * @param antialiasing if we want multiple rays for antialiasing
     * @return list of rays
     */
    public List<Ray> constructRay(int nX, int nY, int j, int i, int antialiasing) {

        double rY = alignZero(height / nY); //  ratio of height of pixel
        double rX = alignZero(width / nX); // ratio of width of pixel
        double xJ = alignZero((j - ((nX - 1d) / 2d)) * rX); // according to slideshow 4
        double yI = alignZero((i - ((nY - 1d) / 2d)) * rY);

        // for 1 ray
        Point pIJ = p0.add(vTo.scale(distance));
        if (!isZero(xJ)) {                 // if not 0 then scale and add
            pIJ = pIJ.add(vRight.scale(xJ));
        }
        if (!isZero(yI)) {
            pIJ = pIJ.add(vUp.scale(-yI));
        }
        Vector vIJ = pIJ.subtract(p0); // direction of ray to pixel
        List <Ray> rayList = new LinkedList<>();
        rayList.add(new Ray(p0, new Vector(vIJ.getX(), vIJ.getY(), vIJ.getZ()))); // create ray for specific pixel

        // for multiple rays
        double divNx = rX / 2; // divide pixel width by 2
        double divNy = rY / 2; // divide pixel height by 2
        Point center = pIJ; // save pixel center
       if ( antialiasing > 1){
            double jumpX = rX / antialiasing;
            double jumpY = rY / antialiasing;
            for (double k = -divNx; k <= divNx; k += jumpX) { // create antialiasing times rays
                for (double l = -divNy; l <= divNy; l += jumpY) {
                    pIJ = pIJ.add(vRight.scale(k));
                    pIJ = pIJ.add(vUp.scale(l));
                    vIJ = pIJ.subtract(p0);
                    rayList.add(new Ray(p0, new Vector(vIJ.getX(), vIJ.getY(), vIJ.getZ()))); // create ray for each new ray in same pixel
                    pIJ = center; // reset center
                }

            }

        }

    return rayList;
    }
}
