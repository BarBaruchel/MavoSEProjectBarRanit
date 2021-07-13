package renderer;

import java.awt.Color;


import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Coordinate;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class TheBestPhoto {
    @Test
    public void test1()
    {

        Scene scene = new Scene("TheBestPhoto");
       // scene.set_screenDistance(150);
        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setViewPlaneSize(150, 150).setDistance(150);
        scene.setAmbientLight(new AmbientLight(new primitives.Color(java.awt.Color.WHITE), 0.1));


        //up left
        Triangle triangle1 = new Triangle(new Point3D( 30, 10, -300), new Point3D(  -20, 50, -300), new Point3D(  -20, -50, -300) );
        triangle1.setEmission(new primitives.Color(new Color(31,195,243)))
                .setMaterial(new Material().setKt(1));
        Triangle triangle2 = new Triangle(new Point3D( 30, 10, -300), new Point3D( 30, -90, -300), new Point3D(  -20, -50, -300));
        triangle2.setEmission(new primitives.Color(new Color(0,99,175)))
                .setMaterial(new Material().setKt(1));
        Triangle triangle3 = new Triangle(new Point3D( 30, 10, -300),new Point3D(  80, 50, -300), new Point3D(  80, -50, -300));
        triangle3.setEmission(new primitives.Color(new Color(37,33,96)))
               .setMaterial(new Material().setKt(1));
        Triangle triangle4 = new Triangle(new Point3D( 30, 10, -300),new Point3D( 30, -90, -300), new Point3D(  80, -50, -300));
        triangle4.setEmission(new primitives.Color(new Color(158,31,98)))
               .setMaterial(new Material().setKt(1));


        Sphere sphere = new Sphere( 150,new Point3D(30,10,-300));
        sphere.setEmission(new primitives.Color(new Color(0,20,20)))
               .setMaterial(new Material().setKd(0.8).setKt(1).setKs(0.6).setShininess(20));

      //  Material material = new Material();
       // material.setShininess(20);
       // material.setKt(1);
       // material.setKs(0.6);

       // sphere.setMaterial(material);

      //  Plane plane1=new Plane(new Point3D (-200, 0, -230), new Vector(new Point3D(-3,0,-1)));
      //  plane1.setEmission(new primitives.Color(new Color(20,20,20)))
       //         .setMaterial(new Material().setKd(0.5).setKt(0).setKs(1).setShininess(20).setKr(0.6));
        Triangle triangleMiror1= new Triangle(new Point3D(-500,500,-500),new Point3D(-500,-500,-500), new Point3D(500, -500, -1000));
        triangleMiror1.setEmission(new primitives.Color(new Color(20,20,20)))
                .setMaterial(new Material().setKd(0.5).setKt(0).setKs(1).setShininess(20).setKr(0.8));
        Triangle triangleMiror2= new Triangle(new Point3D(-502,500,-500),new Point3D(500,500,-1000), new Point3D(498, -500, -1000));
        triangleMiror2.setEmission(new primitives.Color(new Color(20,20,20)))
                .setMaterial(new Material().setKd(0.5).setKt(0).setKs(1).setShininess(20).setKr(0.8));


      //  material.setShininess(20);
       // material.setKt(0);
      //  material.setKr(0.6);
      // material.setKs(1);
      // material.setKd(0.5);
       // plane1.setColor(new Color(20,20,20));
       // plane1.setEmission(new primitives.Color(new Color(128,128,128)));
      //  plane1.setMaterial(material);



       // scene.lights.add(new SpotLight(new Color(50,50,255), new Point3D(200,200, -150),   new Vector(new Point3D(-2, 2, -3))));
      //  scene.lights.add(new SpotLight(new primitives.Color(new Color(50,50,255)), new Point3D(60, 50, 0), new Vector(0, 0, -1))
        scene.lights.add(new SpotLight(new primitives.Color(new Color(50,50,255)), new Point3D(200,200, -150), new Vector(new Point3D(-2, 2, -3)))
                .setKl(0.0004).setKq(0.0000006));
        scene.geometries.add(triangle1);
        scene.geometries.add(triangle2);
        scene.geometries.add(triangle3);
        scene.geometries.add(triangle4);
        scene.geometries.add(sphere);
        //scene.geometries.add(plane1);
        scene.geometries.add(triangleMiror1);
        scene.geometries.add(triangleMiror2);


        ImageWriter imageWriter = new ImageWriter("TheBestPhoto", 500, 500);
        Render render = new Render();
        render.setImageWriter(imageWriter)
                .setCamera(camera)
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        imageWriter.writeToImage();
    }

}
