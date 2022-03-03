package primitives;
import java.util.Objects;

public class Vector extends Point
{
    /********** Constructors ***********/
    public Vector(double x, double y, double z)
    {
        super(x,y,z);
        if(xyz.equals(Double3.ZERO)) throw  new IllegalArgumentException("Cant vector zero");
    }

    protected Vector(Double3 xyz) {
        super(xyz);
        if(xyz.equals(Double3.ZERO)) throw  new IllegalArgumentException("Cant vector zero");
    }

    /************** Operations ***************/
    public Vector add(Vector v)
    {
        return (Vector) super.add(v);
    }

    public Vector substract(Vector v)
    {
        return super.subtract(v);
    }

    public Vector scale(double scalingFactor)
    {
        double helpX=scalingFactor*this.xyz.d1;
        double helpY=scalingFactor*this.xyz.d2;
        double helpZ=scalingFactor*this.xyz.d3;
        return new Vector(helpX,helpY,helpZ);
    }

    public Vector crossProduct(Vector v)
    {
        double coord1 = this.xyz.d2 *v.xyz.d3;
        double coord2 = this.xyz.d3 * v.xyz.d2;
        double finalX=coord1-coord2;

        coord1=this.xyz.d1 *v.xyz.d3;
        coord2=this.xyz.d3 *v.xyz.d1;
        double finalY=-1 * (coord1-coord2);

        coord1=this.xyz.d1 *v.xyz.d2;
        coord2=this.xyz.d2 *v.xyz.d1;
        double finalZ=coord1-coord2;

        return new Vector(finalX,finalY,finalZ);
    }

    public double dotProduct(Vector vector)
    {
        double dotX= this.xyz.d1 * vector.xyz.d1;
        double dotY= this.xyz.d2 * vector.xyz.d2;
        double dotZ= this.xyz.d3 * vector.xyz.d3;
        return dotX+dotY+dotZ;


    }

    public double length()
    {
        return super.distance(this);
    }

    public double lengthSquared()
    {
        return super.distanceSquared(this);
    }

    public Vector normalize()
    {
        return new Vector(xyz.reduce(length()));
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