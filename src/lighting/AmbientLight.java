package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight
{

    Color intensity;

    public Color getIntensity()
    {
        return intensity;
    }

    public AmbientLight(Color ia, Double3 ka)
    {
        this.intensity=ia.scale(ka);
    }

    //default constructor for the background.
    public AmbientLight()
    {
        this.intensity = Color.BLACK;
    }
}
