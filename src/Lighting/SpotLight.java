package Lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends  PointLight
{
    private Vector direction;

    public SpotLight(Color intensity,Point p0, Vector dir)
    {
        super(intensity,p0);
        this.direction=dir.normalize();
    }

    public Color getIntensity(Point p)
    {
        double max= Math.max(0,direction.dotProduct(getL(p)));
        return super.getIntensity(p).scale(max);
    }
}
