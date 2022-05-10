package Lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource
{

    private Vector direction;

    public DirectionalLight(Color intensity, Vector dir)
    {
        super(intensity);
        this.direction=dir.normalize();
    }

    @Override
    public Color getIntensity(Point p)
    {
       return super.getIntensity();
    }

    @Override
    public Vector getL(Point p)
    {
        return this.direction.normalize();
    }

    @Override
    public double getDistance(Point point)
    {
        return Double.POSITIVE_INFINITY;
    }
}
