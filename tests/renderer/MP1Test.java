package renderer;


import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.BasicRayTracer;
import renderer.Render;
import scene.Scene;

/**
 * Testing Vector
 *
 */
public class MP1Test {

    private Scene scene = new Scene("Test scene");

    /**
     * test for mini-project 1 , build ablon model picture
     */
    @Test
    public void projectP1Test() {
        scene.lights.add(new SpotLight(new Color(300, 0, 0), new Point3D(0, 50, -750),//
                new Vector(0, -50, -1))
                .setKl(4E-5).setKq(2E-7));
        scene.lights.add(new PointLight(new Color(0, 0, 200), new Point3D(250, 25, -600))//
                .setKl(0.00001).setKq(0.000001));

        scene.lights.add(new DirectionalLight( new Color(150, 150, 150),new Vector(0, 0, -1)));

        /**
         * camera from the side of the scene
         */
        Camera camera = new Camera(new Point3D(0, -750, 0), new Vector(0, 0.35, -0.35), new Vector(0, 0.35, 0.35))
                .setViewPlaneSize(150, 150).setDistance(400);
        /**
         * camera above the scene
         */
        //Camera camera = new Camera(new Point3D(0, 50, 0), new Vector(0, 0, -1), new Vector(1, 0, 0))
        //        .setViewPlaneSize(150, 150).setDistance(400);

        Color secColor = new Color(175,175,175);

        Point3D center = new Point3D(0, 50, -800);
        Point3D p1 = new Point3D(0, 170, -800);
        Point3D p2 = new Point3D(104.4, 110, -800);
        Point3D p3 = new Point3D(104.4, -10, -800);
        Point3D p4 = new Point3D(0, -70, -800);
        Point3D p5 = new Point3D(-104.4, -10, -800);
        Point3D p6 = new Point3D(-104.4, 110, -800);

        /**
         * the poles of the game (that hold the plane - two triangles in each side)
         */
        /**
         * right side
         */
        Triangle tBase1= new Triangle(p2,new Point3D(130,125,-975), new Point3D(114,110,-975));
        tBase1.setEmission(secColor).setMaterial(new Material().setKt(0.5).
                setShininess(100).setKs(0.5).setKd(0.5));
        Triangle tBase2= new Triangle(p2,new Point3D(98,125,-975), new Point3D(114,110,-975));
        tBase2.setEmission(secColor).setMaterial(new Material().setKt(0.5).
                setShininess(100).setKs(0.5).setKd(0.5));
        /**
         * left side
         */
        Triangle tBase3= new Triangle(p5,new Point3D(-93.4,5,-970), new Point3D(-109.4,-10,-970));
        tBase3.setEmission(secColor).setMaterial(new Material().setKt(0.5).
                setShininess(100).setKs(0.5).setKd(0.5));
        Triangle tBase4= new Triangle(p5,new Point3D(-125.4,5,-970), new Point3D(-109.4,-10,-970));
        tBase4.setEmission(secColor).setMaterial(new Material().setKt(0.5).
                setShininess(100).setKs(0.5).setKd(0.5));

        Color tableCol  = new Color(20, 20, 20);
        Material triangleMat = new Material().setKr(0.7).setKd(0.3).setKs(1).setShininess(50).setKt(0.4);
        /**
         * plane of the game ablon
         */
        Triangle t1 = new Triangle(center, p1, p2); // up right
        Triangle t2 = new Triangle(center, p2, p3); // middle right
        Triangle t3 = new Triangle(center, p3, p4); // down right
        Triangle t4 = new Triangle(center, p4, p5); // down left
        Triangle t5 = new Triangle(center, p5, p6); // middle left
        Triangle t6 = new Triangle(center, p6, p1); // up left
        t1.setEmission(tableCol).setMaterial(triangleMat);
        t2.setEmission(tableCol).setMaterial(triangleMat);
        t3.setEmission(tableCol).setMaterial(triangleMat);
        t4.setEmission(tableCol).setMaterial(triangleMat);
        t5.setEmission(tableCol).setMaterial(triangleMat);
        t6.setEmission(tableCol).setMaterial(triangleMat);

        Material firstMat = new Material().setKr(0.05).setKd(0.1).setKs(1).setShininess(100);

        double depthSp = -793;
       // Sphere s1 = new Sphere(10,new Point3D(0, 151, depthSp));
       // Sphere s2 = new Sphere(10,new Point3D(-22, 138.5, depthSp));
        Sphere s3 = new Sphere(10,new Point3D(-44, 126, depthSp));
        Sphere s4 = new Sphere(10,new Point3D(-66, 113.5, depthSp));
        Sphere s5 = new Sphere(10,new Point3D(-88, 101, depthSp));

       // s1.setMaterial(firstMat);
       // s2.setMaterial(firstMat);
        s3.setMaterial(firstMat);
        s4.setMaterial(firstMat);
        s5.setMaterial(firstMat);


        Sphere s16 = new Sphere(10, new Point3D(-88, 77, depthSp));
        Sphere s15 = new Sphere(10, new Point3D(-66, 89.5, depthSp));
        Sphere s14 = new Sphere(10, new Point3D(-44, 102, depthSp));
        //Sphere s13 = new Sphere(10, new Point3D(-22, 114.5, depthSp));
       // Sphere s12 = new Sphere(10, new Point3D(0, 127, depthSp));
        Sphere s11 = new Sphere(10, new Point3D(22, 139.5, depthSp));

        s11.setMaterial(firstMat);
       // s12.setMaterial(firstMat);
       // s13.setMaterial(firstMat);
        s14.setMaterial(firstMat);
        s15.setMaterial(firstMat);
        s16.setMaterial(firstMat);

        Sphere s21 = new Sphere(10, new Point3D(0, 101, depthSp));
        Sphere s21_1 = new Sphere(10, new Point3D(0, 77, depthSp));
        Sphere s21_2 = new Sphere(10, new Point3D(0, 53, depthSp));  //white ball
        Sphere s21_3 = new Sphere(10, new Point3D(0, 29, depthSp));  //white ball
        Sphere s22 = new Sphere(10, new Point3D(-22, 89.5, depthSp));
        Sphere s22_1 = new Sphere(10, new Point3D(-22, 65.5, depthSp));//black ball
        Sphere s22_2 = new Sphere(10, new Point3D(-22, 41.5, depthSp));//white ball
        Sphere s22_3 = new Sphere(10, new Point3D(-22, 17.5, depthSp));//black ball
        Sphere s23 = new Sphere(10, new Point3D(-44, 77, depthSp)); //black ball
        Sphere s23_1 = new Sphere(10, new Point3D(-44, 53, depthSp));//black ball

        s21.setMaterial(firstMat);
        s21_1.setMaterial(firstMat);
        s21_2.setMaterial(firstMat).setEmission(secColor);
        s21_3.setMaterial(firstMat).setEmission(secColor);
        s22.setMaterial(firstMat);
        s22_1.setMaterial(firstMat);
        s22_2.setMaterial(firstMat).setEmission(secColor);
        s22_3.setMaterial(firstMat);
        s23.setMaterial(firstMat);
        s23_1.setMaterial(firstMat);

        Sphere s71 = new Sphere(10, new Point3D(0, -1, depthSp));
        Sphere s72 = new Sphere(10, new Point3D(22, 10.5, depthSp));
        Sphere s72_1 = new Sphere(10, new Point3D(22, 34.5, depthSp));
        Sphere s73 = new Sphere(10, new Point3D(44, 23, depthSp));


        s71.setEmission(secColor).setMaterial(firstMat);
        s72.setEmission(secColor).setMaterial(firstMat);
        s72_1.setEmission(secColor).setMaterial(firstMat);
        s73.setEmission(secColor).setMaterial(firstMat);

        Sphere s81 = new Sphere(10, new Point3D(88, 23, depthSp));
       // Sphere s82 = new Sphere(10, new Point3D(66, 10.5, depthSp));
       // Sphere s83 = new Sphere(10, new Point3D(44, -2, depthSp));
        Sphere s84 = new Sphere(10, new Point3D(22, -14.5, depthSp));
        Sphere s85 = new Sphere(10, new Point3D(0, -27, depthSp));
        Sphere s86 = new Sphere(10, new Point3D(-22, -39.5, depthSp));
        s81.setEmission(secColor).setMaterial(firstMat);
       // s82.setEmission(secColor).setMaterial(firstMat);
      //  s83.setEmission(secColor).setMaterial(firstMat);
        s84.setEmission(secColor).setMaterial(firstMat);
        s85.setEmission(secColor).setMaterial(firstMat);
        s86.setEmission(secColor).setMaterial(firstMat);

       // Sphere s91 = new Sphere(10, new Point3D(88, -1, depthSp));
       // Sphere s92 = new Sphere(10, new Point3D(66, -13.5, depthSp));
        Sphere s93 = new Sphere(10, new Point3D(44, -26, depthSp));
        Sphere s94 = new Sphere(10, new Point3D(22, -38.5, depthSp));
        Sphere s95 = new Sphere(10, new Point3D(0, -51, depthSp));
      //  s91.setEmission(secColor).setMaterial(firstMat);
      //  s92.setEmission(secColor).setMaterial(firstMat);
        s93.setEmission(secColor).setMaterial(firstMat);
        s94.setEmission(secColor).setMaterial(firstMat);
        s95.setEmission(secColor).setMaterial(firstMat);



        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.geometries.add( tBase1, tBase2, tBase3, tBase4, t1, t2, t3, t4, t5, t6, //
                /*s1,s2,*/  s3, s4, s5, //
                s11, /*s12, s13,*/ s14, s15, s16, //
                s21, s21_1, s21_2 ,s21_3 ,s22, s22_1,s22_2, s22_3,s23, s23_1, //
                s71, s72, s72_1,s73, //
                s81, /*s82, s83,*/ s84, s85, s86,//
               /*s91, s92,*/s93, s94, s95);

        ImageWriter imageWriter = new ImageWriter("P1 17x17", 1000, 1000);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));

        /**
         * if improvement variable  is true we call to the improvement function (anti- alias ,Super-sampling);
         */
        boolean improvement = true;

        if (improvement){
            int resolution= 17;
            render.renderImageImprov(resolution);
            render.writeToImage();
        }
        else{
            render.renderImage();
            render.writeToImage();
        }
    }

}