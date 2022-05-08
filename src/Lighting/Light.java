package Lighting;


import primitives.Color;

//this class is abstract.
abstract class Light
{
    private Color intensity;

    protected Light(Color intensity)    //constructor.
    {
        this.intensity = intensity;
    }

    public Color getIntensity()
    {
        return intensity;
    }


}
