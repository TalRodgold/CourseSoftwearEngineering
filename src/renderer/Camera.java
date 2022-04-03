package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Camera {
    private Point p0; // Camera's fields
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double width;
    private double height;
    private double distance;

    public Camera(Point p01, Vector vTo1, Vector vUp1) { // C-tor for camera
        if (vTo1.dotProduct(vUp1) != 0) { // if they are not orthogonal
        throw new IllegalArgumentException("Two vectors are not orthogonal");
        }
        this.p0 = p01;
        this.vTo = vTo1.normalize(); // normalizing the vectors
        this.vUp = vUp1.normalize();
        this.vRight = vTo1.crossProduct(vUp1).normalize();
    }

    public Point getP0() {
        return p0;
    }  // getters

    public Vector getvTo() {
        return vTo;
    }

    public Vector getvUp() {
        return vUp;
    }

    public Vector getvRight() {
        return vRight;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDistance() {
        return distance;
    }
    public Camera setVPSize(double width1, double height1){  // setters
        this.width = width1;
        this.height = height1;
        return this;
    }
    public Camera setVPDistance(double distance1){
        this.distance = distance1;
        return this;
    }
    public Ray constructRay(int nX, int nY, int j, int i) {

        double rY = alignZero(height / nY); //  ratio of height of pixel
        double rX = alignZero(width / nX); // ratio of width of pixel
        double xJ = alignZero((j - ((nX - 1d) / 2d)) * rX); // according to slideshow 4
        double yI = alignZero(-(i - ((nY - 1d) / 2d)) * rY);

        Point pIJ = p0.add(vTo.scale(distance));
        if (!isZero(xJ)) {                 // if not 0 then scale and add
            pIJ = pIJ.add(vRight.scale(xJ));
        }
        if (!isZero(yI)) {
            pIJ = pIJ.add(vUp.scale(yI));
        }
        Vector vIJ = pIJ.subtract(p0); // direction of ray to pixel
        return new Ray(p0, vIJ);
    }
}
