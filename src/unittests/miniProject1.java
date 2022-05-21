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
                new Sphere(new Point(0,-4,-200),40).setEmission(new Color(0,0,47)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(300).setKt(new Double3(0.5)).setKr(new Double3(0))));

        //SHARKS
        Triangle shark1=new Triangle(new Point(-52, -32, -50), new Point(-32, -32, -50), new Point(-52, -12, -50));
        scene1.geometries.add(shark1.setEmission( new Color(76,76,76)).setMaterial(new Material().setKt(new Double3(0)) .setKr( new Double3(0))));

        Triangle shark2=new Triangle(new Point(-62, -32, -50), new Point(-42, -32, -50), new Point(-62, -12, -50));
        scene1.geometries.add(shark2.setEmission( new Color(76,76,76)).setMaterial(new Material().setKt(new Double3(0)) .setKr( new Double3(0))));

        Triangle shark3=new Triangle(new Point(-54, -32, -10), new Point(-34, -32, -10), new Point(-54, -12, -10));
        scene1.geometries.add(shark3.setEmission( new Color(76,76,76)).setMaterial(new Material().setKt(new Double3(0)) .setKr( new Double3(0))));

        //SKY
        Plane plane2 = new Plane(new Point(0, 0, -300), new Vector(0, 0, 1));
        Plane plane=new Plane(new Point(0, -20, 0), new Vector(0, -40, 0));
        scene1.geometries.add(plane.setEmission(new Color(0,0,40))
        .setMaterial(new Material().setKd(0.0).setKs(0.0).setnShininess(0).setKt(new Double3(0)).setKr(new Double3(0))));

        //SEA
        scene1.geometries.add(plane2.setEmission( new Color(5,0,54)).setMaterial(new Material().setKt(new Double3(0)) .setKr( new Double3(0))));


        //light additions
        scene1.getLights().add(new DirectionalLight(new Color(300,200,0), new Vector(-0.3,-2,0)));
        scene1.getLights().add(new DirectionalLight(new Color(800,200,0), new Vector(0,-2,-1)));
        scene1.getLights().add(new SpotLight(new Color(800, 200, 0), new Point(0, 40, 100), new Vector(0, 1, 4))
                .setkL(0.0004).setkQ(0.0000006));
        scene1.getLights().add(new SpotLight(new Color(0,0,47), new Point(0,-4,-300), new Vector(-1,-1,-1)) //
                .setkL(0.00001).setkQ(0.000005));


        //creating the picture
        ImageWriter imageWriter = new ImageWriter("trytopro", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage(); //
        camera1.writeToImage(); //
    }
}


