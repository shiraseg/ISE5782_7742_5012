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

    protected boolean softShadows =false;
    protected double beamRadius =20d;//אלומת אור

    public RayTracerBase setSoftShadows(boolean softShadows) {
        this.softShadows = softShadows;
        return this;
    }

    public RayTracerBase setsBeamRadius(double beamRadius) {
        this.beamRadius = beamRadius;
        return this;
    }

    public boolean isSoftShadows() {
        return softShadows;
    }

    public double getBeamRadius() {
        return beamRadius;
    }
    public RayTracerBase(Scene scene)
    {
        this.scene = scene;
    }

    public abstract Color traceRay(Ray ray);

    public abstract Color traceRay(List<Ray> ray);

}
