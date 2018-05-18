import org.junit.Test;

import static org.junit.Assert.*;

public class CoveringSegmentsTest {

  @Test
  public void test1() throws Exception {
    CoveringSegments.Segment[] segments = { getSegment(0, 2), getSegment(1, 3) };

    int[] points = CoveringSegments.optimalPoints(segments);

    assertEquals(1, points.length);
    assertEquals(2, points[0]);
  }

  @Test
  public void test2() throws Exception {
    CoveringSegments.Segment[] segments = { getSegment(0, 1), getSegment(1, 2) };

    int[] points = CoveringSegments.optimalPoints(segments);

    assertEquals(1, points.length);
    assertEquals(1, points[0]);
  }

  @Test
  public void test3() throws Exception {
    CoveringSegments.Segment[] segments = { getSegment(0, 1), getSegment(2, 3) };

    int[] points = CoveringSegments.optimalPoints(segments);

    assertEquals(2, points.length);
    assertEquals(1, points[0]);
    assertEquals(3, points[1]);
  }

  @Test
  public void test4() throws Exception {
    CoveringSegments.Segment[] segments = { getSegment(0, 1), getSegment(4, 4), getSegment(2, 3) };

    int[] points = CoveringSegments.optimalPoints(segments);

    assertEquals(3, points.length);
    assertEquals(1, points[0]);
    assertEquals(3, points[1]);
    assertEquals(4, points[2]);
  }

  @Test
  public void test5() throws Exception {
    CoveringSegments.Segment[] segments = { getSegment(0, 1), getSegment(0, 0), getSegment(2, 3) };

    int[] points = CoveringSegments.optimalPoints(segments);

    assertEquals(2, points.length);
    assertEquals(0, points[0]);
    assertEquals(3, points[1]);
  }

  @Test
  public void test6() throws Exception {
    CoveringSegments.Segment[] segments = { getSegment(0, 1), getSegment(0, 0), getSegment(2, 3) };

    int[] points = CoveringSegments.optimalPoints(segments);

    assertEquals(2, points.length);
    assertEquals(0, points[0]);
    assertEquals(3, points[1]);
  }

  @Test
  public void test7() throws Exception {
    CoveringSegments.Segment[] segments = {
        getSegment(1, 10),
        getSegment(3, 12),
        getSegment(11, 12)
    };

    int[] points = CoveringSegments.optimalPoints(segments);

    assertEquals(2, points.length);
  }

  @Test
  public void test8() throws Exception {
    CoveringSegments.Segment[] segments = {
        getSegment(35, 35),
        getSegment(35, 36),
        getSegment(36, 36),
        getSegment(36, 38),
        getSegment(38, 40)
    };

    int[] points = CoveringSegments.optimalPoints(segments);

    assertEquals(3, points.length);
  }

  private CoveringSegments.Segment getSegment(int start, int end) {
    return new CoveringSegments.Segment(start, end);
  }
}