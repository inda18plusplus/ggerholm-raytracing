package rays;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import rays.shapes.Shape;
import rays.shapes.Sphere;

public class Scene {

  private List<Shape> shapes;

  public Scene() {
    shapes = new ArrayList<>();
  }

  public void addShape(Shape shape) {
    shapes.add(shape);
  }

  public void createLight(Vector3 location, double radius, double intensity) {
    Sphere light = new Sphere(location, radius, Vector3.ZERO, 0, 0, Vector3.of(intensity));
    shapes.add(light);
  }

  /**
   * Renders the current scene to the specified file.
   *
   * @param width The width in pixels of the rendered scene.
   * @param height The height in pixels of the rendered scene.
   * @param fileName The output filename, including extension.
   * @throws IOException If an error occurred while writing the image to the output file.
   */
  public void renderToFile(int width, int height, String fileName) throws IOException {
    double invWidth = 1F / width;
    double invHeight = 1F / height;
    double fov = 30;
    double aspectRatio = width / (double) height;
    double angle = Math.tan(Math.PI * 0.5F * fov / 180);

    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        double rayX = (2 * ((x + 0.5F) * invWidth) - 1) * angle * aspectRatio;
        double rayY = (1 - 2 * (y + 0.5F) * invHeight) * angle;
        Vector3 rayDirection = Vector3.of(rayX, rayY, -1).normalize();
        Vector3 color = RayTracer.trace(Vector3.ZERO, rayDirection, shapes, 0);
        bufferedImage.setRGB(x, y, color.as255Rgb());
      }
    }

    String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
    ImageIO.write(bufferedImage, extension, new File(fileName));
  }

}
