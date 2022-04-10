package scene;

import Lighting.AmbientLight;
import geometries.Geometries;
import primitives.Color;

public class Scene
{
    public String Name;
    public Color Background;
    public AmbientLight AmbientLight;
    public Geometries geometries=null;

    public Scene(String name)
    {
        this.Name = name;
        this.geometries=new Geometries();
    }

    public Scene setBackground(Color background)
    {
        Background = background;
        return this;
    }

    public Scene setAmbientLight(Lighting.AmbientLight ambientLight) {
        AmbientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
