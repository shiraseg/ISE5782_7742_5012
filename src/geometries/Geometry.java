package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public abstract class Geometry extends Intersectable {

    abstract public Vector getNormal(Point p);

   // List<Point> findIntersections(Ray ray);

    protected Color emission =Color.BLACK;

    //get and set to the field "emission".

    public Color getEmission()
    {
        return this.emission;
    }

    //setter methode for a builder pattern.
    public Geometry setEmission(Color emission)
    {
        this.emission = emission;
        return this;
    }
}