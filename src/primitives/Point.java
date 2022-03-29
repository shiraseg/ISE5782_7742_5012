package primitives;

import geometries.Plane;

import java.util.Objects;

public class Point
{

    public final static Point ZERO = new Point(0d, 0d, 0d);

    final Double3 xyz;

    public Point(double x, double y, double z)
    {
        xyz=new Double3(x,y,z);
    }

    protected Point(Double3 xyz)
    {
        this.xyz=xyz;
    }

    public double getX()
    {
        return xyz.d1;
    }

    public double getZ()
    {
        return xyz.d3;
    }

    public double getY() {
        return xyz.d2;
    }
   @Override
   public boolean equals(Object o) {
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;
       Point point = (Point) o;
       return xyz.equals(((Point) o).xyz);
   }

    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    public Point add(Vector vector)
    {
        return new Point(xyz.add(vector.xyz));
    }

    public Vector subtract(Point p1)
    {
        return new Vector(xyz.subtract(p1.xyz));
    }

    public double distanceSquared(Point p1)
    {

        double x=this.xyz.subtract(p1.xyz).d1 * this.xyz.subtract(p1.xyz).d1;
        double y=this.xyz.subtract(p1.xyz).d2 * this.xyz.subtract(p1.xyz).d2;
        double z=this.xyz.subtract(p1.xyz).d3 * this.xyz.subtract(p1.xyz).d3;
        return x+y+z;

    }

    public double distance(Point p)
    {
        return Math.sqrt(this.distanceSquared(p));
    }

}