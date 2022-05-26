package Renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * an abstract class.
 */
public abstract class RayTracerBase
{
    /**
     * field
     */
    protected Scene scene;

    /**
     * methodes
     */

    /**
     *
     * @param scene
     * constructor.
     */
    public RayTracerBase(Scene scene)
    {
        this.scene = scene;
    }

    public abstract Color traceRay(Ray ray);

    public abstract Color traceRay(List<Ray> ray);

}
