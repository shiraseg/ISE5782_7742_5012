package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Tube extends Geometry
{
    /**
     * fields
     */
    final Ray axisRay;
    final double radius;

    /**
     * methods
     */

    /**
     *
     * @param axisRay
     * @param radius
     * a constructor.
     */

    public Tube(Ray axisRay, double radius)
    {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    /**
     *
     * @return ray
     * getter for "axisRay"
     */
    public Ray getAxisRay()
    {
        return axisRay;
    }

    /**
     *
     * @return double
     * getteer for "radius"
     */
    public double getRadius()
    {
        return radius;
    }


    /**
     *
     * @return string
     * a methode that returns string with all the characteristics of the triangle
     */
    @Override
    public String toString()
    {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**
     *  implementation of {@link Geometry#getNormal(Point)}
     * @param point external Point
     * @return normal to plane
     */
    @Override
    public Vector getNormal(Point point)
    {
        double t = axisRay.getDir().dotProduct(point.subtract( axisRay.getP0()));//finding scaler for the projection of point on axisRay
        Point O = axisRay.getP0().add(axisRay.getDir().scale(t));// O is the projection of point on axisRay
        Vector N=point.subtract(O);
        return N.normalize();
    }

    /**
     * implementation of {@link Intersectable#findGeoIntersectionsHelper(Ray)}
     * @param ray
     * @return list of geo- points
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray)
    {
        return this.findGeoIntersections(ray);
    }
}