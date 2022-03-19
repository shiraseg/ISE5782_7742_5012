package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
      /**  // if the dotProduct function is zero than the point is on the disk and the vector of the ray is the normal
        if(point.subtract(axisRay.getP0()).dotProduct(axisRay.getDir()) == 0)
            return axisRay.getDir().normalize();
        // else the point is on the tube, and we use the getNormal function of the tube
        return super.getNormal(point);
       */
        Point bottomPoint = axisRay.getPoint(height); // returns the center point of the second disk
        if(point.equals(bottomPoint) ||point.equals(axisRay.getP0()) )
            return axisRay.getDir().normalize();
        if (point.subtract(bottomPoint).length() < radius)// meaning it is on the surface of second disk
            return axisRay.getDir().normalize();
        if (point.subtract(axisRay.getP0()).length() < radius)// meaning it is on the surface of first disk
            return axisRay.getDir().normalize();
        return super.getNormal(point);// else the point is on the tube, and we use the get normal function of the tube.(super)
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }
}