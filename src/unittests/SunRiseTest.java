package unittests;

import Lighting.PointLight;
import Lighting.SpotLight;
import Renderer.Camera;
import Renderer.ImageWriter;
import Renderer.RayTracerBasic;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

/**
 * this is our first image
 * we create a sunrise scene
 * including sea, birds and the sun
 * using plane, sphere and triangles
 */
public class SunRiseTest {
    @Test
    public void test()
    {

        //creating the scene
        Scene scene1 = new Scene.SceneBuilder("Test scene").build();
        Camera camera1 = new Camera(new Point(0, 0, 1000),
                new Vector(0, 0, -1),
                new Vector(0, 1, 0))
                .setVPSize(150, 150)
                .setVPDistance(1000)
                .setRayTracer(new RayTracerBasic(scene1))
                .setsAntiA(false)
                .setsSoftShadows(true);


        //creating the elements

        //SKY (we chose to create it from sphere  to give the feeling of a sky dome)
        Geometry sky = new Sphere(new Point(0,0,-1000),1000)
                .setEmission(new Color(500,200,0))
                .setMaterial(new Material()
                        .setKd(0.5)
                        .setKs(0.5)
                        .setnShininess(300));
        //adding the sky into the picture
        scene1.geometries.add(sky);

        //SUN
        Geometry sun=new Sphere(new Point(0,10,-80),85)
                .setEmission(new Color(200,80,0))
                .setMaterial(new Material()
                        .setKd(0.0)
                        .setKs(1.0)
                        .setnShininess(300)
                        .setKt(new Double3(1.5))
                        .setKr(new Double3(0.4)));
        //adding the sun into the picture
        scene1.geometries.add(sun);

        //SEA
         Geometry sea=new Plane(new Point(0,-15,2),
                 new Vector(0,1,2))
                .setEmission(new Color(0,200,400))
                .setMaterial(new Material()
                        .setKd(0.0)
                        .setKs(0.5)
                        .setnShininess(300)
                        .setKt(new Double3(0.06))
                        .setKr(new Double3(0.2)));
        //adding the sea into the picture
        scene1.geometries.add(sea);

        //BOAT
        Geometry boat= new Polygon(new Point(-35,-9,20),
                new Point(-41,-17,20),
                new Point(-59,-17,20),
                new Point(-65,-9,20))
                .setEmission(new Color(128,104,115));

        //SAIL
        //first sail
        Geometry sail1= new Triangle(new Point(-50,10,20),
                new Point(-41,-10,20),
                new Point(-50,-10,20))
                .setEmission(new Color(java.awt.Color.lightGray));

        //second sail
        Geometry sail2= new Triangle(new Point(-50,10,20),
                new Point(-50,-10,20),
                new Point(-59,-10,20))
                .setEmission(new Color(java.awt.Color.gray));

        //adding the boat into the picture
        scene1.geometries.add(boat,sail1,sail2);

        //BIRDS
        //FIRST BIRD
        //left wing
        Geometry bird1left= new Triangle(new Point(20,25,2),
                new Point(20,30,2),
                new Point(1,40,2))
                .setEmission(new Color(java.awt.Color.black));

        //right wing
        Geometry bird1right= new Triangle(new Point(20,25,2),
                new Point(20,30,2),
                new Point(35,37,2))
                .setEmission(new Color(java.awt.Color.black));

        //SECOND BIRD
        //left wing
        Geometry bird2left= new Triangle(new Point(35,43.75,0),
                new Point(35,48,0),
                new Point(15,62,0))
                .setEmission(new Color(java.awt.Color.black));

        //right wing
        Geometry bird2right= new Triangle(new Point(35,43.75,0),
                new Point(35,48,0),
                new Point(55,57.35,0))
                .setEmission(new Color(java.awt.Color.black));

        //THIRD BIRD
        //left wing
        Geometry bird3left= new Triangle(new Point(48,18,-2),
                new Point(48,23.8,-2),
                new Point(35,30,0))
                .setEmission(new Color(java.awt.Color.black));

        //right wing
        Geometry bird3right= new Triangle(new Point(35,18,-2),
                new Point(35,23.8,-2),
                new Point(52,30,0))
                .setEmission(new Color(java.awt.Color.black));

        //adding the birds into the picture
        scene1.geometries.add(bird1left,bird1right,bird2left,bird2right,bird3left,bird3right);



        //light addition
        //SUNLIGHT
        scene1.getLights().add(
                new SpotLight(new Color(800, 400, 400),
                        new Point(0, 30, 50),
                        new Vector(0, -3, -2))
                        .setkL(4E-4).setkQ(2E-5));
        //DAYLIGHT
        scene1.getLights().add(
                new SpotLight(new Color(0, 0, 800),
                        new Point(150, -30, 50),
                        new Vector(0, 3, -2))
                        .setkL(1E-5).setkQ(1.5E-7));

        //SEA COLOR
        scene1.getLights().add(
                new SpotLight(new Color(java.awt.Color.cyan),
                        new Point(2,5,20),
                        new Vector(0,3,-2)));


        //creating the picture
        ImageWriter imageWriter = new ImageWriter("sunRise", 2000, 2000);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage(); //
        camera1.writeToImage(); //
    }
}