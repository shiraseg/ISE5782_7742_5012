package geometries;
import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry
{
    private Point p;
    private Vector normal;

    public Plane(Point p, Vector normal)
    {
        this.p = p;
        this.normal = normal.normalize();
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

    public Plane(Point x, Point y, Point z)
    {
        this.p=x;
        Vector v1=z.subtract(x);
        Vector v2=y.subtract(x);
        this.normal=v1.crossProduct(v2).normalize();
    }

    @Override
    public Vector getNormal(Point p)
    {
        return normal;
    }
}