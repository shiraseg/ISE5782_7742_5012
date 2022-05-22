package geometries;

import primitives.*;

import java.util.List;

/**
 * an abstract class
 */
public abstract class Geometry extends Intersectable
{


    abstract public Vector getNormal(Point p);

    private Material material= new Material();

    protected Color emission =Color.BLACK;

    //get and set to the field "emission".

    public Color getEmission()
    {
        return this.emission;
    }

    public Material getMaterial()
    {
        return material;
    }

    public Geometry setMaterial(Material material)
    {
        this.material = material;
        return this;
    }

    //setter methode for a builder pattern.
    public Geometry setEmission(Color emission)
    {
        this.emission = emission;
        return this;
    }
}