package rays;

public class Vector3 {

  public static final Vector3 ZERO = new Vector3(0, 0, 0);

  private final double x;
  private final double y;
  private final double z;

  private Vector3(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public double x() {
    return x;
  }

  public double y() {
    return y;
  }

  public double z() {
    return z;
  }


  public double dot(Vector3 vec) {
    return x * vec.x + y * vec.y + z * vec.z;
  }

  public Vector3 scalar(double scalar) {
    return Vector3.of(x * scalar, y * scalar, z * scalar);
  }

  public Vector3 negate() {
    return Vector3.of(-x, -y, -z);
  }

  public Vector3 normalize() {
    double x = this.x;
    double y = this.y;
    double z = this.z;

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
    return Vector3.of(x + vec.x, y + vec.y, z + vec.z);
  }

  public Vector3 subtract(Vector3 vec) {
    return Vector3.of(x - vec.x, y - vec.y, z - vec.z);
  }

  public Vector3 multiply(Vector3 vec) {
    return Vector3.of(x * vec.x, y * vec.y, z * vec.z);
  }

  public double lengthSqr() {
    return x * x + y * y + z * z;
  }

  public double length() {
    return Math.sqrt(lengthSqr());
  }

  public static Vector3 of(double x, double y, double z) {
    return new Vector3(x, y, z);
  }

  public static Vector3 of(double v){
    return new Vector3(v,v,v);
  }

}
