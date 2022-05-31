package renderer;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import static primitives.Util.*;

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
   // for improvements
    private static final double ANTIALIASING = 0.00000000001;
    private static final boolean SUPER_SAMPLING = true;
    private static final boolean THREADS = false;
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
     * @return camera
     */
    public Camera renderImage(){
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
        if (THREADS){
            Pixel.initialize(Ny, Nx, 1);
            int threadsCount = 4;
            while (threadsCount-- > 0) {
                new Thread(() -> {
                    for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
                        castRay(Nx, Ny, pixel.col, pixel.row);
                }).start();
            }
            Pixel.waitToFinish();
        }
        else {
            for (int i = 0; i < Ny; ++i){
                for (int j = 0; j < Nx; ++j){
                    castRay(Nx, Ny, j, i);
                }
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
     * @return color
     */
    private Color castRay(int nX, int nY, int col, int row) {
       // System.out.println(col + "," + row);
        Color color;
        if (SUPER_SAMPLING){
            color = superSampling(nX, nY, col, row, getCenterOfPixel(nX, nY, col, row), 1);// castRay func will create a ray and will figure the color using traceRay func
        }
        else {
            color = rayTracerBasic.traceRay(constructRay(nX,nY,col,row));
        }
        imageWriter.writePixel(col, row, color);
        return color;
    /*  else {
            List<Color> colors = constructRay(nX, nY, col, row, antialiasing);// castRay func will create a ray and will figure the color using traceRay func
        }
        Color avgColor = colors.get(0);
        for (int p = 1; p < colors.size(); p++)
            avgColor = avgColor.add(colors.get(p));
        avgColor = avgColor.scale(1.0/4colors.size());
        // write the colored pixel*/


       // Color color = rayTracerBasic.traceRay(constructRay(nX,nY,col,row));

    }

    /**
     * func for constructing a ray
     * @param nX num of pixels in width
     * @param nY num of pixels in height
     * @param j colum
     * @param i row
     * @return list of rays
    */

    public Ray constructRay(double nX, double nY, int j, int i) {

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
      /*  List <Ray> rayList = new LinkedList<>();
        rayList.add(new Ray(p0, new Vector(vIJ.getX(), vIJ.getY(), vIJ.getZ()))); // create ray for specific pixel*/

       /* // for multiple rays
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

        }*/
        return new Ray(p0, new Vector(vIJ.getX(), vIJ.getY(), vIJ.getZ()));

       /* List<Color> colorList = new LinkedList<>();
        colorList.add(rayTracerBasic.traceRay((rayList.get(0))));

    return supersampling(rX, rY, i, j, pIJ, rayList, colorList);*/
    }

    private Point getCenterOfPixel(double nX, double nY, int j, int i){
        // calculate the ratio of the pixel by the height and by the width of the view plane
        // the ratio Ry = h/Ny, the height of the pixel
        double rY = alignZero(height / nY);
        // the ratio Rx = w/Nx, the width of the pixel
        double rX = alignZero(width / nX);

        // Xj = (j - (Nx -1)/2) * Rx
        double xJ = alignZero((j - ((nX - 1d) / 2d)) * rX);
        // Yi = -(i - (Ny - 1)/2) * Ry
        double yI = alignZero(- (i - ((nY - 1d) / 2d)) * rY);

        Point pIJ = p0;

        if (xJ != 0d) {
            pIJ = pIJ.add(vRight.scale(xJ));
        }
        if (yI != 0d) {
            pIJ = pIJ.add(vUp.scale(yI));
        }
        return pIJ;
    }

    private Color superSampling(double nX, double nY, int i, int j, Point pIJ, int size){
        Ray centerRay = constructRay(nX, nY, i, j);
        Color centerColor = rayTracerBasic.traceRay(centerRay);
        if (nX < ANTIALIASING || nY < ANTIALIASING){
            return centerColor;
        }
        List<Ray> rayList = new LinkedList<>();
        List<Color> colorList = new LinkedList<>();
        Color c = centerColor;
        rayList.add(Helper(-nX, -nY, pIJ));
        rayList.add(Helper(nX, nY, pIJ));
        rayList.add(Helper(-nX, nY, pIJ));
        rayList.add(Helper(nX, -nY, pIJ));
        for (int t = 0; t < 4; t++){
            colorList.add(rayTracerBasic.traceRay((rayList.get(t))));
        }
        for (int k = 0; k<4;k++) {
            if (!centerColor.equals(colorList.get(k))) {
                if (k == 0) {
                    c.add(superSampling(-nX / 2, -nY / 2, i, j, getCenterOfPixel(-nX / 2, -nY / 2, i, j), ++size).reduce(5));
                }
                if (k == 1) {
                    c.add(superSampling(nX / 2, nY / 2, i, j, getCenterOfPixel(nX / 2, nY / 2, i, j), ++size).reduce(5));
                }
                if (k == 2) {
                    c.add(superSampling(-nX / 2, nY / 2, i, j, getCenterOfPixel(-nX / 2, nY / 2, i, j), ++size).reduce(5));
                }
                if (k == 3) {
                    c.add(superSampling(nX / 2, -nY / 2, i, j, getCenterOfPixel(nX / 2, -nY / 2, i, j), ++size).reduce(5));
                }
            }
            else {
                c.add(colorList.get(k).reduce(5));
            }
        }
        return c;
    }
    private Ray Helper (double nX, double nY, Point pIJ){
        pIJ = pIJ.add(vRight.scale(nX));
        pIJ = pIJ.add(vUp.scale(nY));
        if (pIJ.equals(p0)){
            return (new Ray(p0, new Vector(0.000001,0.000001,0.000001))); // create ray for each new ray in same pixel
        }
/*
        System.out.println(pIJ);
*/
        Vector vIJ = pIJ.subtract(p0);
        return (new Ray(p0, new Vector(vIJ.getX(), vIJ.getY(), vIJ.getZ()))); // create ray for each new ray in same pixel
    }
}
