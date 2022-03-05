package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry
{
    final Point p;
    final Vector normal;

    public Plane(Point p, Vector normal)
    {
        this.p = p;
        this.normal = normal.normalize();
    }

    public Plane(Point x, Point y, Point z)
    {
        this.p=x;
        Vector U=y.subtract(x);
        Vector V=z.subtract(x);
        Vector N=U.crossProduct(V);
        N.normalize();
        this.normal=N;

    }

    public Point getP() {
        return p;
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "p=" + p +
                ", normal=" + normal +
                '}';
    }


    @Override
    public Vector getNormal(Point p)
    {
        return normal;
    }
}