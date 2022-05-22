package Lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * an interface
 */
public interface LightSource
{
    public  Color getIntensity(Point p);

    public  Vector getL(Point p);

    double getDistance(Point point);

}
