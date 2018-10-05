package rays;

import java.io.IOException;
import rays.shapes.Sphere;

public class Main {

  private Main() throws IOException {
    Scene scene = new Scene();

    scene.addShape(
        new Sphere(
            Vector3.of(0, -10004, -20),
            10000,
            Vector3.of(0.2F, 0.2F, 0.2F),
            0,
            0,
            Vector3.ZERO));
    scene.addShape(
        new Sphere(
            Vector3.of(0, 0, -20),
            4,
            Vector3.of(1F, 0.32F, 0.26F),
            1,
            0.5F,
            Vector3.ZERO));
    scene.addShape(
        new Sphere(
            Vector3.of(5, -1, -15),
            2,
            Vector3.of(0.9F, 0.76F, 0.46F),
            1,
            0,
            Vector3.ZERO));
    scene.addShape(
        new Sphere(
            Vector3.of(5, 0, -25),
            3,
            Vector3.of(0.65F, 0.77F, 0.97F),
            1,
            0,
            Vector3.ZERO));
    scene.addShape(
        new Sphere(
            Vector3.of(-5.5F, 0, -15),
            3,
            Vector3.of(0.9F, 0.9F, 0.9F),
            1,
            0,
            Vector3.ZERO));

    scene.createLight(Vector3.of(0, 20, -30), 3, 3);

    scene.renderToFile(1920, 1080, "images/output.png");
  }

  public static void main(String[] args) throws IOException {
    new Main();
  }

}
