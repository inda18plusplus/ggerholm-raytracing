package rays.shapes;

import rays.Vector3;

public abstract class Shape {

  private Vector3 center;
  private Vector3 surfaceColor;
  private Vector3 emissionColor;
  private double transparency;
  private double reflectivity;

  Shape(Vector3 center, Vector3 surfaceColor, double reflectivity,
      double transparency, Vector3 emissionColor) {

    this.center = center;
    this.surfaceColor = surfaceColor;
    this.emissionColor = emissionColor;
    this.transparency = transparency;
    this.reflectivity = reflectivity;
  }

  public Vector3 surfaceColor() {
    return surfaceColor;
  }

  public Vector3 emissionColor() {
    return emissionColor;
  }

  public Vector3 center() {
    return center;
  }

  public double transparency() {
    return transparency;
  }

  public double reflectivity() {
    return reflectivity;
  }

  public abstract double intersects(Vector3 rayOrigin, Vector3 rayDirection);

}
