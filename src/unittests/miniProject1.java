package unittests;

import Lighting.AmbientLight;
import Lighting.DirectionalLight;
import Lighting.PointLight;
import Lighting.SpotLight;
import Renderer.Camera;
import Renderer.ImageWriter;
import Renderer.RayTracerBasic;
import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;

public class miniProject1 {

    @Test
    public void test()
    {

        Scene scene1 = new Scene.SceneBuilder("Test scene").build();
        Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150) //
                .setVPDistance(1000);


        //creating the sunset
        //SUN
        scene1.geometries.add(
        new Sphere(new Point(0,-4,-200),40).setEmission(new Color(0,0,47)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(300).setKt(new Double3(0)).setKr(new Double3(0))));

        //SKY
        Plane plane2 = new Plane(new Point(0, 0, -300), new Vector(0, 0, 1));
        Plane plane=new Plane(new Point(0, -20, 0), new Vector(0, -40, 0));
        scene1.geometries.add(plane.setEmission(new Color(0,0,40)));
        //.setMaterial(new Material() .setKd(1.5).setKs(1.5).setnShininess(300).setKt(new Double3(0)).setKr(new Double3(0))));
        //SEA
       scene1.geometries.add(plane2.setEmission( new Color(5,0,54)).setMaterial(new Material().setKt(new Double3(0)) .setKr( new Double3(0))));
        //SHARK
        Triangle shark=new Triangle(new Point(-52, -32, -28), new Point(-32, -32, -28), new Point(-52, -12, -28));
        scene1.geometries.add(shark.setEmission( new Color(76,76,76)).setMaterial(new Material().setKt(new Double3(1)) .setKr( new Double3(0))));


        //light additions
        scene1.getLights().add(new DirectionalLight(new Color(300,200,0), new Vector(-0.3,-2,0)));
        //scene1.getLights().add(new DirectionalLight(new Color(400,200,250), new Vector(0,-2,0)));
        scene1.getLights().add(new DirectionalLight(new Color(800,200,0), new Vector(0,-2,-1)));
        scene1.getLights().add(new SpotLight(new Color(800, 200, 0), new Point(0, 40, 100), new Vector(0, 1, 4))
                .setkL(0.0004).setkQ(0.0000006));

        //creating the picture
        ImageWriter imageWriter = new ImageWriter("trytopro", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage(); //
        camera1.writeToImage(); //
    }
}

        //        Scene scene1 = new Scene.SceneBuilder("Test scene").build();
//        Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
//                .setVPSize(150, 150) //
//                .setVPDistance(1000);
//
//
//        //creating the sunset
//        //SUN
//        scene1.geometries.add(
//        new Sphere(new Point(0,0,-250),40).setEmission(new Color(BLACK)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(300).setKt(new Double3(0.5)).setKr(new Double3(0))));
//
//        //SKY
//        Plane plane = new Plane(new Point(0, 0, -300), new Vector(0, 0, 1));
//        scene1.geometries.add(plane.setEmission(new Color(163,62,0)).setMaterial(new Material() .setKd(0.5).setKs(0.5).setnShininess(2000).setKt(new Double3(0)).setKr(new Double3(0))));
//        //SEA
//        Plane plane2=new Plane(new Point(0, -20, 0), new Vector(0, -40, 0));
//        scene1.geometries.add(plane2.setEmission( new Color(BLUE)).setMaterial(new Material() .setKr( new Double3(1))));
//        scene1.getLights().add( //
//                new SpotLight(new Color(800, 200, 0), new Point(40, 40, 115), new Vector(1, -1, -4)) //
//                        .setkL(4E-4).setkQ(2E-5));
//        scene1.getLights().add(new PointLight(new Color(800,400,250)/*,new Point(0,-20,70)*/,new Vector(0,-1,0)));
//
//        ImageWriter imageWriter = new ImageWriter("trytopro", 500, 500);
//        camera1.setImageWriter(imageWriter) //
//                .setRayTracer(new RayTracerBasic(scene1)) //
//                .renderImage(); //
//        camera1.writeToImage(); //

