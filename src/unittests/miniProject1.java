package unittests;

import Lighting.AmbientLight;
import Lighting.DirectionalLight;
import Lighting.PointLight;
import Lighting.SpotLight;
import Renderer.Camera;
import Renderer.ImageWriter;
import Renderer.RayTracerBasic;
import geometries.Plane;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

public class miniProject1 {

    @Test
    public void test()
    {

        //building the scene and the camera values:
        Scene scene = new Scene.SceneBuilder("test").build();
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0));
        camera.setVPDistance(1500);
        camera.setVPSize(200, 250);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0)));


        Plane plane = new Plane(new Point(0, 0, -300), new Vector(0, 0, 1));
//
//        double i=15, j=3;
//        for(int x=-90, y=140; x<0; x+=j, y-=i, i+=1.5, j+=0.2)
//        {
//            scene.getLights().add(new SpotLight(new Color(400, 300, 300),new Point(x, y, 150),camera.getvTo()).setkC(1.0).setkL(0.005).setkQ(0.0005));
//        }
//        i=15;
//        j=-3;
//        for(int x=90, y=140; x>0; x+=j, y-=i, i+=1.5, j-=0.2)
//        {
//            scene.getLights().add(new SpotLight(new Color(400, 300, 300),new Point(x, y, 150),camera.getvTo()).setkC(1.0).setkL(0.005).setkQ(0.0005));
//        }


        //creating the DUBI
        scene.geometries.add(
        //belly
        new Sphere(new Point(0,0,0),2.0).setEmission(new Color(255,128,255)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),
        //head
        new Sphere(new Point(0,0,3.5),1.5).setEmission(new Color(255,128,255)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),
        //right ear
        new Sphere(new Point(1,0,5.2),6.2).setEmission(new Color(255,128,255)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),
        //left ear
        new Sphere(new Point(-1,0,5.2),6.2).setEmission(new Color(255,128,255)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),
        //right eye
        new Sphere(new Point(0.5,0,3.9),0.03).setEmission(new Color(255,128,255)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),
        //left eye
        new Sphere(new Point(-0.5,0,3.9),0.03).setEmission(new Color(255,128,255)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),
        //nose
        new Sphere(new Point(0,0,3.5),0.03).setEmission(new Color(255,128,255)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),

        //Right hand:
        //first circle
        new Sphere(new Point(1.8,0,1),0.7).setEmission(new Color(255,128,255)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),
        //second circle
        new Sphere(new Point(2.3,0,1.4),0.5).setEmission(new Color(255,128,255)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),
        //third circle
        new Sphere(new Point(2.6,0,1.8),0.3).setEmission(new Color(255,128,255)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),

        //Left hand:
        //first circle
        new Sphere(new Point(-1.8,0,1),0.7).setEmission(new Color(255,128,255)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),
        //second circle
        new Sphere(new Point(-2.3,0,0.6),0.5).setEmission(new Color(255,128,255)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),
        //third circle
        new Sphere(new Point(-2.6,0,0.3),0.3).setEmission(new Color(255,128,255)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0)))
                );


        scene.geometries.add(plane.setEmission(new Color(128,0,128)).setMaterial(new Material() .setKd(0.5).setKs(0.5).setnShininess(1200).setKt(new Double3(0)).setKr(new Double3(0))));
        /////////////mirror
        scene.geometries.add(new Plane(new Point(0, -20, 0), new Vector(0, -40, 0)).setEmission(new Color(0,40,60)).setMaterial(new Material() .setKr( new Double3(1))));


        DirectionalLight direction_light = new DirectionalLight(new Color(0, 0, 1), new Vector(1, -1, 1));
        SpotLight spot_light = new SpotLight(new Color(0, 1, 0), new Point(4,8,18), new Vector(0,0, 1));//1, 4E-4, 2E-5,;
        PointLight point_light = new PointLight(new Color(400, 300, 300), new Point(0,145,50));

        scene.getLights().add(point_light.setkC(1.0).setkL(0.05).setkQ(0.00005));
        scene.getLights().add(direction_light);
        scene.getLights().add(spot_light.setkC(1.0).setkL(4E-4).setkQ(2E-5));

        camera.setImageWriter(new ImageWriter("trytopro", 60,60)) //
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();

    }
}
