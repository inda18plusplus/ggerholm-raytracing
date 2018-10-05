package rays;

import java.util.List;
import rays.shapes.Shape;

class RayTracer {

  private static final int MAX_RAY_DEPTH = 5;

  static Vector3 trace(Vector3 rayOrigin, Vector3 rayDirection, List<Shape> shapes, int depth) {

    double near = Double.MAX_VALUE;
    Shape shape = null;

    for (Shape s : shapes) {
      double intersects = s.intersects(rayOrigin, rayDirection);

      if (intersects != -Double.MAX_VALUE && intersects < near) {
        near = intersects;
        shape = s;
      }
    }

    if (shape == null) {
      return Vector3.of(2, 2, 2);
    }

    Vector3 surfaceColor = Vector3.ZERO;
    Vector3 intersectHit = rayOrigin.add(rayDirection.scalar(near));
    Vector3 normalHit = intersectHit.subtract(shape.center()).normalize();

    double bias = 0.0001;
    boolean inside = false;

    if (rayDirection.dot(normalHit) > 0) {
      normalHit = normalHit.negate();
      inside = true;
    }

    if ((shape.transparency() > 0 || shape.reflectivity() > 0)
        && depth < MAX_RAY_DEPTH) {
      double facingRatio = -rayDirection.dot(normalHit);
      double fresnelEffect = 1 * 0.1 + Math.pow(1 - facingRatio, 3) * (1 - 0.1);

      Vector3 reflectionDirection = rayDirection
          .subtract(
              normalHit
                  .scalar(2 * rayDirection.dot(normalHit)))
          .normalize();
      Vector3 reflection = trace(
          intersectHit.add(normalHit.scalar(bias)),
          reflectionDirection,
          shapes,
          depth + 1);

      Vector3 refraction = Vector3.ZERO;

      if (shape.transparency() != 0) {
        double ior = 1.1;
        double eta = inside ? ior : 1 / ior;
        double cosi = -normalHit.dot(rayDirection);
        double k = 1 - eta * eta * (1 - cosi * cosi);

        Vector3 refractionDirection = rayDirection.scalar(eta)
            .add(normalHit.scalar(eta * cosi - Math.sqrt(k))).normalize();
        refraction = trace(intersectHit.subtract(normalHit.scalar(bias)), refractionDirection,
            shapes, depth + 1);
      }

      surfaceColor = reflection
          .scalar(fresnelEffect)
          .add(refraction
              .scalar(1 - fresnelEffect)
              .scalar(shape.transparency()))
          .multiply(shape.surfaceColor());

    } else {

      for (int i = 0; i < shapes.size(); ++i) {
        Shape s = shapes.get(i);

        if (s.emissionColor().getX() > 0) {
          double transmission = 1;
          Vector3 lightDirection = s.center().subtract(intersectHit).normalize();

          for (int j = 0; j < shapes.size(); j++) {
            if (i == j) {
              continue;
            }

            if (shapes.get(j).intersects(intersectHit.add(normalHit.scalar(bias)),
                lightDirection) != -Double.MAX_VALUE) {
              transmission = 0;
              break;
            }
          }

          surfaceColor = surfaceColor.add(shape.surfaceColor()
              .scalar(transmission * Math.max(0, normalHit.dot(lightDirection))))
              .multiply(shape.emissionColor());

        }
      }
    }

    return surfaceColor.add(shape.emissionColor());
  }

}
