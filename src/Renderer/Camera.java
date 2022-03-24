package Renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;



public class Camera {
    private Point loc;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double width;
    private double height;
    private double distance;

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDistance() {
        return distance;
    }

    public Camera(Vector vTo, Vector vUp, int i, int j)         /*constructor*/ {
        if (vTo.dotProduct(vUp) == 0) throw new IllegalArgumentException("ERROR! two vectors arent orthogonal!");
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();
    }


    /*builder pattern methods:*/
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i)
    {
        return null;
    }
}
