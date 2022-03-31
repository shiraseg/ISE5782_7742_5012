package Renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

//an abstract class.
public abstract class RayTracerBase
{
    protected Scene scene;

    //constructor.
    public RayTracerBase(Scene scene)
    {
        this.scene = scene;
    }

    //an abstract method.
    public abstract Color traceRay(Ray ray);
}
