package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
        if(x.equals(y) || x.equals(z) || y.equals(z))
           throw  new IllegalArgumentException("ERROR: plane must be with three different points");
        Vector xt = x.subtract(new Point(0,0,0)).normalize();
        Vector yt = y.subtract(new Point(0,0,0)).normalize();
        Vector zt = z.subtract(new Point(0,0,0)).normalize();
        if(xt.dotProduct(yt)==1 || xt.dotProduct(zt)==1 || yt.dotProduct(zt)==1)
            throw new  IllegalArgumentException("ERROR: cant points on the same ray");
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

    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}