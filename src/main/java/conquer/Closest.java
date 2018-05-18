package conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Closest {

  static class Point {
    long x;
    long y;

    Point(long x, long y) {
      this.x = x;
      this.y = y;
    }

    @Override public String toString() {
      return "Point{" +
          "x=" + x +
          ", y=" + y +
          '}';
    }
  }

  static double smartMinimalDistance(List<Point> points) {
    points.sort(Comparator.comparingLong(o -> o.x));
    return smartMinimalDistanceRecursion(points);
  }

  private static double smartMinimalDistanceRecursion(List<Point> points) {
    int size = points.size();
    if (size == 2) {
      return dist(points.get(0), points.get(1));
    } else if (size < 2) {
      return Double.POSITIVE_INFINITY;
    }

    int middle = size / 2;
    double d1 = smartMinimalDistanceRecursion(points.subList(0, middle));
    double d2 = smartMinimalDistanceRecursion(points.subList(middle, size));

    double dRough = Math.min(d1, d2);

    List<Point> strip = points.stream()
        .filter(point -> Math.abs(point.x - points.get(middle).x) <= dRough)
        .sorted(Comparator.comparingLong(o -> o.y))
        .collect(Collectors.toList());

    double dStrip = Double.POSITIVE_INFINITY;
    for (int i = 0; i < strip.size(); i++) {
      for (int j = i + 1; j < Math.min(i + 7, strip.size()); j++) {
        double dist = dist(strip.get(i), strip.get(j));
        if (dist < dStrip) {
          dStrip = dist;
        }
      }
    }

    return Math.min(dRough, dStrip);
  }

  private static double dist(Point a, Point b) {
    double dx = (double) a.x - b.x;
    double dy = (double) a.y - b.y;
    return Math.sqrt(dx * dx + dy * dy);
  }

  public static void main(String[] args) {
    reader = new BufferedReader(new InputStreamReader(System.in));
    writer = new PrintWriter(System.out);
    int n = nextInt();
    List<Point> points = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      points.add(new Point(nextInt(), nextInt()));
    }
    System.out.println(smartMinimalDistance(points));
    writer.close();
  }

  private static BufferedReader reader;
  private static PrintWriter writer;
  private static StringTokenizer tok = new StringTokenizer("");

  private static String next() {
    while (!tok.hasMoreTokens()) {
      String w = null;
      try {
        w = reader.readLine();
      } catch (Exception e) {
        e.printStackTrace();
      }
      if (w == null) {
        return null;
      }
      tok = new StringTokenizer(w);
    }
    return tok.nextToken();
  }

  private static int nextInt() {
    return Integer.parseInt(next());
  }
}
