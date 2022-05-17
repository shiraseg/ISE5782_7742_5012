package unittests;



//import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.api.Test;

import Lighting.AmbientLight;
import Lighting.DirectionalLight;
import Lighting.PointLight;
import Lighting.SpotLight;
import Renderer.Camera;
import Renderer.ImageWriter;
import Renderer.RayTracerBasic;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.*;

import scene.Scene;


public class tryyy {
    @Test
    public void test()
    {
        Scene scene = new Scene.SceneBuilder("test").build();
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0));
        camera.setVPDistance(1500);
        camera.setVPSize(200,250);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0)));


        Plane plane = new Plane(new Point(0, 0, -300), new Vector(0, 0, 1));

        double i=15, j=3;
        for(int x=-90, y=140; x<0; x+=j, y-=i, i+=1.5, j+=0.2)
        {
            scene.getLights().add(new SpotLight(new Color(400, 300, 300),new Point(x, y, 150),camera.getvTo()).setkC(1.0).setkL(0.005).setkQ(0.0005));
        }
        i=15;
        j=-3;
        for(int x=90, y=140; x>0; x+=j, y-=i, i+=1.5, j-=0.2)
        {
            scene.getLights().add(new SpotLight(new Color(400, 300, 300),new Point(x, y, 150),camera.getvTo()).setkC(1.0).setkL(0.005).setkQ(0.0005));
        }

        //DUBI
        scene.geometries.add(

                new Sphere(new Point(4, 8 ,18),25).setEmission(new Color(77,38,0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),

                new Sphere(new Point(26, -11, 18),8).setEmission(new Color(77,38,0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),

                new Sphere(new Point(-14,-12, 18),8).setEmission(new Color(77,38,0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt((new Double3(0.5))).setKr(new Double3(0))),

                new Sphere(new Point(-21,16,18),8).setEmission(new Color(77,38,0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),

                new Sphere(new Point(27,16,18),8).setEmission(new Color(77,38,0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),
                new Sphere(new Point(3,39,18),18).setEmission(new Color(77,38,0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),
                new Sphere(new Point(11,43,35.71),2.2).setEmission(new Color(java.awt.Color.BLACK)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0)).setKr(new Double3(0))),
                new Sphere(new Point(-4,43,35.94),2.2).setEmission(new Color(java.awt.Color.BLACK)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0)).setKr(new Double3(0))),
                //nose
                //new Triangle(new Point3D(0.2,36.6,37.6),new Point3D(5.7,36.85,37.70),new Point3D(3.33,31.8,36.65)).setEmission(new Color(java.awt.Color.gray)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0).setkR(0)),
                new Sphere(new Point(2.699,37.57,37.94),2.5).setEmission(new Color(java.awt.Color.gray)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0)).setKr(new Double3(0))),

                new Sphere(new Point(-8,58,18),7).setEmission(new Color(77,38,0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),
                new Sphere(new Point(14,58,18),7).setEmission(new Color(77,38,0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))));

        scene.geometries.add(plane.setEmission(new Color(255,153,153)).setMaterial(new Material() .setKd(0.5).setKs(0.5).setnShininess(1200).setKt(new Double3(0)).setKr(new Double3(0))));
        /////////////mirror
        scene.geometries.add(new Plane(new Point(0, -20, 0), new Vector(0, -40, 0)).setEmission(new Color(0,40,60)).setMaterial(new Material() .setKr( new Double3(1))));



        DirectionalLight direction_light = new DirectionalLight(new Color(0, 0, 1), new Vector(1, -1, 1));
        SpotLight spot_light = new SpotLight(new Color(0, 1, 0), new Point(4,8,18), new Vector(0,0, 1));//1, 4E-4, 2E-5,;
        PointLight point_light = new PointLight(new Color(400, 300, 300), new Point(0,145,50));
        //on mirror

        ImageWriter imageWriter = new ImageWriter("mini1", 600, 500);

        scene.getLights().add(point_light.setkC(1.0).setkL(0.05).setkQ(0.00005));
        scene.getLights().add(direction_light);
        scene.getLights().add(spot_light.setkC(1.0).setkL(4E-4).setkQ(2E-5));

        camera.setImageWriter(new ImageWriter("trytopro", 600,600)) //
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();
    }
}