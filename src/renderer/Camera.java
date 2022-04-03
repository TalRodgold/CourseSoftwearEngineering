package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Camera {
    private Point p0;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double width;
    private double height;
    private double distance;

    public Camera(Point p01, Vector vTo1, Vector vUp1) {
        if (vTo1.dotProduct(vUp1) != 0) {
        throw new IllegalArgumentException("Two vectors are not orthogonal");
        }
        this.p0 = p01;
        this.vTo = vTo1.normalize();
        this.vUp = vUp1.normalize();
        this.vRight = vTo1.crossProduct(vUp1).normalize();
    }

    public Point getP0() {
        return p0;
    }

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
    public Camera setVPSize(double width1, double height1){
        this.width = width1;
        this.height = height1;
        return this;
    }
    public Camera setVPDistance(double distance1){
        this.distance = distance1;
        return this;
    }
    public Ray constructRay(int nX, int nY, int j, int i) {
        // calculate the ratio of the pixel by the height and by the width of the view plane
        // the ratio Ry = h/Ny, the height of the pixel
        double rY = alignZero(height / nY);
        // the ratio Rx = w/Nx, the width of the pixel
        double rX = alignZero(width / nX);

        double xJ = alignZero((j - ((nX - 1d) / 2d)) * rX); // Xj = (j - (Nx -1)/2) * Rx
        // Yi = -(i - (Ny - 1)/2) * Ry
        double yI = alignZero(-(i - ((nY - 1d) / 2d)) * rY);

        Point pIJ = p0.add(vTo.scale(distance));

        if (!isZero(xJ)) {
            pIJ = pIJ.add(vRight.scale(xJ));
        }
        if (!isZero(yI)) {
            pIJ = pIJ.add(vUp.scale(yI));
        }

        //Vi,j = Pi,j - P0, the direction of the ray to the pixel(j, i)
        Vector vIJ = pIJ.subtract(p0);
        return new Ray(p0, vIJ);
    }
}
