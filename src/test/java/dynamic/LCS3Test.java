package dynamic;

import org.junit.Test;

import static org.junit.Assert.*;

public class LCS3Test {

  @Test public void test1() {
    int[] a = { 1, 2, 3 };
    int[] b = { 2, 1, 3 };
    int[] c = { 1, 3, 5 };

    int result = LCS3.lcs3(a, b, c);

    assertEquals(2, result);
  }

  @Test public void test2() {
    int[] a = { 8, 3, 2, 1, 7 };
    int[] b = { 8, 2, 1, 3, 8, 10, 7 };
    int[] c = { 6, 8, 3, 1, 4, 7 };

    int result = LCS3.lcs3(a, b, c);

    assertEquals(3, result);
  }
}