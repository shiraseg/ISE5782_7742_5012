package primitives;

public class Point
{
    protected final Double3 xyz;

    public Point(double x, double y, double z)
    {
        xyz=new Double3(x,y,z);
    }

    protected Point(Double3 xyz)
    {
        this.xyz=xyz;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector other = (Vector) obj;
        if (this.xyz == null) {
            if (other.xyz != null)
                return false;
        } else if (!xyz.equals(other.xyz))
            return false;
        return true;
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