package Lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight extends Light
{

//    Color intensity;
//
//    public Color getIntensity()
//    {
//        return intensity;
//    }

    public AmbientLight(Color ia, Double3 ka)
    {
        super(ia.scale(ka));    //the constructor of the father class is working.
    }

    //default constructor for the background.
    public AmbientLight()
    {
       super(Color.BLACK);
    }
}
