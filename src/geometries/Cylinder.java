package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Cylinder extends Tube
{
    double height;
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point point)
    {
        // if the dotProduct function is zero than the point is on the disk and the vector of the ray is the normal
        if(point.subtract(axisRay.getP0()).dotProduct(axisRay.getDir()) == 0)
            return axisRay.getDir().normalize();
        // else the point is on the tube, and we use the getNormal function of the tube
        return super.getNormal(point);
    }
}