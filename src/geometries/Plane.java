package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.Objects;

import static primitives.Util.*;


public class Plane implements Geometry {
    final private Point _q0;
    final private Vector _normal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return _q0.equals(plane._q0) && _normal.equals(plane._normal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_q0, _normal);
    }

    public Plane(Point q0, Vector normal) {
        _q0 = q0;
        _normal = normal;
    }

    public Plane(Point x, Point y, Point z) {
        this._q0 = x;
        if(x.equals(y) || x.equals(z) || y.equals(z))
            throw  new IllegalArgumentException("ERROR: plane must be with three different points");
        Vector xt = x.subtract(new Point(0,0,0)).normalize();
        Vector yt = y.subtract(new Point(0,0,0)).normalize();
        Vector zt = z.subtract(new Point(0,0,0)).normalize();
        if(xt.dotProduct(yt)==1 || xt.dotProduct(zt)==1 || yt.dotProduct(zt)==1)
            throw new  IllegalArgumentException("ERROR: cant points on the same ray");

        Vector U = y.subtract(x);
        Vector V = z.subtract(x);
        Vector N = U.crossProduct(V);
        this._normal = N.normalize();
    }

    public Point getQ0() {
        return _q0;
    }

    /**
     *  getter for _normal Vector
     * @return the infamous normal
     */
    public Vector getNormal()
    {
       return this._normal;
    }

    /**
     *  implementation of {@link Geometry#getNormal(Point)}
     * @param point external Point
     * @return normal to plane
     */
    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }

    @Override
    public List<Point> findIntersections(Ray ray)
    {
        // look at powerpoint 4 how to make the function(Ray Plane intersection))
        Point P0=ray.getP0();
        Vector v=ray.getDir();
        Vector n=_normal;

        // denominator
        double nv = n.dotProduct(v);

        if (isZero(nv))
            return null;

        Vector P0_Q= _q0.subtract(P0);
        double t = alignZero( n.dotProduct(P0_Q)/nv);
        // if t<0 thn the ray is not in the right direction
        //if t==0 the ray origin alay on the
        if(t > 0 ) {
            Point P = P0.add(v.scale(t));
            return List.of(P);
        }
        return null ;
    }
}