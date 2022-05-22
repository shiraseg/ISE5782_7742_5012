package Renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

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
}
