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
        //creating the scene
    Scene scene = new Scene.SceneBuilder("Test scene").build();
    Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVPSize(150, 150) //
            .setVPDistance(1000);
    //is going to be the background of our project
    Plane plane = new Plane(new Point(0, 0, -300), new Vector(0, 0, 1));
//    double i=15, j=3;
//    for(int x=-90, y=140; x<0; x+=j, y-=i, i+=1.5, j+=0.2)
//    {
//        scene.getLights().add(new SpotLight(new Color(400, 300, 300),new Point(x, y, 150),camera1.getvTo()).setkC(1.0).setkL(0.005).setkQ(0.0005));
//    }
//    i=15;
//    j=-3;
//    for(int x=90, y=140; x>0; x+=j, y-=i, i+=1.5, j-=0.2)
//    {
//        scene.getLights().add(new SpotLight(new Color(400, 300, 300),new Point(x, y, 150),camera1.getvTo()).setkC(1.0).setkL(0.005).setkQ(0.0005));
//    }

    scene.geometries.add(
            new Sphere(new Point(4, 8 ,18),25).setEmission(new Color(77,38,0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),
            new Sphere(new Point(26, -11, 18),8).setEmission(new Color(77,38,0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),
            new Sphere(new Point(-14,-12, 18),8).setEmission(new Color(77,38,0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt((new Double3(0.5))).setKr(new Double3(0))),
            new Sphere(new Point(-21,16,18),8).setEmission(new Color(77,38,0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))),
            new Sphere(new Point(27,16,18),8).setEmission(new Color(77,38,0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))), new Sphere(new Point(3,39,18),18).setEmission(new Color(77,38,0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))), new Sphere(new Point(11,43,35.71),2.2).setEmission(new Color(java.awt.Color.BLACK)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0)).setKr(new Double3(0))), new Sphere(new Point(-4,43,35.94),2.2).setEmission(new Color(java.awt.Color.BLACK)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0)).setKr(new Double3(0))),
            //nose
            new Triangle(new Point(0.2,36.6,37.6),new Point(5.7,36.85,37.70),new Point(3.33,31.8,36.65)).setEmission(new Color(java.awt.Color.gray)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(Double3.ZERO).setKr(Double3.ZERO)),
            new Sphere(new Point(2.699,37.57,37.94),2.5).setEmission(new Color(java.awt.Color.gray)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0)).setKr(new Double3(0))),
            new Sphere(new Point(-8,58,18),7).setEmission(new Color(77,38,0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))), new Sphere(new Point(14,58,18),7).setEmission(new Color(77,38,0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setnShininess(100).setKt(new Double3(0.5)).setKr(new Double3(0))));
    scene.geometries.add(plane.setEmission(new Color(255,153,153)).setMaterial(new Material() .setKd(0.5).setKs(0.5).setnShininess(1200).setKt(new Double3(0)).setKr(new Double3(0))));
    scene.geometries.add(new Plane(new Point(0, -20, 0), new Vector(0, -40, 0)).setEmission(new Color(0,40,60)).setMaterial(new Material() .setKr( new Double3(1))));
    //creating the picture
        ImageWriter imageWriter = new ImageWriter("trytopro1", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
        camera1.writeToImage(); //
    }
}