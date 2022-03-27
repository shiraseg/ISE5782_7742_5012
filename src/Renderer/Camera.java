package Renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;


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

    public Camera(Point p0, Vector vTo, Vector vUp)         /*constructor*/
    {
        if (vTo.dotProduct(vUp) != 0) throw new IllegalArgumentException("ERROR! two vectors arent orthogonal!");
        this.loc=p0;
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
        Point pCenter=this.loc.add(this.vTo.scale(this.distance));
        double rX=this.width/nX;
        double rY=this.height/nY;

        double xj=(j-(nX-1)/2)*rX;
        double yi=-(i-(nY-1)/2)*rY;

        Point pIJ=pCenter;

        if(!isZero(xj))
        {
            pIJ=pIJ.add(vRight.scale(xj));
        }

        if(!isZero(yi))
        {
            pIJ=pIJ.add(vUp.scale(yi));
        }

        Vector vIJ=pIJ.subtract(this.loc);
        Ray rayThrough= new Ray(this.loc,vIJ);

        return rayThrough;

    }
}
