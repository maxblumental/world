package dynamic;

import org.junit.Test;

import static org.junit.Assert.*;

public class Partition3Test {

  @Test public void test1() {
    int i = Partition3.partition3(new int[] { 3, 3, 3, 3 });

    assertEquals(0, i);
  }

  @Test public void test2() {
    int i = Partition3.partition3(new int[] { 40 });

    assertEquals(0, i);
  }

  @Test public void test3() {
    int i = Partition3.partition3(new int[] { 17, 59, 34, 57, 17, 23, 67, 1, 18, 2, 59 });

    assertEquals(1, i);
  }

  @Test public void test4() {
    int i = Partition3.partition3(new int[] { 1, 2, 3, 4, 5, 5, 7, 7, 8, 10, 12, 19, 25 });

    assertEquals(1, i);
  }
}