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

        Point p1=this.vRight.scale(xj).add(this.vUp.scale(yi));
        /*Point pIJ=new Point(pCenter.getX()+ p1.getX(),pCenter.getY()+ p1.getY(),pCenter.getZ()+ p1.getZ());*/

        Point pIJ=pCenter;

        if(xj!=0)
        {
            pIJ=pIJ.add(vRight.scale(xj));
        }

        if(yi!=0)
        {
            pIJ=pIJ.add(vUp.scale(yi));
        }

        Vector vIJ=pIJ.subtract(this.loc);
        Ray rayThrough= new Ray(this.loc,vIJ);

        return rayThrough;

    }
}
