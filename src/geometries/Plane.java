package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.Objects;

import static primitives.Util.*;


public class Plane extends Geometry
{
    final private Point _q0;
    final private Vector _normal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return _q0.equals(plane._q0) && _normal.equals(plane._normal);
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
    public List<Point> findIntersections(Ray ray) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        Vector n = _normal;

        if(this._q0.equals(P0))
        {
            return  null;
        }

        Vector P0_Q0 = this._q0.subtract(P0);

        //numerator
        double nP0Q0  = alignZero(n.dotProduct(P0_Q0));

        //
        if (isZero(nP0Q0 )){
            return null;
        }

        //denominator
        double nv = alignZero(n.dotProduct(v));

        // ray is lying in the plane axis
        if(isZero(nv)){
            return null;
        }

        double  t = alignZero(nP0Q0  / nv);

        if (t <=0){
            return  null;
        }

        Point point = ray.getPoint(t);

        return List.of(point);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray)
    {
        return this.findGeoIntersections(ray);
    }

}