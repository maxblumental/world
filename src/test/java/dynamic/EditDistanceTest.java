package dynamic;

import org.junit.Test;

import static org.junit.Assert.*;

public class EditDistanceTest {

  @Test
  public void test1() {
    int distance = EditDistance.EditDistance("ab", "ab");

    assertEquals(0, distance);
  }

  @Test
  public void test2() {
    int distance = EditDistance.EditDistance("short", "ports");

    assertEquals(3, distance);
  }

  @Test
  public void test3() {
    int distance = EditDistance.EditDistance("editing", "distance");

    assertEquals(5, distance);
  }
}