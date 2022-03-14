package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Sphere implements Geometry
{
    final Point center;
    final double radius;

    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public Vector getNormal(Point point)
    {
        Vector normal=this.center.subtract(point);
        return normal.normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}