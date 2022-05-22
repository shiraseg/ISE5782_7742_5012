package Lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * a class with builder pattern
 */
public class PointLight extends Light implements LightSource
{
    /**
     * fields
     */

    private Point position;
    private Double kC=1.0;
    private Double kL=0.0;
    private Double kQ=0.0;

    /**
     * methods
      */

    /**
     *
     * @param intensity
     * @param p0
     * a constructor
     */

    public PointLight(Color intensity, Point p0)
    {
        super(intensity);
        this.position=p0;
    }


    /**
     * implementation of {@link LightSource#getIntensity(Point)}
     * @param p
     * @return color
     * a methode that gets the intensity in a point
     */
    @Override
    public Color getIntensity(Point p)
    {
        double distance= p.distance(this.position);
        double disquer=p.distanceSquared(this.position);
        return super.getIntensity().scale(1/(kC+kL*distance+kQ*disquer));
    }

    /**
     * implementation of {@link LightSource#getL(Point)}
     * @param p
     * @return vector
     * a methode that returns the normalized direction.
     */
    @Override
    public Vector getL(Point p)
    {
       return p.subtract(position).normalize();
    }


    /**
     * implementation of {@link LightSource#getDistance(Point)}
     * @param point
     * @return infinity
     * a methode that returns the distance between the light source and a point.
     */
    @Override
    public double getDistance(Point point)
    {
        return position.distance(point);
    }

    /**
     *
     * @param kC
     * @return point light
     * setter to "kc" in builder pattern
     */
    public PointLight setkC(Double kC)
    {
        this.kC = kC;
        return this;
    }

    /**
     *
     * @param kL
     * @return point light
     * setter to "kl" in builder pattern
     */
    public PointLight setkL(Double kL)
    {
        this.kL = kL;
        return  this;
    }

    /**
     *
     * @param kQ
     * @return point light
     * setter to "kq" in builder pattern
     */
    public PointLight setkQ(Double kQ)
    {
        this.kQ = kQ;
        return  this;
    }
}
