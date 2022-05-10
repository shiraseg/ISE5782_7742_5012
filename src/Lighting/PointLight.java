package Lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource
{

    private Point position;
    private Double kC=1.0;
    private Double kL=0.0;
    private Double kQ=0.0;

    public PointLight(Color intensity, Point p0)
    {
        super(intensity);
        this.position=p0;
    }

    @Override
    public Color getIntensity(Point p)
    {
        double distance= p.distance(this.position);
        double disquer=p.distanceSquared(this.position);
        return super.getIntensity().scale(1/(kC+kL*distance+kQ*disquer));
    }

    @Override
    public Vector getL(Point p)
    {
       return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point)
    {
        return position.distance(point);
    }

    public PointLight setkC(Double kC)
    {
        this.kC = kC;
        return this;
    }

    public PointLight setkL(Double kL)
    {
        this.kL = kL;
        return  this;
    }

    public PointLight setkQ(Double kQ)
    {
        this.kQ = kQ;
        return  this;
    }
}
