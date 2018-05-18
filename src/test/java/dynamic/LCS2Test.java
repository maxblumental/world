package dynamic;

import org.junit.Test;

import static org.junit.Assert.*;

public class LCS2Test {

  @Test public void test1() {
    int[] a = { 2, 7, 5 };
    int[] b = { 2, 5 };

    int result = LCS2.lcs2(a, b);

    assertEquals(2, result);
  }

  @Test public void test2() {
    int[] a = { 7 };
    int[] b = { 2, 3, 4 };

    int result = LCS2.lcs2(a, b);

    assertEquals(0, result);
  }

  @Test public void test3() {
    int[] a = { 2, 7, 8, 3 };
    int[] b = { 5, 2, 8, 7 };

    int result = LCS2.lcs2(a, b);

    assertEquals(2, result);
  }
}