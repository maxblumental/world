import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class PointsAndSegmentsTest {

  @Test
  @Ignore
  public void generalStressTest() {
    Random random = new Random(3);
    int limit = 3;
    int s = 1 + random.nextInt(limit);
    int p = 1 + random.nextInt(limit);
    while (true) {
      int[] starts = new int[s + 1];
      int[] ends = new int[s + 1];
      int[] starts1 = new int[s];
      int[] ends1 = new int[s];
      int[] points = generatePointsAndSegments(random, s, p, 20, starts, ends, starts1, ends1);

      int[] ints = PointsAndSegments.fastCountSegments(starts, ends, points);
      int[] ints1 = PointsAndSegments.naiveCountSegments(starts1, ends1, points);
      String expected =
          String.format("%s, %s, %s => %s", Arrays.toString(starts1), Arrays.toString(ends1),
              Arrays.toString(points), Arrays.toString(ints1));
      String actual =
          String.format("%s, %s, %s => %s", Arrays.toString(starts), Arrays.toString(ends),
              Arrays.toString(points), Arrays.toString(ints));
      assertArrayEquals(expected + "\n" + actual, ints1, ints);
    }
  }

  @Test
  @Ignore
  public void stressTest() {
    Random random = new Random(2);
    int N = 4;
    while (true) {
      int[] starts = new int[N + 1];
      int[] ends = new int[N + 1];
      int[] starts1 = new int[N];
      int[] ends1 = new int[N];
      int[] points = generatePointsAndSegments(random, N, N, 500, starts, ends, starts1, ends1);

      int[] ints = PointsAndSegments.fastCountSegments(starts, ends, points);
      int[] ints1 = PointsAndSegments.naiveCountSegments(starts1, ends1, points);
      String expected =
          String.format("%s, %s, %s => %s", Arrays.toString(starts1), Arrays.toString(ends1),
              Arrays.toString(points), Arrays.toString(ints1));
      String actual =
          String.format("%s, %s, %s => %s", Arrays.toString(starts), Arrays.toString(ends),
              Arrays.toString(points), Arrays.toString(ints));
      assertArrayEquals(expected + "\n" + actual, ints1, ints);
    }
  }

  private int[] generatePointsAndSegments(Random random, int s, int p, int lim,
                                          int[] starts, int[] ends, int[] starts1, int[] ends1) {
    int[] nums = IntStream.generate(() -> -lim + random.nextInt(2 * lim)).limit(s).toArray();
    System.arraycopy(nums, 0, starts, 0, s);
    for (int i = 0; i < s; i++) {
      ends[i] = starts[i] + random.nextInt(s);
    }
    int max = Arrays.stream(ends).max().getAsInt();
    starts[s] = max;
    ends[s] = max + 1;

    int[] points = new int[p];
    for (int i = 0; i < p; i++) {
      points[i] = -lim + 2 * random.nextInt(lim);
    }

    System.arraycopy(starts, 0, starts1, 0, s);
    System.arraycopy(ends, 0, ends1, 0, s);
    return points;
  }

  @Test
  public void test1() {
    int[] starts = { 0, 7, 10 };
    int[] ends = { 5, 10, 11 };
    int[] points = { 1, 6, 11 };
    int[] counts = PointsAndSegments.fastCountSegments(starts, ends, points);
    assertArrayEquals(new int[] { 1, 0, 0 }, counts);
  }

  @Test
  public void test2() {
    int[] starts = { -10, 10 };
    int[] ends = { 10, 11 };
    int[] points = { -100, 100, 0 };
    int[] counts = PointsAndSegments.fastCountSegments(starts, ends, points);
    assertArrayEquals(new int[] { 0, 0, 1 }, counts);
  }

  @Test
  public void test3() {
    int[] starts = { 0, -3, 7, 10 };
    int[] ends = { 5, 2, 10, 11 };
    int[] points = { 1, 6 };
    int[] counts = PointsAndSegments.fastCountSegments(starts, ends, points);
    assertArrayEquals(new int[] { 2, 0 }, counts);
  }

  @Test
  public void test4() {
    int[] starts = { 7, 8, 10 };
    int[] ends = { 9, 10, 11 };
    int[] points = { 8 };
    int[] counts = PointsAndSegments.fastCountSegments(starts, ends, points);
    assertArrayEquals(new int[] { 2 }, counts);
  }

  @Test
  public void test5() {
    int[] starts = { 1, 1, 1, 1 };
    int[] ends = { 1, 1, 1, 2 };
    int[] points = { 1, 1, 1 };
    int[] counts = PointsAndSegments.fastCountSegments(starts, ends, points);
    assertArrayEquals(new int[] { 3, 3, 3 }, counts);
  }

  @Test
  public void test6() {
    int[] starts = { 0, 7, 10 };
    int[] ends = { 5, 10, 11 };
    int[] points = { 1, 6, 11 };
    int[] counts = PointsAndSegments.fastCountSegments(starts, ends, points);
    assertArrayEquals(new int[] { 1, 0, 0 }, counts);
  }

  @Test
  public void testSingularJoin() {
    int[] starts = { 0, 1, 1, 2 };
    int[] ends = { 1, 2, 1, 3 };
    int[] points = { 1 };
    int[] counts = PointsAndSegments.fastCountSegments(starts, ends, points);
    assertArrayEquals(new int[] { 3 }, counts);
  }

  @Test
  public void testNegativeMiddle() {
    int[] starts = { -4, -2 };
    int[] ends = { -2, -1 };
    int[] points = { -3 };
    int[] counts = PointsAndSegments.fastCountSegments(starts, ends, points);
    assertArrayEquals(new int[] { 1 }, counts);
  }

  @Test
  public void testMiddle() {
    int[] starts = { 0, 4 };
    int[] ends = { 4, 5 };
    int[] points = { 2 };
    int[] counts = PointsAndSegments.fastCountSegments(starts, ends, points);
    assertArrayEquals(new int[] { 1 }, counts);
  }

  @Test
  public void testLeftSingularPoint() {
    int[] starts = { 0, 0, 1 };
    int[] ends = { 1, 0, 2 };
    int[] points = { 0 };
    int[] counts = PointsAndSegments.fastCountSegments(starts, ends, points);
    assertArrayEquals(new int[] { 2 }, counts);
  }

  @Test
  public void testRightSingularPoint() {
    int[] starts = { 0, 1, 1 };
    int[] ends = { 1, 1, 2 };
    int[] points = { 1 };
    int[] counts = PointsAndSegments.fastCountSegments(starts, ends, points);
    assertArrayEquals(new int[] { 2 }, counts);
  }

  @Test
  public void testMiddleOverlap() {
    int[] starts = { 0, 1, 4 };
    int[] ends = { 4, 3, 5 };
    int[] points = { 2 };
    int[] counts = PointsAndSegments.fastCountSegments(starts, ends, points);
    assertArrayEquals(new int[] { 2 }, counts);
  }

  @Test
  public void testSingleLeftEdge() {
    int[] starts = { 0, 4 };
    int[] ends = { 4, 5 };
    int[] points = { 0 };
    int[] counts = PointsAndSegments.fastCountSegments(starts, ends, points);
    assertArrayEquals(new int[] { 1 }, counts);
  }

  @Test
  public void testSingleRightEdge() {
    int[] starts = { 0, 4 };
    int[] ends = { 4, 5 };
    int[] points = { 4 };
    int[] counts = PointsAndSegments.fastCountSegments(starts, ends, points);
    assertArrayEquals(new int[] { 1 }, counts);
  }

  @Test
  public void testJoinPoint() {
    int[] starts = { 0, 1, 2 };
    int[] ends = { 1, 2, 3 };
    int[] points = { 1 };
    int[] counts = PointsAndSegments.fastCountSegments(starts, ends, points);
    assertArrayEquals(new int[] { 2 }, counts);
  }

  @Test
  public void testOverlappedJoinPoint() {
    int[] starts = { 0, 1, 0, 2 };
    int[] ends = { 1, 2, 2, 3 };
    int[] points = { 1 };
    int[] counts = PointsAndSegments.fastCountSegments(starts, ends, points);
    assertArrayEquals(new int[] { 3 }, counts);
  }
}