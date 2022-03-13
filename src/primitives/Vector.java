package primitives;
import java.util.Objects;

public class Vector extends Point
{
    /********** Constructors ***********/
    public Vector(double x, double y, double z)
    {
        this(new Double3(x,y,z));
    }

    protected Vector(Double3 xyz) {
        super(xyz);
        if(xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("Cant vector zero");
    }

    /************** Operations ***************/
    /**
    public Vector add(Vector v)
    {
        return (Vector) super.add(v);
    }


    public Vector substract(Vector v)
    {
        return super.subtract(v);
    }
     */

    public Vector scale(double scalingFactor)
    {
        return new Vector(xyz.scale(scalingFactor));
    }

    public Vector crossProduct(Vector v)
    {
        double ax = xyz.d1;
        double ay = xyz.d2;
        double az = xyz.d3;
        double bx = v.xyz.d1;
        double by = v.xyz.d2;
        double bz = v.xyz.d3;

        double cx = ay * bz - az * by;
        double  cy = az * bx - ax * bz;
        double cz = ax * by - ay * bx;
        return  new Vector(cx, cy, cz);
    }

    public double dotProduct(Vector vector)
    {
        return this.xyz.d1 * vector.xyz.d1 +
                this.xyz.d2 * vector.xyz.d2 +
                this.xyz.d3 * vector.xyz.d3;
    }

    public double length()
    {
        return  Math.sqrt(lengthSquared());
    }

    public double lengthSquared()
    {
        return xyz.d1 * xyz.d1 +
                xyz.d2 * xyz.d2 +
                xyz.d3 * xyz.d3;
    }

    public Vector normalize()
    {
        double len = length();
        return new Vector(xyz.reduce((len)));
    }

    @Override
    public boolean equals(Object obj)
    {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }

}