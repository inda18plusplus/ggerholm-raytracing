package rays.shapes;

import rays.Vector3;

public class Sphere extends Shape {

  private double radiusSqr;

  public Sphere(Vector3 center, double radius, Vector3 surfaceColor, double reflectivity,
      double transparency, Vector3 emissionColor) {
    super(center, surfaceColor, reflectivity, transparency, emissionColor);

    radiusSqr = radius * radius;
  }

  public Sphere(Vector3 center, double radius, Vector3 surfaceColor, double reflectivity,
      double transparency) {
    this(center, radius, surfaceColor, reflectivity, transparency, Vector3.ZERO);
  }

  public Sphere(Vector3 center, double radius) {
    this(center, radius, Vector3.ZERO, 0, 0, Vector3.ZERO);
  }

  @Override
  public double intersects(Vector3 rayOrigin, Vector3 rayDirection) {
    Vector3 vec = center().subtract(rayOrigin);
    double tca = vec.dot(rayDirection);
    if (tca < 0) {
      return -Double.MAX_VALUE;
    }

    double distanceSqr = vec.dot(vec) - tca * tca;
    if (distanceSqr > radiusSqr) {
      return -Double.MAX_VALUE;
    }

    double thc = Math.sqrt(radiusSqr - distanceSqr);
    double v = tca - thc;
    if (tca - thc < 0) {
      v = tca + thc;
    }

    return v;
  }

}
