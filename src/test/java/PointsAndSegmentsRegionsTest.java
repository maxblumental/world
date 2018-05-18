import java.util.Collections;
import java.util.List;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class PointsAndSegmentsRegionsTest {

  @Test
  public void testMiddle() {
    int[] starts = { 0 };
    int[] ends = { 4 };
    List<PointsAndSegments.Region> regions = PointsAndSegments.buildRegions(starts, ends);
    assertEquals(singletonList(new PointsAndSegments.Region(1, 1, 0, 4)), regions);
  }

  @Test
  public void testMiddleOverlap() {
    int[] starts = { 0, 1 };
    int[] ends = { 4, 3 };
    List<PointsAndSegments.Region> regions = PointsAndSegments.buildRegions(starts, ends);
    assertEquals(asList(
        new PointsAndSegments.Region(1, 1, 0, 1),
        new PointsAndSegments.Region(2, 2, 1, 3),
        new PointsAndSegments.Region(1, 2, 3, 4)
    ), regions);
  }

  @Test
  public void testJoinPoint() {
    int[] starts = { 0, 1 };
    int[] ends = { 1, 2 };
    List<PointsAndSegments.Region> regions = PointsAndSegments.buildRegions(starts, ends);
    assertEquals(asList(
        new PointsAndSegments.Region(1, 1, 0, 1),
        new PointsAndSegments.Region(1, 2, 1, 2)
    ), regions);
  }

  @Test
  public void testOverlappedJoinPoint() {
    int[] starts = { 0, 1, 0 };
    int[] ends = { 1, 2, 2 };
    List<PointsAndSegments.Region> regions = PointsAndSegments.buildRegions(starts, ends);
    assertEquals(asList(
        new PointsAndSegments.Region(2, 2, 0, 1),
        new PointsAndSegments.Region(2, 3, 1, 2)
    ), regions);
  }

  @Test
  public void testCross() {
    int[] starts = { 0, 1 };
    int[] ends = { 2, 3 };
    List<PointsAndSegments.Region> regions = PointsAndSegments.buildRegions(starts, ends);
    assertEquals(asList(
        new PointsAndSegments.Region(1, 1, 0, 1),
        new PointsAndSegments.Region(2, 2, 1, 2),
        new PointsAndSegments.Region(1, 2, 2, 3)
    ), regions);
  }

  @Test
  public void testLeftSingularPoint() {
    int[] starts = { 0, 0 };
    int[] ends = { 0, 1 };
    List<PointsAndSegments.Region> regions = PointsAndSegments.buildRegions(starts, ends);
    assertEquals(Collections.singletonList(
        new PointsAndSegments.Region(1, 2, 0, 1)
    ), regions);
  }

  @Test
  public void testRightSingularPoint() {
    int[] starts = { 0, 1 };
    int[] ends = { 1, 1 };
    List<PointsAndSegments.Region> regions = PointsAndSegments.buildRegions(starts, ends);
    assertEquals(Collections.singletonList(
        new PointsAndSegments.Region(1, 1, 0, 1)
    ), regions);
  }
}