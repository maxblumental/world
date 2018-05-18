package lists;

import org.junit.Test;

import static org.junit.Assert.*;

public class TreeHeightTest {

  @Test public void test1() {
    int height = TreeHeight.run(new int[] { 4, -1, 4, 1, 1 });

    assertEquals(3, height);
  }

  @Test public void test2() {
    int height = TreeHeight.run(new int[] { -1, 0, 4, 0, 3 });

    assertEquals(4, height);
  }
}