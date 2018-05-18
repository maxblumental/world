package conquer;

import conquer.Closest.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClosestTest {

  @Test
  public void test1() {
    List<Point> points = Arrays.asList(sample(0, 0), sample(3, 4));

    double distance = Closest.smartMinimalDistance(points);

    assertEquals(5.0, distance, 1e-4);
  }

  @Test
  public void test2() {
    List<Point> points = Arrays.asList(
        sample(7, 7),
        sample(1, 100),
        sample(4, 8),
        sample(7, 7)
    );

    double distance = Closest.smartMinimalDistance(points);

    assertEquals(0.0, distance, 1e-4);
  }

  @Test
  public void test3() {
    List<Point> points = Arrays.asList(
        sample(4, 4),
        sample(-2, -2),
        sample(-3, -4),
        sample(-1, 3),
        sample(2, 3),
        sample(-4, 0),
        sample(1, 1),
        sample(-1, -1),
        sample(3, -1),
        sample(-4, 2),
        sample(-2, 4)
    );

    double distance = Closest.smartMinimalDistance(points);

    assertEquals(1.414213, distance, 1e-4);
  }

  private static Point sample(int a, int b) {
    return new Point(a, b);
  }
}