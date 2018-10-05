package rays;

public class Vector3 {

  public static final Vector3 ZERO = new Vector3(0, 0, 0);

  private final double locX;
  private final double locY;
  private final double locZ;

  private Vector3(double x, double y, double z) {
    this.locX = x;
    this.locY = y;
    this.locZ = z;
  }

  public double getX() {
    return locX;
  }

  public double getY() {
    return locY;
  }

  public double getZ() {
    return locZ;
  }


  public double dot(Vector3 vec) {
    return locX * vec.locX + locY * vec.locY + locZ * vec.locZ;
  }

  public Vector3 scalar(double scalar) {
    return Vector3.of(locX * scalar, locY * scalar, locZ * scalar);
  }

  public Vector3 negate() {
    return Vector3.of(-locX, -locY, -locZ);
  }

  /**
   * Creates a copy of the current vector and normalizes it.
   *
   * @return The normalized copy of the current vector.
   */
  public Vector3 normalize() {
    double x = this.locX;
    double y = this.locY;
    double z = this.locZ;

    double nor = lengthSqr();
    if (nor > 0) {
      double inv = 1 / length();
      x *= inv;
      y *= inv;
      z *= inv;
    }

    return Vector3.of(x, y, z);
  }

  public Vector3 add(Vector3 vec) {
    return Vector3.of(locX + vec.locX, locY + vec.locY, locZ + vec.locZ);
  }

  public Vector3 subtract(Vector3 vec) {
    return Vector3.of(locX - vec.locX, locY - vec.locY, locZ - vec.locZ);
  }

  public Vector3 multiply(Vector3 vec) {
    return Vector3.of(locX * vec.locX, locY * vec.locY, locZ * vec.locZ);
  }

  public double lengthSqr() {
    return locX * locX + locY * locY + locZ * locZ;
  }

  public double length() {
    return Math.sqrt(lengthSqr());
  }

  public static Vector3 of(double x, double y, double z) {
    return new Vector3(x, y, z);
  }

  public static Vector3 of(double v) {
    return new Vector3(v, v, v);
  }

  /**
   * Returns the ARGB value. X corresponds to red, Y to green and Z to blue. The alpha is constant.
   *
   * @return The ARGB made from the vectors coordinates.
   */
  public int as255Rgb() {
    int red = (int) (Math.min(1, locX) * 255);
    int green = (int) (Math.min(1, locY) * 255);
    int blue = (int) (Math.min(1, locZ) * 255);
    int alpha = 255;

    return (alpha << 24) | (red << 16) | (green << 8) | blue;
  }

}
