package primitives;

import java.util.List;
import java.util.Objects;
import geometries.Intersectable.GeoPoint;


import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Ray
{
    final Point p0;
    final Vector dir;

    private static final double DELTA = 0.1;

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() { return dir; }

    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    public Ray(Point point, Vector direction, Vector normal)
    {
        //point + normal.scale(±EPSILON)
        dir = direction.normalize();

        double nv = alignZero(normal.dotProduct(dir));

        Vector normalDelta = normal.scale((nv > 0 ? DELTA : -DELTA));
        p0 = point.add(normalDelta);
    }


    public Point getPoint(double t2) {
        if (isZero(t2)){
            return  p0;
        }
        return p0.add(dir.normalize().scale(t2));
    }

    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }


    public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections)
    {
        //the list is empty
        if(intersections==null|| intersections.isEmpty())
        {
            return null;
        }

        double min=Double.MAX_VALUE;
        GeoPoint closestPoint=null;

        //Loop that goes over the list and finds the closest point in it.
        for(int i=0;i<intersections.size();i++)
        {
            double distance= intersections.get(i).point.distance(this.p0);
            if(distance<min)
            {
                closestPoint=intersections.get(i);
                min=distance;
            }
        }

        return closestPoint;
    }
}