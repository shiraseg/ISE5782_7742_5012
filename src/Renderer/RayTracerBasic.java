package Renderer;

import Lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;


import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase
{
    /**
     * an initialized fields for the deep of the recursion
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K =new Double3(1);

    /**
     * methods
     */

    /**
     *
     * @param scene
     * a constructor.
     */
    public RayTracerBasic(Scene scene)
    {
        super(scene);
    }

    /**
     *
     * @param point
     * @param inRay
     * @param n
     * @return ray
     * creating a ray for transparency
     */
    private Ray constructRefractedRay(Point point, Ray inRay, Vector n)
    {
        return new Ray(point, inRay.getDir(), n);
    }


    /**
     *
     * @param point
     * @param inRay
     * @param n
     * @return ray
     * creating a ray for reflection
     */
    private Ray constructReflectedRay(Point point,  Ray inRay, Vector n)
    {

        Vector v = inRay.getDir();
        double vn = v.dotProduct(n);

        Vector r = v.subtract(n.scale(2 * vn)).normalize();
        return new Ray(point, r, n);
    }


    /**
     * implementation of {@link RayTracerBase#traceRay(Ray)}
     * @param ray
     * @return color
     * a methode that decide what color to paint the pixel-
     * if there are no intersection-> in the background color
     * if there are intersection-> in the color of the closest geometry.
     */
    @Override
    public Color traceRay(Ray ray)
    {
        List<GeoPoint> intersection = scene.geometries.findGeoIntersections(ray);
        if (intersection == null)
        {
            return scene.getBackground();
        }
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersection);
        return calcColor(closestPoint, ray)==null?scene.getBackground():calcColor(closestPoint,ray);
    }


    public Color traceRay(List<Ray> rays)
    {
        Color finalColor=null;
        Color colorTmp=new Color(0,0,0);
        for(var ray:rays)
        {
            List<GeoPoint> intersection = scene.geometries.findGeoIntersections(ray);
            if (intersection == null)
            {
                return scene.getBackground();
            }
            GeoPoint closestPoint = ray.findClosestGeoPoint(intersection);

             colorTmp=calcColor(closestPoint, ray) == null ? scene.getBackground() : calcColor(closestPoint, ray);
             if(finalColor==null)
             {
                 finalColor=new Color(0,0,0);
                 for (int i = 0; i < 9; i++)
                     finalColor.add(colorTmp);
             }

            finalColor.add(colorTmp);

        }
        int size=rays.size();
        return new Color(finalColor.getColor().getRed()/size,
                finalColor.getColor().getGreen()/size,
                finalColor.getColor().getBlue()/size) ;
    }

    /**
     *
     * @param closestPoint
     * @param ray
     * @return color
     * an auxiliary method to calculate the pixels color
     */
    private Color calcColor(GeoPoint closestPoint, Ray ray)
    {
        return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.getAmbientLight().getIntensity());
    }


    /**
     *
     * @param point
     * @param ray
     * @param level
     * @param k
     * @return color
     * a methode that calculate the pixels color with all its affects
     */
    private Color calcColor(GeoPoint point, Ray ray, int level, Double3 k)
    {
        Color color= calcLocalEffects(point, ray,k);
        return level==1? color:color.add(calcGlobalEffects(point, ray,level,k));

    }


    /**
     *
     * @param gp
     * @param ray
     * @param k
     * @return color
     * a methode that calculate the colors local effects:
     * diffusive and specular
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray,Double3 k)
    {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));

        if (nv == 0)
            return Color.BLACK;

        Material material = gp.geometry.getMaterial();

        for (LightSource lightSource : scene.getLights())
        {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0)        //checking if sign(nl) == sing(nv)
            {
                Double3 ktr = transparency(gp,lightSource,l, n);

                 if(ktr.product(k).biggerThan(MIN_CALC_COLOR_K))
                 {
                     Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                     color = color.add(
                             iL.scale(calcDiffusive(material, nl)),
                             iL.scale(calcSpecular(material, n, l, nl, v)));

                 }
            }
        }
        return color;
    }


    /**
     *
     * @param material
     * @param nl
     * @return double3
     * a methode that calculate the effect of diffusive on the picture
     */
    private Double3 calcDiffusive(Material material, double nl)
    {
        return material.kD.scale(Math.abs(nl));
    }

    /**
     *
     * @param material
     * @param n
     * @param l
     * @param nl
     * @param v
     * @return double3
     * a methode that calculate the effect of specular on the picture
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v)
    {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2)).normalize();
        double minusVR = 0 - alignZero(r.dotProduct(v));

        if (minusVR <= 0)
            return Double3.ZERO;

        return material.kS.scale(Math.pow(Math.max(0, r.dotProduct(v.scale(-1d))), material.getnShininess()));
    }

    /**
     *
     * @param gp
     * @param ray
     * @param level
     * @param k
     * @return color
     *  a methode that calculate the colors global effects:
     *  transparency and reflection
     */
    private Color calcGlobalEffects(GeoPoint gp,Ray ray, int level, Double3 k)
    {
        Color color=Color.BLACK;
        Vector normal=gp.geometry.getNormal(gp.point);
        Material material=gp.geometry.getMaterial();
        Double3 kkr=material.kR.product(k);

        if(kkr.biggerThan(MIN_CALC_COLOR_K))
            color=calcGlobalEffect(constructReflectedRay(gp.point,ray,normal),level,material.kR,kkr);

        Double3 kkt =material.kT.product(k);

        if (kkt.biggerThan(MIN_CALC_COLOR_K))
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, ray, normal), level, material.kT, kkt));
        return color;
    }

    /**
     *
     * @param ray
     * @param level
     * @param kx
     * @param kkx
     * @return color
     * an auxiliary method for the recursion
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx)
    {
        GeoPoint gp = findClosestIntersection (ray);
        return (gp == null ? scene.getBackground() : calcColor(gp, ray, level-1, kkx)).scale(kx);
    }

    /**
     *
     * @param gp
     * @param light
     * @param l
     * @param n
     * @return double3
     * a methode that calculates the transparency.
     */
    private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n)
    {
        //from point to light source
        Vector lightDirection = l.scale(-1);
        // Refactored ray head move
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        double maxDistance = light.getDistance(gp.point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        //if there are no intersections return true (there is no shadow)
        if (intersections == null)
            return new Double3(1.0);

        for (var geo : intersections)
        {
            double dist = geo.point.distance(gp.point);

            //if the point is farther than the maximum distance- remove from list.
            if (dist >= maxDistance)
                intersections.remove(geo);

        }

        if (intersections.isEmpty())
            return new Double3(1.0);


        Double3 ktr = new Double3(1.0);

        //for each intersection
        for (GeoPoint intersection : intersections)
        {
            ktr = intersection.geometry.getMaterial().kT.product(ktr);
            if (ktr.lowerThan(MIN_CALC_COLOR_K))
                return Double3.ZERO;
        }
        return ktr;
    }

    /**
     *
     * @param ray
     * @return geo- point
     * a methode that goes over all the intersection points of a ray and returns the closest one.
     */
    private GeoPoint findClosestIntersection(Ray ray)
    {
        List<GeoPoint> intersectionPoints = scene.geometries.findGeoIntersections(ray);

        if (intersectionPoints == null)
            return null;

        GeoPoint closestPoint = ray.findClosestGeoPoint(intersectionPoints);
        return closestPoint;
    }

}







