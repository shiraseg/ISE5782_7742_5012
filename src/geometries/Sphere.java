package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.*;

import static primitives.Util.alignZero;

public class Sphere extends Geometry {
    final Point center;
    final double radius;

    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public Vector getNormal(Point point)
    {
        Vector normal = point.subtract(center);
        return normal.normalize();
    }

//    @Override
//    public List<Point> findIntersections(Ray ray)
//    {
//        Point P0 = ray.getP0();
//        Vector v = ray.getDir();
//        //if the ray is starting from precisely the center of the sphere
//        // than we know the intersection point will be scaling the point by the radius
//        if (P0.equals(center))
//        {
//            return List.of(center.add(v.scale(radius)));
//        }
//
//        Vector U = center.subtract(P0); // The vector from p0 to the center of the sphere
//        double tm =alignZero(v.dotProduct(U)); // the scalar for the projection of v on u
//        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm)); // the distance of the center to the ray
//
//        // no intersections : the ray direction is above the sphere
//        if (d >= radius) {
//            return null;
//        }
//
//        double th = alignZero(Math.sqrt(radius * radius - d * d)); // distance from p1 to intersection with d
//        double t1 = alignZero(tm - th); // from p0 to p1
//        double t2 = alignZero(tm + th);// from p0 to p2
//
//        if (t1 > 0 && t2 > 0) // take only t > 0 (going in the right direction)
//        {
////            Point P1 = P0.add(v.scale(t1));
////            Point P2 = P0.add(v.scale(t2));
//            Point P1 =ray.getPoint(t1);
//            Point P2 =ray.getPoint(t2);
//            return List.of(P1, P2);
//        }
//        if (t1 > 0) {
////            Point P1 = P0.add(v.scale(t1));
//            Point P1 =ray.getPoint(t1);
//            return List.of(P1);
//        }
//        if (t2 > 0) {
////            Point P2 = P0.add(v.scale(t2));
//            Point P2 =ray.getPoint(t2);
//            return List.of(P2);
//        }
//        return null;
//    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray)
    {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        //if P0 is the center of the sphere, return the radius to there
        if (P0.equals(center)) {
            //center+(radius*v)
            return List.of(new GeoPoint(this, center.add(v.scale(radius))));
        }

        Vector U = center.subtract(P0); //u=center-p0

        double tm = alignZero(v.dotProduct(U)); //tm=v*u
        //d is the distance from the center to the ray
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm)); //d=squrt(|u|^2-tm^2)

        //if d is larger are equals radius, then there are no intersections
        if (d >= radius) {
            return null;
        }

        //th is the last part of the triangle between radius and d, calculate according to pythagoras
        double th = alignZero(Math.sqrt(radius * radius - d * d));
        double t1 = alignZero(tm - th); //t1=tm-th
        double t2 = alignZero(tm + th); //t2=tm+th

        if (t1 > 0 && t2 > 0) { //if they are both positive then there are 2 intersections
            Point P1 = ray.getPoint(t1); //p1=p0+t1*v
            Point P2 = ray.getPoint(t2); //p2=p0+t2*v
            return List.of(new GeoPoint(this, P1), new GeoPoint(this, P2));
        }
        //otherwise if only one is positive so there is one intersection
        if (t1 > 0) {
            Point P1 = ray.getPoint(t1); //p1=p0+t1*v
            return List.of(new GeoPoint(this, P1));
        }
        if (t2 > 0) {
            Point P2 = ray.getPoint(t2); //p2=p0+t2*v
            return List.of(new GeoPoint(this, P2));
        }

        //if they are both negative then there are no intersections
        return null;
    }



    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}